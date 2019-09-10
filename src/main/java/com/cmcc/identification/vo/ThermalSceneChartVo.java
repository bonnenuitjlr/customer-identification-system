package com.cmcc.identification.vo;

import java.util.Map;

import lombok.Data;

/**
 * 上报摄像头实景图
 * @author: yx
 * @date: 2019年6月26日 下午5:30:34
 */
@Data
public class ThermalSceneChartVo {
	
	private String mac_address;					// 摄像头mac地址，需要转化为摄像头id
    private String org_id;						// 营业厅ID
    private String prd_id;						// 5位省代码
    private String cty_id;						// 区域id
    private String base64_image;				// 摄像头全景图base64编码jpg文件
    private int timestamp;						// Unix时间
    private int duration;						// 上报间隔
    private Map<String,Object> configuration;	// 配置参数

}
