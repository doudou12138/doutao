package edu.nju.doudou.doutaoproduct.dao;

import edu.nju.doudou.doutaoproduct.entity.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品属性
 * 
 * @author doudou
 * @email 2380601579@qq.com
 * @date 2024-02-22 23:13:57
 */
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {

    /**
     * 筛选出其中是搜索属性的属性ids
     * @param attrIds
     * @return
     */
    List<Long> selectSearchAttrIds(List<Long> attrIds);
}
