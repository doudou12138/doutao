package edu.nju.doudou.doutaoproduct.service.impl;

import edu.nju.doudou.doutaoproduct.entity.CategoryBrandRelationEntity;
import edu.nju.doudou.doutaoproduct.service.CategoryBrandRelationService;
import edu.nju.doudou.doutaoproduct.vo.CategoryVo;
import edu.nju.doudou.doutaoproduct.vo.Catelog2Vo;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.nju.doudou.common.utils.PageUtils;
import edu.nju.doudou.common.utils.Query;

import edu.nju.doudou.doutaoproduct.dao.CategoryDao;
import edu.nju.doudou.doutaoproduct.entity.CategoryEntity;
import edu.nju.doudou.doutaoproduct.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 查询所有分类并形成树结构
     * @return
     */
    @Override
    public List<CategoryVo> listWithTree() {
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);

        List<CategoryVo> result = categoryEntities.stream()
                .filter((item)-> item.getParentCid() == 0)
                .map((item1)-> {
                    CategoryVo categoryVo = new CategoryVo();
                    BeanUtils.copyProperties(item1,categoryVo);
                    return categoryVo;
                })
                .sorted(Comparator.comparingInt(CategoryEntity::getSort))
                .collect(Collectors.toList());

        setChildren(result,categoryEntities);
        return result;
    }

    /**
     * 建立父子关系
     * @param children
     */
    private void setChildren(List<CategoryVo> children, List<CategoryEntity> categoryEntities) {
        List<CategoryEntity> secondCategoies = categoryEntities.stream().filter((item)-> (item.getCatLevel()==2)).collect(Collectors.toList());
        List<CategoryEntity> thirdCategories = categoryEntities.stream().filter((item)-> (item.getCatLevel()==3)).collect(Collectors.toList());

        children.stream().forEach((item)->{
            item.setChildren(
                    secondCategoies.stream()
                            //选择出子类
                            .filter((item1)->(item1.getParentCid().equals(item.getCatId())))
                            //将子类转换成Vo,并且设置子类的子类
                            .map((item2)->{
                                CategoryVo categoryVo = new CategoryVo();
                                BeanUtils.copyProperties(item2,categoryVo);
                                categoryVo.setChildren(
                                        thirdCategories.stream()
                                                .filter((item3)-> item2.getCatId().equals(item3.getParentCid()))
                                                .map((item3)->{
                                                    CategoryVo categoryVo1 = new CategoryVo();
                                                    BeanUtils.copyProperties(item3,categoryVo1);
                                                    return categoryVo1;
                                                })
                                                .sorted(Comparator.comparingInt(CategoryVo::getSort))
                                                .collect(Collectors.toList())
                                );
                                return categoryVo;
                            })
                            .collect(Collectors.toList())
            );
        });
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        //  删除分类前先判断是否有关联的商品
        List<CategoryBrandRelationEntity> categoryBrandRelation =
                categoryBrandRelationService.list(new QueryWrapper<CategoryBrandRelationEntity>().in("catelog_id", asList));

        if (categoryBrandRelation.size() == 0) {
            //逻辑删除
            baseMapper.deleteBatchIds(asList);
        } else {
            throw new RuntimeException("该菜单下面还有属性，无法删除!");
        }

        baseMapper.deleteBatchIds(asList);
    }

    //[2,25,225]
    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId, paths);

        Collections.reverse(parentPath);


        return parentPath.toArray(new Long[parentPath.size()]);
    }

    //225,25,2
    private List<Long> findParentPath(Long catelogId,List<Long> paths){
        //1、收集当前节点id
        paths.add(catelogId);
        CategoryEntity byId = this.getById(catelogId);
        if(byId.getParentCid()!=0){
            findParentPath(byId.getParentCid(),paths);
        }
        return paths;

    }

    @CacheEvict(value = "category",allEntries = true)       //删除某个分区下的所有数据
    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("catalogJson-lock");
        //创建写锁
        RLock rLock = readWriteLock.writeLock();

        try {
            rLock.lock();
            this.baseMapper.updateById(category);
            categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
        }
    }

    @Cacheable(value = {"category"},key = "#root.method.name",sync = true)
    @Override
    public List<CategoryEntity> getLevelCategorys(int level) {
        LOGGER.info("getLevel1Categorys........");
        long l = System.currentTimeMillis();
        List<CategoryEntity> categoryEntities = this.baseMapper.selectList(
                new QueryWrapper<CategoryEntity>().eq("cat_level", level));
        LOGGER.info("消耗时间："+ (System.currentTimeMillis() - l));
        return categoryEntities;
    }

    @Cacheable(value = "category",key = "#root.methodName")
    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
        LOGGER.info("查询了数据库");

        //将数据库的多次查询变为一次
        List<CategoryEntity> selectList = this.baseMapper.selectList(null);

        //1、查出所有分类
        //1、1）查出所有一级分类
        List<CategoryEntity> level1Categorys = getParent_cid(selectList, 0L);

        //封装数据
        Map<String, List<Catelog2Vo>> parentCid = level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            //1、每一个的一级分类,查到这个一级分类的二级分类
            List<CategoryEntity> categoryEntities = getParent_cid(selectList, v.getCatId());

            //2、封装上面的结果
            List<Catelog2Vo> catelog2Vos = null;
            if (categoryEntities != null) {
                catelog2Vos = categoryEntities.stream().map(l2 -> {
                    Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName().toString());

                    //1、找当前二级分类的三级分类封装成vo
                    List<CategoryEntity> level3Catelog = getParent_cid(selectList, l2.getCatId());

                    if (level3Catelog != null) {
                        List<Catelog2Vo.Category3Vo> category3Vos = level3Catelog.stream().map(l3 -> {
                            //2、封装成指定格式
                            Catelog2Vo.Category3Vo category3Vo = new Catelog2Vo.Category3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());

                            return category3Vo;
                        }).collect(Collectors.toList());
                        catelog2Vo.setCatalog3List(category3Vos);
                    }

                    return catelog2Vo;
                }).collect(Collectors.toList());
            }

            return catelog2Vos;
        }));

        return parentCid;
    }

    /**
     * 在指定分类集合中找出指定父类id的子分类
     * @param selectList
     * @param parentCid
     * @return
     */
    private List<CategoryEntity> getParent_cid(List<CategoryEntity> selectList,Long parentCid) {
        List<CategoryEntity> categoryEntities = selectList.stream().filter(item -> item.getParentCid().equals(parentCid)).collect(Collectors.toList());
        return categoryEntities;
    }
}