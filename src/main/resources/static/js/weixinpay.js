function onBridgeReady(appid,timeStamp,nonceStr,package,signType,paySign){
    WeixinJSBridge.invoke(
        'getBrandWCPayRequest', {
            "appId":appid,     //公众号名称，由商户传入
            "timeStamp":timeStamp,         //时间戳，自1970年以来的秒数
            "nonceStr":nonceStr, //随机串
            "package":package,
            "signType":signType,         //微信签名方式：
            "paySign":paySign //微信签名
        },
        function(res){
            if(res.err_msg == "get_brand_wcpay_request:ok" ){
                // 使用以上方式判断前端返回,微信团队郑重提示：
                //res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。

            }

            $.ajax({
                    url : "/payFinish",
                    type : "POST",
                    dataType : "json",
                    data : JSON.stringify({
                        "payResult":res.err_msg,
                        "openId":openId
                    }),
                    contentType : 'application/json',
                    success : function(data) {

                    },
                    error : function(err) {
                        console.log(err)
                    }

                })
        });
}

