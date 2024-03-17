package edu.nju.doudou.doutaoauthserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import edu.nju.doudou.common.utils.HttpUtils;
import edu.nju.doudou.common.utils.R;
import edu.nju.doudou.common.vo.MemberResponseVo;
import edu.nju.doudou.doutaoauthserver.feign.MemberFeignService;
import edu.nju.doudou.doutaoauthserver.vo.SocialUser;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class OAuth2Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2Controller.class);

    public static final String LOGIN_USER = "loginUser";
    @Autowired
    private MemberFeignService memberFeignService;

    @Value("${oauth.weibo.client-id}")
    private String CLIENT_ID;

    @Value("${oauth.weibo.client-secret}")
    private String CLIENT_SECRET;

    /**
     * 接收微博认证成功后的回调,并带着返回的code,去交换token
     * @param code
     * @param session
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/oauth2.0/weibo/success")
    public String weibo(@RequestParam("code") String code, HttpSession session) throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("client_id",CLIENT_ID);
        map.put("client_secret",CLIENT_SECRET);
        map.put("grant_type","authorization_code");
        map.put("redirect_uri","http://auth.doutao.com/oauth2.0/weibo/success");
        map.put("code",code);

        //1、根据用户授权返回的code换取access_token
        HttpResponse response = HttpUtils.doPost("https://api.weibo.com", "/oauth2/access_token", "post", new HashMap<>(), map, new HashMap<>());

        //2、处理
        if (response.getStatusLine().getStatusCode() == 200) {
            //获取到了access_token,转为通用社交登录对象
            String json = EntityUtils.toString(response.getEntity());
            //String json = JSON.toJSONString(response.getEntity());
            SocialUser socialUser = JSON.parseObject(json, SocialUser.class);

            //知道了哪个社交用户
            //1）、当前用户如果是第一次进网站，自动注册进来（为当前社交用户生成一个会员信息，以后这个社交账号就对应指定的会员）
            //登录或者注册这个社交用户
            LOGGER.info(socialUser.getAccess_token());
            //调用远程服务
            R oauthLogin = memberFeignService.oauthLogin(socialUser);
            if (oauthLogin.getCode() == 0) {
                MemberResponseVo data = oauthLogin.getData("data", new TypeReference<MemberResponseVo>() {});
                LOGGER.info("登录成功：用户信息：{}",data.toString());

                //1、第一次使用session，命令浏览器保存卡号，JSESSIONID这个cookie
                //以后浏览器访问哪个网站就会带上这个网站的cookie
                session.setAttribute(LOGIN_USER,data);

                //2、登录成功跳回首页
                return "redirect:http://doutao.com";
            } else {

                return "redirect:http://auth.doutao.com/login.html";
            }

        } else {
            return "redirect:http://auth.doutao.com/login.html";
        }

    }

}
