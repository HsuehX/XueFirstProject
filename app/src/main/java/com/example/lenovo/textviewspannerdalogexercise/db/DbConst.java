package com.example.lenovo.textviewspannerdalogexercise.db;

/**
 */
public final class DbConst {
    public static final int DB_VERSION = 1; // 数据库版本号

    /**
     * demo
     */
    public static class DemoTable {
        public static final String TABLE_NAME = "DemoInfo";
        public static final String COLUMN_ID = "ID";
        public static final String COLUMN_COURSEID = "courseID";
        public static final String COLUMN_COURSETITLE = "courseTitle";
        public static final String COLUMN_DATE = "date";
    }

    public static class ShoppingCarTable {
        public static final String TABLE_NAME = "ShoppingCar";
        public static final String COLUMN_ID = "ID";
        public static final String COLUMN_SHOPCODE = "shopCode";//店code
        public static final String COLUMN_SHOPNAME = "shopName";//店名
        public static final String COLUMN_USERTOKEN = "userToken";//用户信息
        public static final String COLUMN_TYPECODE = "typeCode";//大分类Code
        public static final String COLUMN_GOODSCODE = "goodsCode";//商品code
        public static final String COLUMN_GOODSNAME = "goodsName";//商品名
        public static final String COLUMN_GOODSPRICE = "goodsPrice";//价格
        public static final String COLUMN_GOODSCOUNT = "goodsCount";//数量
        public static final String COLUMN_GOODSINVENTORY = "goodsInventory";//库存
        public static final String COLUMN_GOODSOLDPRICE = "goodsOldPrice";//原价格
        public static final String COLUMN_GOODSPHOTO = "goodsPhoto";//商品照片
        public static final String COLUMN_GOODSFREIGHT = "goodsFreight";//商品照片
        public static final String COLUMN_GOODSSHOPDESC = "goodsShopDesc";//商品描述
        public static final String COLUMN_GOODSSHOPCOUNT = "goodsShopcount";//
        public static final String COLUMN_SKUCODE = "skucode ";//
    }

    public static class LocationTable {
        public static final String TABLE_NAME = "Location";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_LAT = "lat";
        public static final String COLUMN_LNG = "lng";
        public static final String COLUMN_PROVINCE = "province";
        public static final String COLUMN_CITY = "city";
        public static final String COLUMN_AREA = "area";
        public static final String COLUMN_STREET = "street";
        public static final String COLUMN_TIME = "time";

    }
}
