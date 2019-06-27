package com.cmcc.identification.entity;

import lombok.Data;

@Data
public class Frequenter {

    private Integer id;							// 记录行号;唯一标识记录在接口数据文件中的行号。
    private String visit_id;					// 客户标识
    private String uuid;						// 用户标识
    private String month;						// 统计月份; 格式：YYYYMM
    private String province_code;				// 省公司编码
    private String channel_code;				// 渠道编码
    
}
