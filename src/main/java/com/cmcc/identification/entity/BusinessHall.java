package com.cmcc.identification.entity;
import lombok.Data;

/**
 * 营业厅配置类
 * @author: yx
 * @date: 2019年6月26日 下午5:25:14
 */
@Data
public class BusinessHall{

    private Integer function;					// 表示厅店开启的功能;0:客流+人脸识别+热力;1：客流+热力;2：热力
    private String org_id;						// 营业厅ID
    private String prd_id;						// 5位省代码
    private String cty_id;						// 区域id
            
}
