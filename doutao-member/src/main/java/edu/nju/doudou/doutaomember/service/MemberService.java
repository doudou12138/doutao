package edu.nju.doudou.doutaomember.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.nju.doudou.common.utils.PageUtils;
import edu.nju.doudou.doutaomember.entity.MemberEntity;
import edu.nju.doudou.doutaomember.exception.PhoneException;
import edu.nju.doudou.doutaomember.exception.UsernameException;
import edu.nju.doudou.doutaomember.vo.MemberRegistVo;
import edu.nju.doudou.doutaomember.vo.MemberUserLoginVo;
import edu.nju.doudou.doutaomember.vo.SocialUser;

import java.util.Map;

/**
 * 会员
 *
 * @author doudou
 * @email 2380601579@qq.com
 * @date 2024-02-23 21:03:01
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 注册
     * @param vo
     */
    void register(MemberRegistVo vo);

    /**
     * 判断邮箱是否重复
     * @param phone
     * @return
     */
    void checkPhoneUnique(String phone) throws PhoneException;

    /**
     * 判断用户名是否重复
     * @param userName
     * @return
     */
    void checkUserNameUnique(String userName) throws UsernameException;

    /**
     * 用户登录
     * @param vo
     * @return
     */
    MemberEntity login(MemberUserLoginVo vo);

    /**
     * 社交用户的登录
     * @param socialUser
     * @return
     */
    MemberEntity login(SocialUser socialUser) throws Exception;


}

