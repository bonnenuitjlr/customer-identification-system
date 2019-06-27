package com.cmcc.identification.entity;

import lombok.Data;

/**
 * 底库识别结果配置类
 * @author: yx
 * @date: 2019年6月27日 上午11:51:21
 */
@Data
public class IdentifyResults {

    private String base64_face;					// 抓拍人脸图base64编码
    private String uuid;						// 识别到的顾客uuid
    private String gender;						// 性别;0-男，1-女;
    private String age;							// 年龄段;0-儿童，3-青年，5-中年，7-老年
    private String is_incoming;					// 进店或离店状态；0:进店 1:离店
    	
}
