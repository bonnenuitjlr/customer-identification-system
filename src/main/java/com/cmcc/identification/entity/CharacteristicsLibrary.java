package com.cmcc.identification.entity;

import lombok.Data;

/**
 * 特征值配置类
 * @author: yx
 * @date: 2019年6月26日 下午5:41:55
 */
@Data
public class CharacteristicsLibrary {

    private String base64_face;					// 人脸图的base64编码
    private String uuid;						// 用户的uuid
    private String type;						// 表示图片的类型;0表示身份证，1表示现场照，2表示摄像头抓拍照
    private String org_id;						// 营业厅ID
    private String prd_id;						// 5位省代码
    private String cty_id;						// 区域id
    
}
