package com.cmcc.identification.entity;

import lombok.Data;

@Data
public class ThermalCamera {

    /**
     * 摄像头mac地址
     */
    private String mac_address;

    /**
     *摄像头编号
     */
    private String camera_id;

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
