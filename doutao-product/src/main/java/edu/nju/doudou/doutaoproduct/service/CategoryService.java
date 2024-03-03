package edu.nju.doudou.doutaoproduct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.nju.doudou.common.utils.PageUtils;
import edu.nju.doudou.doutaoproduct.entity.CategoryEntity;
import edu.nju.doudou.doutaoproduct.vo.CategoryVo;
import edu.nju.doudou.doutaoproduct.vo.Catelog2Vo;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author doudou
 * @email 2380601579@qq.com
 * @date 2024-02-22 23:13:57
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     *
     * @return
     */
    List<CategoryVo> listWithTree();

    void removeMenuByIds(List<Long> asList);

    /**
     * 找到catelog的完整路径
     * @param catelogId
     * @return
     */
    Long[] findCatelogPath(Long catelogId);

    void updateCascade(CategoryEntity category);

    /**
     * 获取指定层级的分类
     * @param i
     * @return
     */
    List<CategoryEntity> getLevelCategorys(int i);

    Map<String, List<Catelog2Vo>> getCatalogJson();
}

