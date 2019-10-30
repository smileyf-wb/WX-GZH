package com.weixin.common;

import lombok.Data;


public class Order {
   private String openId;

    /**
     * 订单编号
     */
    private String trade_no ;

    /**
     * 订单金额
     */
    private String trade_fee;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getTrade_fee() {
        return trade_fee;
    }

    public void setTrade_fee(String trade_fee) {
        this.trade_fee = trade_fee;
    }
}
