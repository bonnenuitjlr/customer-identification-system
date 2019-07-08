package com.cmcc.identification.entity.feigin;

import lombok.Data;

@Data
public class CharacteristicsLibraryFeign {
    
    private String identity_id;    //全局客户唯一ID
    private String user_pic;       //base64编码的图片   
    private String org_id;         //营业厅id
    private Integer db_no;         //营业厅下底库编号, 其中0.屏蔽库：营业员；1.临时库：领导；2.重点识别库：vip及投诉客户；3.基本库：有身份的顾客；4.当日进库：当日进店人群
    private String uuid;           //客户唯一ID, 36位长度uuid
    private Integer type;          //照片类型：现场照1、芯片照片2、底库照片3、抓拍照4 
}
