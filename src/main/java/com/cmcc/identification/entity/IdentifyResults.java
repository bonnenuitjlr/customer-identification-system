package com.cmcc.identification.entity;

import lombok.Data;

@Data
public class IdentifyResults {

    /**
     * 抓拍人脸图base64编码
     */
    private String base64_face;

    /**
     *识别到的顾客uuid
     */
    private String uuid;

    /**
     *性别
     * 0-男，1-女
     */
    private String gender;

    /**
     *年龄段
     * 0-儿童，3-青年，5-中年，7-老年
     */
    private String age;

    /**
     *进店或离店状态
     * 0:进店 1:离店
     */
    private String is_incoming;
    
}
