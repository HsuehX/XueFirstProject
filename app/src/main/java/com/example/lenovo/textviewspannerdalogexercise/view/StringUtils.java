package com.example.lenovo.textviewspannerdalogexercise.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.EditText;

public class StringUtils {
	public final static String UTF_8 = "utf-8";

	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };
	
	/**
	 * 是否是中文
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * 是否是英文 数字组合
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isEnglishOrNum(String str) {
		return str.matches("^[0-9a-zA-Z]+$");
	}

	/**
	 * Add by walker Date 2017年3月4日
	 * @Description: TODO
	 * 校验字符串是否为纯英文 
	 *  @param str 被校验字符串
	 *  @return 为纯英文（[a-zA-Z]）组成返回true，否则返回false
	 */
	public static boolean isEnglish(String str){
		return str.matches("^[a-zA-Z]+$");
	}
	
	/**
	 * @Description: TODO 校验是否为中文(包含中文字符即视为中文)
	 * @param str
	 *            被校验字符串
	 * @return 包含中文字符返回true，不包含中文返回false
	 */
	public static boolean isChinese(String str) {
		String regEx = "[\\u4e00-\\u9fa5]+";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if (m.find())
			return true;
		else
			return false;
	}

	/**
	 * 校验是否仅包含中英文
	 */
	public static boolean isEnglishAndChinese(String str) {
		String strReg = "[A-Za-z0-9\\-\\u4e00-\\u9fa5]+";
		return str.matches(strReg);
	}

	/**
	 * @Description: TODO
	 * 	校验字符串是否包含特殊含义字符： &
	 *  @param str
	 *  @return 包含特殊字符 返回true 否则返回false
	 */
	public static boolean isContainSparator(String str){
		String strSparator[] = new String[]{"&","\t"};
		for (int i = 0; i < strSparator.length; i++) {
			//str 不为空，且包含指定特殊字符
			if(!isEmpty(str)&&str.contains(strSparator[i])){
				return true;
			}
		}
		return false;
	}
	
	/** 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false */
	public static boolean isEmpty(String value) {
		if (value != null && !"".equalsIgnoreCase(value.trim())  && value.trim().length()!=0&&!"null".equals(value)) {
			return false;
		} else {
			return true;
		}
	}
	/**判断所有字符是否为空, 只要一个字符串为空则返回true*/
	public static boolean isContainEmpty(String... values) {
		for (String value : values) {
			 if(isEmpty(value)){
				 return true;//只要一个值为空，则返回false
			 }
		}
		return false;
	}
	public static boolean isEmptyUnNull(String value) {
		if (value != null && !"".equalsIgnoreCase(value.trim()) && !"null".equalsIgnoreCase(value.trim()) && value.trim().length()!=0) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * @Description: TODO
	 *	判断字符串是否为金额：非空 且为数字
	 *  @param str
	 *  @return
	 */
	public static boolean isMoney(String str){
		try {
			double n = Double.parseDouble(str);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

//	/**
//	 * 验证邮箱格式
//	 */
//	public static boolean isEmail(String email) {
//		String str = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
//		Pattern p = Pattern.compile(str);
//		Matcher m = p.matcher(email);
//		return m.matches();
//
//	}

	/**
	 * 验证手机号码格式
	 */
	public static boolean isMobileNO(String mobiles) {
		if (isEmpty(mobiles)) {
			return false;
		}
		Pattern p = Pattern.compile("^1(3[0-9]|4[57]|5[0-35-9]|8[025-9])\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/** 判断多个字符串是否相等，如果其中有一个为空字符串或者null，则返回false，只有全相等才返回true */
	public static boolean isEquals(String... agrs) {
		String last = null;
		for (int i = 0; i < agrs.length; i++) {
			String str = agrs[i];
			if (isEmpty(str)) {
				return false;
			}
			if (last != null && !str.equalsIgnoreCase(last.trim())) {
				return false;
			}
			last = str;
		}
		return true;
	}

	/**
	 * 返回一个高亮spannable
	 * 
	 * @param content
	 *            文本内容
	 * @param color
	 *            高亮颜色
	 * @param start
	 *            起始位置
	 * @param end
	 *            结束位置
	 * @return 高亮spannable
	 */
	public static CharSequence getHighLightText(String content, int color, int start, int end) {
		if (TextUtils.isEmpty(content)) {
			return "";
		}
		start = start >= 0 ? start : 0;
		end = end <= content.length() ? end : content.length();
		SpannableString spannable = new SpannableString(content);
		CharacterStyle span = new ForegroundColorSpan(color);
		spannable.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return spannable;
	}

	/**
	 * url编码为文件路径
	 * 
	 * @Description: TODO
	 *
	 * @param path
	 *            url路径
	 * @return String
	 *
	 */
	public static String getUrlEncodePath(String path) {
		try {
			String substring1 = path.substring(0, path.lastIndexOf("/") + 1);
			String substring = path.substring(path.lastIndexOf("/") + 1);
			path = substring1 + URLEncoder.encode(substring, "utf-8");
			return path;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据文件路径获取文件类型
	 * 
	 * @param path
	 *            文件路径
	 * @return 文件后缀名
	 */
	public static String getFileType(String path) {
		return path.substring(path.lastIndexOf("."));
	}

	/**
	 * 生成Md5
	 * 
	 * @param s
	 *            字符串
	 * @return 字符串的MD5值
	 */
	public static String md5(String s) {
		try {
			// Create MD5 Hash
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();

			return toHexString(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * @Description: TODO
	 *	字符串转 哈希值
	 *  @param b
	 *  @return
	 */
	public static String toHexString(byte[] b) {
		// String to byte
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
			sb.append(HEX_DIGITS[b[i] & 0x0f]);
		}
		return sb.toString();
	}

	/**
	 * String转JsonArray数组
	 * 
	 * @param str
	 * @return JsonArray数组
	 * @throws Exception
	 */
	public static JSONArray stringToJSONArray(String str) throws Exception {
		try {
			return new JSONArray(str);
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage() + ":" + str, e);
		}
	}

	/**
	 * 方法描述：将一个流转换成字符串
	 */
	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null)
				sb.append(line);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				is.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	/**
	 * @Description: TODO
	 *	获取指定EditText中文本：如果文本框对象为null或其内容为空则返回空串，否则返回去除空格后的字符串
	 *  @param editText 输入文本框
	 *  @return 文本框中文本内容
	 */
	public static String getStringFromEdit(EditText editText){
		if(editText != null && !isEmpty(editText.getText().toString())){
			return editText.getText().toString().trim();
		}
		return "";
	}
	
	public static void setStrEdit(String str, EditText et){
		et.setText(isEmpty(str)?"":str);
	}
	
	/**
	 * 比较两个list数据是否相同，并将数据打印到log文件中
	 * @param <T>
	 */
//	public static <T> void compareList(List<T> oldList, List<T> newList, String des){
//		boolean isSame = false;
//		if(oldList == null && newList == null){
//			isSame = true;
//		}else if(oldList != null && newList != null){
//			
//		}
//		
//		if (oldList != null && newList != null) {
//			StringBuilder oldSb = new StringBuilder();
//			StringBuilder newSb = new StringBuilder();
//
//			for (int i = 0; i < oldList.size(); i++) {
//				oldSb.append(oldList.get(i) + "");
//			}
//			for (int i = 0; i < newList.size(); i++) {
//				newSb.append(newList.get(i) + "");
//			}
//			
//			
//			
//			if ( (oldSb+"").replace("null", "").equals((newSb+"").replace("null", ""))) {
//				isSame = true;
//			}
//			String res = des +"\n"+ oldSb + " \n改动后\n" + newSb + "\n =======比对结果======="+CommonUtils.getFormatedDateTime("yyyy-MM-dd HH:mm:ss", System.currentTimeMillis())  +">>>>>>>>>>> \n"+ isSame + "\n\n";
//			System.out.println(res);
//			
//			try {
//				File file = new File(AppContext.getAppContext().Assetsfile_path + "/bohanDbReadLog.txt");
//				if (!file.exists()) {
//					file.createNewFile();
//				}
//				
//				FileWriter fw = new FileWriter(file,true);
//				fw.write(res);
//				fw.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}else{
//			System.out.println( des +"\n" + oldList + " 改动后" + newList + "\n =======比对结果=======>>>>>>" + isSame);
//
//			try {
//				File file = new File(AppContext.getAppContext().Assetsfile_path + "/bohanDbReadLog.txt");
//				if (!file.exists()) {
//					file.createNewFile();
//				}
//				
//				FileWriter fw = new FileWriter(file,true);
//				fw.write( des +"\n" + oldList + " 改动后" + newList + "\n =======比对结果=======>>>>>>" + isSame);
//				fw.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}

	/**
	 * 将字符串转行成double类型
	 */
	public static Double parseDouble(Editable editTex){
		try {
			return Double.parseDouble(editTex.toString().trim());			
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("解析Double异常：","解析数据=============》》》" +  editTex);
			return 0.0;
		}
	}
	/**
	 * 将字符串转行成double类型
	 */
	public static Double parseDouble(String s){
		try {
			return Double.parseDouble(s);			
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("解析Double异常：","解析数据=============》》》" +  s);
			return 0.0;
		}
	}


	/**
	 * @Description: TODO
	 *	获取当前系统时间
	 *  @return  时间格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentDate(){
		try {
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			return format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return ""; 
		}
	}
	
	/**
	 * Add by walker Date 2017年3月12日
	 * @Description: TODO
	 * 当前日期： yyyy-MM-dd
	 *  @return 返回当前年月日时间日期字符串
	 */
	public static String getCurrentDay(){
		try {
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			return format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return ""; 
		}
	}
	
	/**
	 * Add by walker Date 2017年3月12日
	 * @Description: TODO
	 * 当前系统时间转化成毫秒数 
	 *  @param date 日期yyyy-MM-dd
	 *  @return 返回对应的毫秒数
	 */
	public static long dateToMillion(String date){
		try {
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			return format.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return System.currentTimeMillis();
		}
	}

	/**
	 * Add by walker Date 2017年2月8日
	 * @Description: TODO
	 *  将Json字符串转化成插入数据的sql语句
	 *  @param tableName 插入数据的表名称
	 *  @param jsonStr 数据的json字符串
	 *  @return 返回sql执行语句
	 */
	public String getSqlByJsonStr(String tableName, String jsonStr){
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " + tableName + " (");
		StringBuilder sbValue = new StringBuilder();
		sbValue.append(" ) VALUES (");
		try {
			JSONObject jsonObject = new JSONObject(jsonStr);
			Iterator it = jsonObject.keys();
			String key ;
			String value;
			while (it.hasNext()) {
				 key = it.next()+"";
				 value = jsonObject.getString(key);
				if(!isEmpty(key)){
					sb.append(key + ", ");
					sbValue.append(value + ", ");
				}
			}
			sb.delete(sb.length()-1, sb.length());
			sbValue.delete(sbValue.length()-1, sbValue.length());
			sbValue.append(" ) ");
			sb.append(sbValue);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}

	/**
	 * Add by walker Date 2017年2月4日
	 * @Description: TODO
	 *  获取插入数据库数据的sql语句 
	 *  @param map 插入数据的键值对
	 *  @param tableName 数据库表名称
	 *  @return 插入数据库的标准sql语句
	 */
	public static String getInsertSql(Map<String, String> map, String tableName) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into " + tableName + "(");

		Set<Entry<String, String>> set = map.entrySet();
		Iterator<Entry<String, String>> it = set.iterator();
		ArrayList<String> valueList = new ArrayList<String>();
		while (it.hasNext()) {
			Entry<String, String> me = it.next();
			sql.append(me.getKey() + ",");
				valueList.add("'"+me.getValue()+"'");
		}
		sql.replace(sql.length() - 1, sql.length(), ")");
		sql.append("value(");
		for (int i = 0; i < valueList.size(); i++) {
			sql.append(valueList.get(i) + ",");
		}
		sql.replace(sql.length() - 1, sql.length(), ")");

		for (String value : valueList) {
			System.out.println(value);
		}
		
		return sql.toString();
	}

	/**
	 * Add by walker Date 2017年3月4日
	 * @Description: TODO ^\d+(\.\d+)?$
	 * 校验字符串是否为数字（浮点数）
	 *  @param strAddress
	 *  @return
	 */
	public static boolean isNumber(String strAddress) {
		String str = "^\\d+(\\.\\d+)?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(strAddress);
		return m.matches();
	}

	public static String getNumStr(double num){
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		return decimalFormat.format(num);
	}

	/**Add by walker Date 2017年3月12日
	 * @Description: TODO
	 *  获取毫秒数对应的天数
	 *  @param currentTimeMillis 对应的时间
	 */
	public static String getDay(long currentTimeMillis) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd"); 
		return dateFormat.format(currentTimeMillis);
	}

	/**
	 * Add by walker Date 2017年4月5日
	 * @Description: TODO
	 *  判断字符串的值是否存在于字符串数组strArr中
	 *  @param str 字符串
	 *  @param strArr 字符串数组
	 *  @return 如果字符串的值在指定数组strArr中出现，则返回true。如果str 为空、strArr为空或strArr中未出现字符串str则返回false
	 */
	public static boolean strInStrArr(String str, String... strArr){
		if(strArr == null || strArr.length < 1 || isEmpty(str)){
			return false;
		}
		for(String strValue: strArr){
			if(str.trim().equals(strValue)){
				return true;
			}
		}
		return false;
	}


	/**
	 * Add by walker Date 2017年3月30日
	 * @Description: TODO
	 *  判断字符串str是否与其它参数相等，如果存在相等参数，则返回true，否则返回false
	 */
	public static boolean strInStrs(String str, String... strs) {
		if(str == null && strs == null){
			return true;
		}
		if(isEmpty(str)){
			return false;
		}
		for(String s: strs){
			if(str.equalsIgnoreCase(s)){
				return true;
			}
		}
		return false;
	}

}
