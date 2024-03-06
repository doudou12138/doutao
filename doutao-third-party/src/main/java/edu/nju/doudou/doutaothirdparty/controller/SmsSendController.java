package edu.nju.doudou.doutaothirdparty.controller;

import edu.nju.doudou.common.utils.R;
import edu.nju.doudou.doutaothirdparty.utils.AliyunSmsUtil;
import edu.nju.doudou.doutaothirdparty.utils.MediaMsgType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/sms")
public class SmsSendController {


    /**
     * 提供给别的服务进行调用
     * @param phone
     * @param code
     * @return
     */
    @GetMapping(value = "/sendCode")
    public R sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code) {

        //发送验证码
        AliyunSmsUtil.sendMessage(MediaMsgType.LOG_IN,phone,code);

        return R.ok();
    }

}