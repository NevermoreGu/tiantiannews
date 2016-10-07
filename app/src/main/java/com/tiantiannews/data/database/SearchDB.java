package com.tiantiannews.data.database;

import android.database.sqlite.SQLiteDatabase;

import com.tiantiannews.data.bean.CityInfo;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

/**
 * Created by guqian on 2015/5/17.
 */
public class SearchDB {
    /**
     * 一些基本的数据库方法封装
     */
    private SQLiteDatabase db;

    private static SearchDB searchDB;

    public SearchDB() {
        db = Connector.getDatabase();// 正式生成数据库
    }

    public synchronized static SearchDB getInstance() {
        if (searchDB == null) {
            searchDB = new SearchDB();
        }
        return searchDB;
    }

    /**
     * 将searchCityLog实例存储到数据库
     */
    public void saveCity(CityInfo city) {
        if (city != null) {
            city.save();
        }
    }

    /**
     * 从数据库查询搜索城市记录信息
     */
    public List<CityInfo> getLogCities() {
        List<CityInfo> searchCityLogs = DataSupport.findAll(CityInfo.class);
        return searchCityLogs;
    }

    /**
     * 关闭数据库
     */
    public void destroyDB() {
        if (db != null) {
            db.close();
        }
    }
}
