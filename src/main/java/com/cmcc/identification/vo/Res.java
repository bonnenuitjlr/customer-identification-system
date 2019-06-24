package com.cmcc.identification.vo;

/**
 * @ClassName: Res
 * @Description TODO : 返回值定义
 * @Author: yxy
 * @Date: 2019-06-24 15:00
 * @Version 1.0
 **/
public enum Res {

    /**
     * 成功
     */
    OK(200, "SUCCESS"),

    /**
     * 失败
     */
    fail(1000, "服务失败"),;


    private int code;
    private String message;

    Res(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
