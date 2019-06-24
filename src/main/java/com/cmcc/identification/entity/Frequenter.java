package com.cmcc.identification.entity;

import lombok.Data;

@Data
public class Frequenter {

    /**
     * 记录行号
     * 唯一标识记录在接口数据文件中的行号。
     */
    private Integer id;

    /**
     *客户标识
     */
    private String visit_id;

    /**
     *用户标识
     */
    private String uuid;

    /**
     *统计月份
     * 格式：YYYYMM
     */
    private String month;

    /**
     *省公司编码
     */
    private String province_code;

    /**
     *渠道编码
     */
        private String channel_code;
    
}
