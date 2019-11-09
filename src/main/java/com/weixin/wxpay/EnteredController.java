package com.weixin.wxpay;
import com.alibaba.fastjson.JSON;
import com.weixin.common.Order;
import com.weixin.entity.EnteredDetail;
import com.weixin.wxpay.security.CheckRepeatEntered;
import com.weixin.wxpay.service.RedisService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EnteredController {

    @Autowired
    RedisService redisService;
    /**
     * 报名详情
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

    /**
     * 报名信息存放在redis中，并设置过期时间
     * @return
     */
    @CheckRepeatEntered
    @RequestMapping("/baoming")
    public String pay(HttpServletRequest request, HttpServletResponse response, EnteredDetail enteredDetail){

        String name = enteredDetail.getName();
       String enteredDetailJson = JSON.toJSONString(enteredDetail);
       String openId = enteredDetail.getOpenId();
         long expireTime = 2*60*60;
        redisService.set(openId,enteredDetailJson,expireTime);
         return "pay";
    }

    @RequestMapping(value="pay_finish")
    public String payFinish(@RequestParam("reult")String payResult,@RequestParam("openId")String openId, HttpServletRequest request,HttpServletResponse response,Model model){
       if("get_brand_wcpay_request:ok".equals(payResult)){
            //支付成功
            String userEnteredInfo=(String) redisService.get(openId);
           EnteredDetail enteredDetail =  JSON.parseObject(userEnteredInfo,EnteredDetail.class);
           //存入数据库

           model.addAttribute("payResult","支付成功");
        }else{
           model.addAttribute("payResult","支付成功");
        }

        return "payResult";
    }

    @RequestMapping(value="/main")
    public ModelAndView main(Model model){
        List<EnteredDetail> enteredDetailLsit = new ArrayList<>();
        EnteredDetail enteredDetail = new EnteredDetail();
        enteredDetail.setName("张三");
        enteredDetail.setSex("女");
        enteredDetail.setClasses("1020561");

        EnteredDetail enteredDetail1 = new EnteredDetail();
        enteredDetail1.setName("张三");
        enteredDetail1.setSex("女");
        enteredDetail1.setClasses("1020561");

        enteredDetailLsit.add(enteredDetail);
        enteredDetailLsit.add(enteredDetail1);

        model.addAttribute("enteredDetailLsit",enteredDetailLsit);
        model.addAttribute("title","报名信息");
        return new ModelAndView("main","userModel",model);

    }
}
