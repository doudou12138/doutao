package edu.nju.doudou.doutaomember.dao;

import edu.nju.doudou.doutaomember.entity.MemberLoginLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员登录记录
 * 
 * @author doudou
 * @email 2380601579@qq.com
 * @date 2024-02-23 21:03:01
 */
@Mapper
public interface MemberLoginLogDao extends BaseMapper<MemberLoginLogEntity> {
	
}
