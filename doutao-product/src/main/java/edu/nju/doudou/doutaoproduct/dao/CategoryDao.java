package edu.nju.doudou.doutaoproduct.dao;

import edu.nju.doudou.doutaoproduct.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author doudou
 * @email 2380601579@qq.com
 * @date 2024-02-22 23:13:57
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
