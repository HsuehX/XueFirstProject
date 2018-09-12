package com.example.lenovo.textviewspannerdalogexercise.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.lenovo.textviewspannerdalogexercise.db.dto.DemoDto;
import com.example.lenovo.textviewspannerdalogexercise.db.dto.ShoppingCarDto;
import com.example.lenovo.textviewspannerdalogexercise.log.PathUtilBh;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private Context mContext;
    private Dao<DemoDto, Integer> mDemoDao = null;
    private Dao<ShoppingCarDto, String> mShoppingCarDao = null;
    private static DatabaseHelper instance;

    private static String file_path = PathUtilBh.getInstance().getDbPath();//PathUtil获取路径的工具类，这里是获取数据库路径

    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getInstance(Context context) {
        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null)
                    instance = new DatabaseHelper(context);
            }
        }
        File f = new File(file_path);
        try {
            if (!f.exists()) {
                f.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    public DatabaseHelper(Context context) {
        super(context, file_path + "/app.db", null, DbConst.DB_VERSION);
        mContext = context;
    }

    @Override
    public void close() {
        super.close();
        mDemoDao = null;
        mShoppingCarDao = null;
    }

    public Dao<DemoDto, Integer> getDemoDao() throws SQLException {
        if (mDemoDao == null) {
            mDemoDao = getDao(DemoDto.class);
        }
        return mDemoDao;
    }

    /**
     * 购物车表
     *
     * @return
     * @throws SQLException
     */
    public Dao<ShoppingCarDto, String> getShoppingCarDao() throws SQLException {
        if (mShoppingCarDao == null) {
            mShoppingCarDao = getDao(ShoppingCarDto.class);
        }
        return mShoppingCarDao;
    }


    /**
     * This is called when the database is first created. Usually you should
     * call createTableIfNotExists statements here to create the tables that
     * will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, DemoDto.class);
            TableUtils.createTableIfNotExists(connectionSource, ShoppingCarDto.class); // 购物车表
            // 初始化数据
            onCreateData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 初始化数据
     */
    public void onCreateData() {
        // TODO 初始化密码等数据
    }

    /**
     * 数据库更新
     */
    @Override
    public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int oldVersion, int newVersion) {
        if (oldVersion == 1) {
            try {
                TableUtils.createTableIfNotExists(connectionSource, DemoDto.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
