/**   
* @Title: LocationBean.java
* @Package com.bohan.ems.ui.viewutils
* @Description: TODO
* @author A18ccms A18ccms_gmail_com   
* @date 2016年8月2日 上午11:49:37
* @version V1.0   
*/
package com.example.lenovo.textviewspannerdalogexercise.bean;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @ClassName: LocationBean
 *	显示三级联动选择器需要的数据实体:
 *	无敏感数据，每次进入界面时刷新，因此安全性要求不高，所有变量public属性
 *
 * @author walker
 *
 * @date 2016年8月2日 上午11:38:36
 *
 */
public class LocationBean {

	/**当前已选择省名称*/
	public String selectProvince;
	/**当前已选择市名称*/
	public String selectCity;
	/**当前已选择区县名称*/
	public String selectCounty;
	/**所有省名称集合*/
	public ArrayList<String> provinceList= new ArrayList<String>();
	/**省map集合： 省名为键，对应省名下所有市的集合为值*/
	public HashMap<String, ArrayList<String>> provinceMap;
	/**市map集合： 市名为键，对应市下所有县区的集合为值*/
	public HashMap<String, ArrayList<String>> cityMap;
	/**行政区划集合,以当前省市县名称为key*/
	public HashMap<String, ArrayList<String>> xzqhMap;
	
}
