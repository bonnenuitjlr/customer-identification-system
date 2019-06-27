package com.cmcc.identification.entity;

import lombok.Data;

/**
 * 压缩包上传
 * @author: yx
 * @date: 2019年6月27日 上午11:56:55
 */
@Data
public class CustomerInfo {

    private Integer id;							// 记录行号
    private String province_code;				// 省公司编码
    private String image_id;					// 图像标识
    private String image_type;					// 图像类型;1现场照，2芯片身份证 3身份证照，4其他
    private String uuid;						// 客户标识
    private String create_time;					// 采集时间;格式：YYYYMMDDHHMMSS
    private String image_source;				// 1 CRM，2国政通，3在线公司，4其他

}
