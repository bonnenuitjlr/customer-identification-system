package com.cmcc.identification.vo;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class R extends LinkedHashMap<String, Object> {

    public R() {
    }

    public R(int status) {
        put("code", status);
        put("message", "success");
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 成功
     * @return
     */
    public static R OK() {
        R res = new R();
        res.put("code", 200);
        res.put("error_msg", "");
        return res;
    }

    /**
     * 成功
     * @return
     */
    public static R OK(com.cmcc.identification.vo.Res res) {
        R response = new R();
        response.put("code", res.getCode());
        response.put("error_msg", res.getMessage());
        return response;
    }

    /**
     * 成功
     * @return
     */
    public static R OK(com.cmcc.identification.vo.Res res, Map<String, Object> body) {
        R response = new R();
        response.put("code", res.getCode());
        response.put("error_msg", res.getMessage());
        response.put("data", body);
        return response;
    }

    /**
     * 成功
     * @param body
     * @return
     */
    public static R OK(Map<String, Object> body) {
        R res = new R();
        res.put("code", 200);
        res.put("error_msg", "");
        res.put("data", body);
        return res;
    }
    
    /**
     * 成功
     * 加入预留字段返回样式
     * @param body
     * @return
     */
    public static R OK(Object body, String content) {
        R res = new R();
        res.put("code", 200);
        res.put("error_msg", "");
        res.put("data", body);
        res.put("isfullview", content);
        return res;
    }

    /**
     * 成功
     * @param body
     * @return
     */
    public static R OK(Object body) {
        R res = new R();
        res.put("code", 200);
        res.put("data", body);
        res.put("error_msg", "");
        return res;
    }

    /**
     * 成功
     * @param code
     * @return
     */
    public static R OK(int code, String message) {
        R res = new R();
        res.put("code", code);
        res.put("data", null);
        res.put("error_msg", message);
        return res;
    }

    /**
     * 失败
     * @param code
     * @return
     */
    public static R ERROR(int code, String message) {
        R res = new R();
        res.put("code", code);
        res.put("data", "");
        res.put("error_msg", message);
        return res;
    }


}
