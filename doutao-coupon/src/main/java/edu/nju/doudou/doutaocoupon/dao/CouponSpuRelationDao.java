package edu.nju.doudou.doutaocoupon.dao;

import edu.nju.doudou.doutaocoupon.entity.CouponSpuRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券与产品关联
 * 
 * @author doudou
 * @email 2380601579@qq.com
 * @date 2024-02-22 23:53:25
 */
@Mapper
public interface CouponSpuRelationDao extends BaseMapper<CouponSpuRelationEntity> {
	
}
