package com.tiantiannews.base;

public class Constants {

    public static final String ACTION_BOOT_COMPLETED="android.intent.action.BOOT_COMPLETED";

    public static final int PAGE_SIZE = 20;// 默认分页大小
    // token数据
    public static String APPTOKEN = "8e523cf73be561731c69ee923ee7d0f0";
    // 接口地址根
    public static String HTTP_HEAD = "";
    // 接口地址前缀
    public static String HTTP_HEAD_PREFIX = "";
    // config
    public static String HTTP_CONFIG = "http://open.tmtsp.com/app/main/config";
    // 聚合key
    public static int MAXSELECTPICTURES = 9;
    public static final String DESCRIPTOR = "com.umeng.share";


    public static final String APPCONFIG = "appconfig";
    public static final String CAR = "car";
    public static final String NEWSLIST = "newslist";
    public static final String NEWSSCROLL = "newsscroll";
    public static final String VIDEOSLIST = "videoslist";

    /*activity_action*/
    public static final String ACTION_PICTURE_BROWSE = "com.myapplication.picture.browse";

    //检查网络
    public static boolean CheckWifi = true;

    //SQLiteDbAdapter
    public static final String ASSETS_DATABASE_NAME = "cities.db";
    public static final String DATABASE_PATH = "/data/data/com.tiantiannews/databases/";
    public static final String DATABASE_NAME = "cities.db";
    public static final int DATABASE_VERSION = 1;
    public static final String CITY_TABLE_NAME = "cityinfo";
    public static final String CITY_ID = "cityid";
    public static final String CITY_NAME = "name";
    public static final String CITY_PINYIN = "pinyin";
    /**
     * SQliteDBProvider
     */
    public static final int CITIES = 1;
    public static final int CITIES_ID = 2;
    public static final String CITIES_CONTENT_TYPE = "vnd.android.cursor.dir/vnd.database";
    public static final String CITIES_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.database";
    public static final String CITIES_AUTHORITY = "com.guqian.database.cities";


    public static final String PICS = "pics";
}
