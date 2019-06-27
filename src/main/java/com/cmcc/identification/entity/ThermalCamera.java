package com.cmcc.identification.entity;

import lombok.Data;

/**
 * 摄像头配置类
 * @author: yx
 * @date: 2019年6月26日 下午5:40:12
 */
@Data
public class ThermalCamera {

    private String mac_address;					// 摄像头mac地址
    private String camera_id;					// 摄像头编号
    private String org_id;						// 营业厅ID
    private String prd_id;						// 5位省代码
    private String cty_id;						// 区域id
    
}