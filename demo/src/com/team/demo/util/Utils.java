package com.team.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.team.demo.biz.BizException;

public class Utils {

	/**
	 * 	判断传值是否为空 或空字符串
	 * @param value
	 * @param msg
	 * @throws BizException
	 */
	public static void checkNull(Object value, String msg) throws BizException {
		if (value == null) {
			throw new BizException(msg);
		}
		if (value instanceof String) {
			String svalue = (String) value;
			if (svalue.trim().isEmpty()) {
				throw new BizException(msg);
			}
		}
	}
	
	/**
	 * 获取当前时间
	 * @author Y
	 *
	 */
	public static String getNowTime(){
	          SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式      
	          return df.format(new Date());// new Date()为获取当前系统时间
	}

}
