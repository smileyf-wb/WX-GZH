package com.weixin.wxpay.security;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Aspect
public class CheckRepeatEnteredAspect {

    @Around("@annotation(com.weixin.wxpay.security.CheckRepeatEntered)")
    public Object  checkRepeat(ProceedingJoinPoint point){
        try {
            System.out.println("切面开启检查是否重复报名");
            return point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
