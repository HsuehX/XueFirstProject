/**   
* @Title: TypeUtil.java
* @Package com.bohan.ems.utils
* @Description: TODO
* @author A18ccms A18ccms_gmail_com   
* @date 2017年3月9日 下午6:05:19
* @version V1.0   
*/
package com.example.lenovo.textviewspannerdalogexercise.log;

import java.text.DecimalFormat;

import javax.xml.parsers.DocumentBuilderFactory;

/** @ClassName: TypeUtil
 *
 * @Description: TODO
 *
 * @author walker
 *
 * @date 2017年3月9日 下午6:05:19
 * 
 */
public class TypeUtil {
	/**
	 * Add by walker Date 2017年3月9日
	 * @Description: TODO
	 * 	String 类型转换成double数字 
	 *  @param str 被转换字符串
	 *  @return 如果是合法数字字符串时返回对应数字，否则返回0
	 */
	public static double strToDouble(String str){
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * Add by walker Date 2017年3月9日
	 * @Description: TODO
	 * 	String 类型转换成double数字 
	 *  @param str 被转换字符串
	 *  @return 如果是合法数字字符串时返回对应数字，否则返回0
	 */
	public static String strToDoubleStr(String str, int decimal){
		StringBuilder  sb = new StringBuilder();
		sb.append("0.");
		for(int i = 0; i < decimal; i++){
			sb.append("0");
		}
		if(sb.indexOf(".") == sb.length()-1){
			sb.deleteCharAt(sb.length()-1);
		}
		DecimalFormat format = new DecimalFormat(sb.toString());
		try {
			double num = Double.parseDouble(str);
			return format.format(num);
		} catch (Exception e) {
			e.printStackTrace();
			return "0.00";
		}
	}

	/**
	 * Add by walker Date 2017年3月12日
	 * @Description: TODO
	 *  
	 *  @param date
	 */
	public static int strToInt(String intStr) {
		try {
			return Integer.parseInt(intStr);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
