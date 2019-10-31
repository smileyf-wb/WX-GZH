package com.weixin.wxpay;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
public class EnteredController {

    /**
     * 活动报名
     * @return
     */
    @RequestMapping("/signup")
    public String hello(){
        return "entered";
    }

    /**
     * 活动详情
     * @return
     */
    @RequestMapping("/activityDetail")
    public String activity(){
        return "activityDetail";
    }
}
