package com.weixin.wxpay;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
public class HelloContrller {

    @RequestMapping("/signup")
    public String hello(){
        return "index";
    }
}
