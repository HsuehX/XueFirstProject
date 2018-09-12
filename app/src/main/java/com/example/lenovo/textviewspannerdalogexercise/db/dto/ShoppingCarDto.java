package com.example.lenovo.textviewspannerdalogexercise.db.dto;

import com.example.lenovo.textviewspannerdalogexercise.db.DbConst;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by xueww on 2018/1/1.
 */

@DatabaseTable(tableName = DbConst.ShoppingCarTable.TABLE_NAME)
public class ShoppingCarDto implements Serializable {
    @DatabaseField(columnName = DbConst.ShoppingCarTable.COLUMN_ID, generatedId = true, canBeNull = false)
    private int id;
    @DatabaseField(columnName = DbConst.ShoppingCarTable.COLUMN_SHOPCODE, canBeNull = true)
    private String shopCode;//商店code
    @DatabaseField(columnName = DbConst.ShoppingCarTable.COLUMN_SHOPNAME, canBeNull = true)
    private String shopName;//商店名
    @DatabaseField(columnName = DbConst.ShoppingCarTable.COLUMN_USERTOKEN, canBeNull = true)
    private String userToken;
//    @DatabaseField(columnName = DbConst.ShoppingCarTable.COLUMN_TYPECODE, canBeNull = true)
//    private String typeCode;//类型
    @DatabaseField(columnName = DbConst.ShoppingCarTable.COLUMN_GOODSCODE, canBeNull = true)
    private String goodsCode;//商品code
    @DatabaseField(columnName = DbConst.ShoppingCarTable.COLUMN_GOODSNAME, canBeNull = true)
    private String goodsName;//商品名
    @DatabaseField(columnName = DbConst.ShoppingCarTable.COLUMN_GOODSPRICE, canBeNull = true)
    private String goodsPrice;//现价
    @DatabaseField(columnName = DbConst.ShoppingCarTable.COLUMN_GOODSCOUNT, canBeNull = true)
    private String goodsCount;//数量
    @DatabaseField(columnName = DbConst.ShoppingCarTable.COLUMN_GOODSINVENTORY, canBeNull = true)
    private String goodsInventory;//库存
    @DatabaseField(columnName = DbConst.ShoppingCarTable.COLUMN_GOODSOLDPRICE, canBeNull = true)
    private String goodsOldPrice;//原价
    @DatabaseField(columnName = DbConst.ShoppingCarTable.COLUMN_GOODSPHOTO, canBeNull = true)
    private String goodsPhoto;//商品照片
    @DatabaseField(columnName = DbConst.ShoppingCarTable.COLUMN_GOODSFREIGHT, canBeNull = true)
    private String goodsFreight;//运费

    @DatabaseField(columnName = DbConst.ShoppingCarTable.COLUMN_GOODSSHOPDESC, canBeNull = true)
    private String goodsShopDesc;//商品描述
    @DatabaseField(columnName = DbConst.ShoppingCarTable.COLUMN_GOODSSHOPCOUNT, canBeNull = true)
    private String goodsShopcount;//
    @DatabaseField(columnName = DbConst.ShoppingCarTable.COLUMN_SKUCODE, canBeNull = true)
    private String skucode;//

    public ShoppingCarDto() {
    }

    public ShoppingCarDto(String shopCode, String userToken, String shopName, String goodsCode, String goodsName,
                          String goodsPrice, String goodsCount, String goodsInventory, String goodsOldPrice, String goodsPhoto,
                          String goodsFreight, String goodsShopDesc, String goodsShopcount, String skucode) {
        this.shopCode = shopCode;
        this.userToken = userToken;
        this.shopName = shopName;
//        this.typeCode = typeCode;
        this.goodsCode = goodsCode;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsCount = goodsCount;
        this.goodsInventory = goodsInventory;
        this.goodsOldPrice = goodsOldPrice;
        this.goodsPhoto = goodsPhoto;
        this.goodsFreight = goodsFreight;

        this.goodsShopDesc = goodsShopDesc;
        this.goodsShopcount = goodsShopcount;
        this.skucode = skucode;
    }

    public ShoppingCarDto(String shopCode, String userToken, String shopName) {
        this.shopCode = shopCode;
        this.userToken = userToken;
        this.shopName = shopName;
    }

    public String getGoodsShopDesc() {
        return goodsShopDesc;
    }

    public void setGoodsShopDesc(String goodsShopDesc) {
        this.goodsShopDesc = goodsShopDesc;
    }

    public String getGoodsShopcount() {
        return goodsShopcount;
    }

    public void setGoodsShopcount(String goodsShopcount) {
        this.goodsShopcount = goodsShopcount;
    }

    public String getSkucode() {
        return skucode;
    }

    public void setSkucode(String skucode) {
        this.skucode = skucode;
    }

    public String getGoodsFreight() {
        return goodsFreight;
    }

    public void setGoodsFreight(String goodsFreight) {
        this.goodsFreight = goodsFreight;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getGoodsPhoto() {
        return goodsPhoto;
    }

    public void setGoodsPhoto(String goodsPhoto) {
        this.goodsPhoto = goodsPhoto;
    }

    public String getGoodsOldPrice() {
        return goodsOldPrice;
    }

    public void setGoodsOldPrice(String goodsOldPrice) {
        this.goodsOldPrice = goodsOldPrice;
    }

    public String getInventory() {
        return goodsInventory;
    }

    public void setInventory(String inventory) {
        this.goodsInventory = inventory;
    }

//    public String getTypeCode() {
//        return typeCode;
//    }
//
//    public void setTypeCode(String typeCode) {
//        this.typeCode = typeCode;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(String goodsCount) {
        this.goodsCount = goodsCount;
    }
}

