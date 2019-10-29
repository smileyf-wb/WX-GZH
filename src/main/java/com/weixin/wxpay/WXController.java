package com.weixin.wxpay;



import com.weixin.common.WXUtil;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;


@RestController
public class WXController {

    @GetMapping("/tokenCheck")
    public void doGet(HttpServletRequest httpRequest, HttpServletResponse response) {

        //获取Get请求携带参数
        String content = httpRequest.getQueryString();
        System.out.println("content:"+content);
        if (content.startsWith("signature")) {
            //检查消息是否来自微信服务器
            String echostr = WXUtil.CheckSignature(content);
            try {
                //返回echostr给微信服务器
                OutputStream os = response.getOutputStream();
                os.write(URLEncoder.encode(echostr, "UTF-8").getBytes());
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @GetMapping("/getToken")
    public String getAccesToken(){
        String token = null;
        return token;
    }

    @GetMapping(value = "/auth")
    @ResponseBody
    public void GuideServlet(HttpServletRequest request,HttpServletResponse response) throws Exception {
        // 设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        /**
         * 第一步：用户同意授权，获取code:https://open.weixin.qq.com/connect/oauth2/authorize
         * ?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE
         * &state=STATE#wechat_redirect
         */
        String redirect_uri = URLEncoder.encode(
                "http://1.1.1.1/wechatServer/login", "UTF-8");// 授权后重定向的回调链接地址，请使用urlencode对链接进行处理（文档要求）
        // 按照文档要求拼接访问地址
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
                + "wx399392132fb80a72"
                + "&redirect_uri="
                + redirect_uri
                + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        response.sendRedirect(url);// 跳转到要访问的地址

    }


}
