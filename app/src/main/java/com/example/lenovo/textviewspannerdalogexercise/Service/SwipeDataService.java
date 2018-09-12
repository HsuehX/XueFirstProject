package com.example.lenovo.textviewspannerdalogexercise.Service;

import android.content.Context;

import com.example.lenovo.textviewspannerdalogexercise.App;
import com.example.lenovo.textviewspannerdalogexercise.BaseService;
import com.example.lenovo.textviewspannerdalogexercise.db.DbConst;
import com.example.lenovo.textviewspannerdalogexercise.db.dto.DemoDto;
import com.example.lenovo.textviewspannerdalogexercise.db.dto.ShoppingCarDto;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xue on 2018/1/15.
 */

public class SwipeDataService extends BaseService {
    private Dao<ShoppingCarDto, String> mShoppingCarDao = null;
    private Dao<DemoDto, String> mDemoDto = null;

    public SwipeDataService(Context context) {
        super(context);
        try {
            mShoppingCarDao = App.getInstance().getDatabaseHelper().getDao(ShoppingCarDto.class);
            mDemoDto = App.getInstance().getDatabaseHelper().getDao(DemoDto.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 加
     */
    public void addDemoDto(DemoDto demoDto) {
        try {
            mDemoDto.create(demoDto);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加
     */
    public void addShoppingCarDto(ShoppingCarDto shoppingCarDao) {
        try {
            mShoppingCarDao.create(shoppingCarDao);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 查询所有
     * @return
     */
    public List<DemoDto> queryForAll() {
        List<DemoDto> list = new ArrayList<DemoDto>();
        try {
            list = mDemoDto.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 查
     */
    public ShoppingCarDto findQuerySelect(String id){
        ShoppingCarDto dto = null;
        try {
            QueryBuilder<ShoppingCarDto, String> builder = mShoppingCarDao.queryBuilder();
            builder.where().eq(DbConst.ShoppingCarTable.COLUMN_USERTOKEN, App.getInstance().getUserToken()).
                    and().eq(DbConst.ShoppingCarTable.COLUMN_SHOPCODE, id);
            dto = mShoppingCarDao.queryForFirst(builder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dto;
    }

    /**
     * 删
     */
    public void deleteDemoDto(String courseID) {
        try {
            DeleteBuilder<DemoDto, String> builder = mDemoDto.deleteBuilder();
            builder.where().eq(DbConst.DemoTable.COLUMN_COURSEID, courseID);
            mDemoDto.delete(builder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
