
package com.weixin.wxpay.task;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Date;

@Component
public class TaskTimer {

    @Scheduled(cron = "0 0/59 * * * ?")
    public   String getToken(){
        HttpURLConnection conn=null;
        String access_token = null ;
        String AppID = "wx399392132fb80a72" ;
        String AppSecret = "17844daf7d2f332273b48b1909cb0d39";
            try{
                String Strurl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx399392132fb80a72&secret=17844daf7d2f332273b48b1909cb0d39 ";
                URL url = new URL(Strurl);
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");
                StringBuffer  backcontent = new StringBuffer() ;
                if (HttpURLConnection.HTTP_OK == conn.getResponseCode()){
                    InputStream in=conn.getInputStream();
                    int bytesRead = 0;
                    byte[] buffer = new byte[1024];
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
                    while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                        //将读取的字节转为字符串对象
                        String content = new String(buffer, 0, bytesRead);
                        backcontent.append(content);
                    }
                    System.out.print(backcontent.toString());

                    String   response= URLDecoder.decode(backcontent.toString(),"UTF-8");
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode =objectMapper.readTree(response);
                    //JSONObject json = JSONObject.fromObject(backcontent);
                    access_token=jsonNode.get("access_token").asText();
                   // access_token=json.getString("access_token");
                    if(access_token!=null){
                        System.out.println("获取token成功："+access_token);
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                conn.disconnect();
            }

        return access_token;
    }

}

