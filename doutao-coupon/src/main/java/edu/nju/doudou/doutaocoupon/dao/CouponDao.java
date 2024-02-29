package edu.nju.doudou.doutaocoupon.dao;

import edu.nju.doudou.doutaocoupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author doudou
 * @email 2380601579@qq.com
 * @date 2024-02-22 23:53:25
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
