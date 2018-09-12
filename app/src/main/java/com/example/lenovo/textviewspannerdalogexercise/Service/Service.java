package com.example.lenovo.textviewspannerdalogexercise.Service;

import android.app.Application;

import com.example.lenovo.textviewspannerdalogexercise.bean.LocationBean;
import com.example.lenovo.textviewspannerdalogexercise.view.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xueww on 2017/9/14.
 */

public class Service {
    /**
     * @Description: TODO
     *	获取三级联动所需数据
     *  @param i 为1时获取当前机构的省市县为默认选择数据，否则默认选择省市县数据为空
     *  @return
     */
    public LocationBean getLocationBean() {
        LocationBean locationBean = new LocationBean();
//        if(i == 1){//获取本地信息，以当前机构所在地理位置为默认选择项
//
//        }
        //地名级别
        String dmjb;
        //省份名称
        String strProvince;
        //市名称
        String strCity;
        //县区名称
        String strCounty;
        /**map中县区名称对应的Key：省名称＋市名称*/
        String coutountykey;
//        /**行政区划*/
//        String strXZQH;
//        /**获取国内地名表中省市县名称及地名级别、行政区划， 获取的地区的地名代码必须在国内营收地名表中存在，且使用状态为1 －－ 已使用,查询结果按照地名代码分类*/
//        String sql = "select QSJGNDM.SFMC, QSJGNDM.XSMC, QSJGNDM.DSMC, QSJGNDM.DMJB, QSJGNDM.XZQH, QSJGNDM.DMDM from  QSJGNDM "
//                + "inner join QSJYSGNDM  ON QSJGNDM.DMDM = QSJYSGNDM.DMDM WHERE   SYZT='1'   ORDER BY QSJGNDM.DMDM";
////		String sql = "select QSJ_GNDM.*  from  QSJ_GNDM inner join QSJ_YSGNDM  ON QSJ_GNDM.DMDM = QSJ_YSGNDM.DMDM WHERE   SYZT='1'   ORDER BY DMDM";
////		Cursor cursor = DBManager.getInstance(mAppContext).query(sql, null);
//        List<BHHashMap<String>> arrayList = null;
//        try {
//            arrayList = Application.getDatabaseHelper().queryRaw(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            if(arrayList == null){
//                return null;
//            }
//        }
//        ArrayList<String> provinceList = new ArrayList<String>();
//        HashMap<String, ArrayList<String>> cityMap = new HashMap<String, ArrayList<String>>();
//        HashMap<String, ArrayList<String>> countyMap = new HashMap<String, ArrayList<String>>();
//        HashMap<String, ArrayList<String>> countyXzqhMap = new HashMap<String, ArrayList<String>>();
//
//        ArrayList<String> list;
//
//        int size = arrayList.size();
//
//        BHHashMap<String> iteMap;
//
//        for (int y = 0; y < size; y++){
//            iteMap = arrayList.get(y);
//            dmjb = iteMap.get(QSJGNDMTable.COLUMN_DMJB.toUpperCase());
//            strProvince = iteMap.get(QSJGNDMTable.COLUMN_SFMC.toUpperCase());
//            strCity = iteMap.get(QSJGNDMTable.COLUMN_DSMC.toUpperCase());
//            strCounty = iteMap.get(QSJGNDMTable.COLUMN_XSMC.toUpperCase());
//            strXZQH = iteMap.get(QSJGNDMTable.COLUMN_DMDM.toUpperCase());
////			strXZQH = cursor.getString(cursor.getColumnIndex(QsjGndmTable.COLUMN_XZQH));
//            if ("1".equals(dmjb)) {// 地名级别为省
//                if (!StringUtils.isEmptyUnNull(strProvince)) {
//                    provinceList.add(strProvince);
//                }
//            }else if("2".equals(dmjb)){//地名级别为市
//                if(!StringUtils.isContainEmpty(strProvince, strCity) && !strCity.contains("直辖")){
//                    if(cityMap.containsKey(strProvince)){
//                        cityMap.get(strProvince).add(strCity);
//                    }else{
//                        list = new ArrayList<String>();
//                        list.add(strCity);
//                        cityMap.put(strProvince, list);
//                    }
//                }
//            }else if("3".equals(dmjb)){//地名级别为县区
//                coutountykey = strProvince+strCity;
//
//                if(!StringUtils.isContainEmpty( strCounty, strCity, strCounty, strXZQH)){
//                    if(countyMap.containsKey(coutountykey)){
//                        if(countyMap.get(coutountykey).contains(strCounty)){
//                            while (countyMap.get(coutountykey).contains(strCounty)) {
//                                strCounty += " ";
//                            }
//                            countyMap.get(coutountykey).add(strCounty);
//                        }else{
//                            countyMap.get(coutountykey).add(strCounty);
//                        }
//                    }else{
//                        list = new ArrayList<String>();
//                        list.add(strCounty);
//                        countyMap.put(coutountykey, list);
//                    }
//                    //设置行政区划数据
//                    if(countyXzqhMap.containsKey(coutountykey+"xzqh")){
//                        countyXzqhMap.get(coutountykey+"xzqh").add(strXZQH);
//                    }else{
//                        list = new ArrayList<String>();
//                        list.add(strXZQH);
//                        countyXzqhMap.put(coutountykey+"xzqh", list);
//                    }
//
//
////					if(countyMap.containsKey(strCity)){
////						countyMap.get(strCity).add(strCounty);
////					}else{
////						list = new ArrayList<String>();
////						list.add(strCounty);
////						countyMap.put(strCity, list);
////					}
//                }
//            }
//        }

        ArrayList<String> provinceList = new ArrayList<String>();
        HashMap<String, ArrayList<String>> cityMap = new HashMap<String, ArrayList<String>>();
        HashMap<String, ArrayList<String>> countyMap = new HashMap<String, ArrayList<String>>();
        HashMap<String, ArrayList<String>> countyXzqhMap = new HashMap<String, ArrayList<String>>();
        provinceList.add("辽宁");
        provinceList.add("四川");
        provinceList.add("内蒙古");
        provinceList.add("西藏");
        provinceList.add("河北");
        cityMap.put("营口",provinceList);
        cityMap.put("大连",provinceList);
        cityMap.put("沈阳",provinceList);
        cityMap.put("鞍山",provinceList);
        cityMap.put("盘锦",provinceList);
        countyMap.put("?",provinceList);
        countyMap.put("?",provinceList);
        countyMap.put("?",provinceList);
        countyMap.put("？",provinceList);
        countyMap.put("？",provinceList);
        countyXzqhMap.put("！",provinceList);
        countyXzqhMap.put("！",provinceList);
        countyXzqhMap.put("！",provinceList);
        countyXzqhMap.put("！",provinceList);

        locationBean.cityMap = countyMap;
        locationBean.provinceMap = cityMap;
        locationBean.provinceList = provinceList;
        locationBean.xzqhMap = countyXzqhMap;
        return locationBean;
    }
}
