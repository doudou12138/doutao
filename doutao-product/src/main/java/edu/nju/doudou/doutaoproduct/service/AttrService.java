package edu.nju.doudou.doutaoproduct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.nju.doudou.common.utils.PageUtils;
import edu.nju.doudou.doutaoproduct.entity.AttrAttrgroupRelationEntity;
import edu.nju.doudou.doutaoproduct.entity.AttrEntity;
import edu.nju.doudou.doutaoproduct.vo.AttrGroupRelationVo;
import edu.nju.doudou.doutaoproduct.vo.AttrGroupWithAttrsVo;
import edu.nju.doudou.doutaoproduct.vo.AttrRespVo;
import edu.nju.doudou.doutaoproduct.vo.AttrVo;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author doudou
 * @email 2380601579@qq.com
 * @date 2024-02-22 23:13:57
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attr);

    /**
     * 获取属性列表
     * @param params
     * @param catelogId
     * @param type
     * @return
     */
    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type);

    /**
     * 获取属性信息
     * @param attrId
     * @return
     */
    AttrRespVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attr);

    /**
     * 根据分组id查找关联的所有属性
     * @param attrgroupId
     * @return
     */
    List<AttrEntity> getRelationAttr(Long attrgroupId);

    /**
     * 根据多个分组id查找关联的所有属性
     * @param attrgroupIds
     * @return
     */
    List<AttrGroupWithAttrsVo> getRelationAttr(List<Long> attrgroupIds);

    /**
     * 删除属性与分组关联信息
     * @param vos
     */
    void deleteRelation(AttrGroupRelationVo[] vos);

    /**
     * 获取当前分组没有关联的其他属性
     * @param params
     * @param attrgroupId
     * @return
     */
    PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId);

    /**
     * 根据attrIds筛选出其中的搜索属性的attrIds
     * @param attrIds
     * @return
     */
    List<Long> selectSearchAttrs(List<Long> attrIds);
}

