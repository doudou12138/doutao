package edu.nju.doudou.doutaoproduct.service.impl;

import edu.nju.doudou.doutaoproduct.service.CategoryBrandRelationService;
import edu.nju.doudou.doutaoproduct.vo.CategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

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
     * //todo: 二级分类进行排序报空指针
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
        // TODO: 2019/8/30 删除分类前先判断是否有关联的商品

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

    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(),category.getName());
    }
}