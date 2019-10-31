package com.weixin.wxpay;
import com.weixin.common.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
public class EnteredController {

    /**
     * 活动报名
     * @return
     */
    @RequestMapping("/signup")
    public String hello( Model model,Order order){
        /*String trade_fee = order.getTrade_fee();
        model.addAttribute("fee",trade_fee);*/

        System.out.println("fee:"+order.getTrade_fee());
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
