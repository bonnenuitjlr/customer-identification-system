package com.cmcc.identification.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;


public class ErrorConst {
	
	private static Map<Integer,String> message = new HashMap<Integer,String>();
	static{
		message.put(ErrorConst.SUCC, "success");
		
		message.put(ErrorConst.RFAIL, "report fail");
		message.put(ErrorConst.R501, "热力区域上报错误");
		
		message.put(ErrorConst.QFAIL, "query fail");
		message.put(ErrorConst.Q601, "热力图查询错误");
	}

	public final static int SUCC  = 200; //请求成功	
	public final static int RFAIL = 500; //上报错误
	public final static int R501 = 501; //热力区域上报错误
	
	public final static int QFAIL = 600; //查询错误
	public final static int Q601 = 601; //热力图查询错误
	
	public final static String getMessage(Integer code){
		String msg = message.get(code);
		if(StringUtils.isEmpty(msg)){
			msg = "fail";
		}
		return msg;
	}
}
