package com.weixin.wxpay;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
public class EnteredController {

    @RequestMapping("/signup")
    public String hello(){
        return "entered";
    }
}
