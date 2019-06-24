package com.cmcc.identification.entity;

import lombok.Data;

@Data
public class FaceImages {

    /**
     * 摄像头mac地址
     */
    private String mac_address;
    
    /**
     *抓拍人脸的base64编码jpg文件
     */
    private String base64_face;
    
    /**
     *摄像头全景图base64编码jpg文件
     */
    private String base64_image;
    
    /**
     *Unix时间
     */
    private String timestamp;

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

    /**
     *数据来源
     * 0表示抓拍
     * 1表示热力
     */
    private String source;
    
}
