package com.cmcc.identification.entity;

import lombok.Data;

import java.util.List;

@Data
public class CharacteristicsLibrary {

    /**
     * 人脸图的base64编码
     */
    private String base64_face;

    /**
     *用户的uuid
     */
    private String uuid;

    /**
     *表示图片的类型
     * 0表示身份证，1表示现场照，2表示摄像头抓拍照
     */
    private String type;

    /**
     *营业厅ID
     */
    private String org_id;

    /**
     *5位省代码
     */
    private String prd_id;

    /**
     *区域id
     */
    private String cty_id;
    

}
