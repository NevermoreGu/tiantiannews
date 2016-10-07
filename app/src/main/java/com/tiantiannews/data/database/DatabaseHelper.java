package com.tiantiannews.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.tiantiannews.base.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;
    final String TABLE_CITY_SQL = "CREATE TABLE IF NOT EXISTS "

            + Constants.CITY_TABLE_NAME + " (" + Constants.CITY_ID

            + " INTEGER PRIMARY KEY AUTOINCREMENT,"

            + Constants.CITY_NAME + " TEXT,"

            + Constants.CITY_PINYIN + " TEXT"

            + ");";

    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        this.mContext = context;
    }

    /**
     * 初次调用getWritableDatabase时，如果数据库不存在才会执行onCreate方法
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

//        db.execSQL(TABLE_CITY_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Create database
     */
    public void createDataBase() throws IOException {
        // 创建数据库
        try {
            File dir = new File(Constants.DATABASE_PATH);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File dbFile = new File(Constants.DATABASE_PATH + Constants.DATABASE_NAME);
            if (dbFile.exists()) {
                dbFile.delete();
            }
            // 复制assets中的db文件到DATABASE_PATH下
            copyDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Copy database
     */
    private void copyDataBase() throws IOException {
        InputStream is = mContext.getAssets().open(Constants.ASSETS_DATABASE_NAME);
        String outFileName = Constants.DATABASE_PATH + Constants.DATABASE_NAME;
        OutputStream output = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        // Close the streams
        output.flush();
        output.close();
        output.close();
    }
}
