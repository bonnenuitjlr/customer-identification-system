package com.cmcc.identification.entity;

import lombok.Data;

@Data
public class CustomerInfo {

    /**
     * 记录行号
     */
    private Integer id;

    /**
     *省公司编码
     */
    private String province_code;

    /**
     *图像标识
     */
    private String image_id;

    /**
     *图像类型
     * 1现场照，2芯片身份证 3身份证照，4其他
     */
    private String image_type;

    /**
     *客户标识
     */
    private String uuid;

    /**
     *采集时间
     * 格式：YYYYMMDDHHMMSS
     */
    private String create_time;

    /**
     *1 CRM，2国政通，3在线公司，4其他
     */
    private String image_source;

}
