package com.weixin.common;

import java.util.HashMap;
import java.util.Map;

public class ReponseMap extends HashMap<String,Object> {
    private static final long serialVersionUID = 1L;

    public static final String SUCCESS = "success";

    public static final String EXCEPTION = "exception";

    public static final Integer SUCCESSCODE = 0;

    public static final Integer EXCEPTIONCODE = 500;


    public ReponseMap() {
        put("errCode", 0);
        put("msg", SUCCESS);
    }

    public ReponseMap(int code, String msg){
        put("errCode", code);
        put("msg", msg);
    }

    public static ReponseMap error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static ReponseMap error(String msg) {
        return error(500, msg);
    }

    public static ReponseMap error(int code, String msg) {
        ReponseMap reponseMap = new ReponseMap();
        reponseMap.put("errCode", code);
        reponseMap.put("msg", msg);
        return reponseMap;
    }

    public static ReponseMap ok(String msg) {
        ReponseMap reponseMap = new ReponseMap();
        reponseMap.put("msg", msg);
        return reponseMap;
    }

    public static ReponseMap ok(Map<String, Object> map) {
        ReponseMap reponseMap = new ReponseMap();
        reponseMap.putAll(map);
        return reponseMap;
    }

    public static ReponseMap ok() {
        return new ReponseMap();
    }

    public ReponseMap put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
