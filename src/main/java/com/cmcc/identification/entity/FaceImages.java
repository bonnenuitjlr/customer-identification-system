package com.cmcc.identification.entity;

import lombok.Data;

/**
 * 抓拍人脸照上报类
 * @author: yx
 * @date: 2019年6月26日 下午5:27:20
 */
@Data
public class FaceImages {

    private String mac_address;					// 摄像头mac地址
    private String base64_face;					// 抓拍人脸的base64编码jpg文件
    private String base64_image;				// 摄像头全景图base64编码jpg文件
    private String timestamp;					// Unix时间
    private String org_id;						// 营业厅ID
    private String prd_id;						// 5位省代码
    private String cty_id;						// 区域id
    private String source;						// 数据来源 ;0表示抓拍;1表示热力
    
}
