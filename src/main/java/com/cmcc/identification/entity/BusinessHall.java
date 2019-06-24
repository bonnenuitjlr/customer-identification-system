package com.cmcc.identification.entity;
import lombok.Data;

@Data
public class BusinessHall{


    /**
     * 表示厅店开启的功能
     * 0:客流+人脸识别+热力
     * 1：客流+热力
     * 2：热力
     */
    private Integer function;

    /**
     * 营业厅ID
     */
    private String org_id;

    /**
     * 5位省代码
     */
    private String prd_id;

    /**
     * 区域id
     */
    private String cty_id;
            
}
