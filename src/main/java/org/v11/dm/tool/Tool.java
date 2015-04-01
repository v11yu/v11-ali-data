package org.v11.dm.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tool {
	/**
	 * 输入时间字符串，返回Date
	 * @param str 例如:2014-11-26 20
	 * @return 时间Date
	 */
	public static Date getTime(String str){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
		try {
			return format.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
