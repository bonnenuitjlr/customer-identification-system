package com.cmcc.identification.entity;

import java.util.Map;

import lombok.Data;

/**
 * 上报摄像头实景图
 * @author: yx
 * @date: 2019年6月26日 下午5:30:34
 */
@Data
public class ThermalSceneChart {
	
	private String camera_id;					// 摄像头id
    private String org_id;						// 营业厅ID
    private String base64_image;				// 摄像头全景图base64编码jpg文件
    private int timestamp;						// Unix时间
    private int duration;						// 上报间隔
    private Map<String,Object> configuration;	// 配置参数

}
