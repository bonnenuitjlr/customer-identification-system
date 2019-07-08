package com.cmcc.identification.entity.feigin;

import lombok.Data;

@Data
public class Thermal {
    
    private String image;             //base64编码后的摄像头截图
    private Integer store_id;         //店铺的ID
    private Integer camera_id;        //摄像头的ID
    private Integer time_stamp;       //快照的时间戳
    private Integer duration;         //两次快照的间隔（秒）  
    
}
