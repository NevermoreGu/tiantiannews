package com.tiantiannews.base;

public class Constants {

    /**
     * intent action
     */
    public static final String ACTION_BOOT_COMPLETED="android.intent.action.BOOT_COMPLETED";

    public static int MAXSELECTPICTURES = 9;

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
