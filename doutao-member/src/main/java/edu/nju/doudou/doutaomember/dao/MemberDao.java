package edu.nju.doudou.doutaomember.dao;

import edu.nju.doudou.doutaomember.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author doudou
 * @email 2380601579@qq.com
 * @date 2024-02-23 21:03:01
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
