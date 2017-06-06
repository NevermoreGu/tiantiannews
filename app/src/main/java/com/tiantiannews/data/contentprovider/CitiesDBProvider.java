package com.tiantiannews.data.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.v4.database.DatabaseUtilsCompat;

import com.tiantiannews.base.Constants;
import com.tiantiannews.data.bean.CityInfo;
import com.tiantiannews.data.database.DatabaseHelper;
import com.tiantiannews.data.database.SearchDB;

import java.io.IOException;

public class CitiesDBProvider extends ContentProvider {

    private DatabaseHelper mOpenHelper;
    private static final String UNKNOWN_URI_LOG = "Unknown URI ";
    private static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

        URI_MATCHER.addURI(Constants.CITIES_AUTHORITY, Constants.CITY_TABLE_NAME,
                Constants.CITIES);
        URI_MATCHER.addURI(Constants.CITIES_AUTHORITY, Constants.CITY_TABLE_NAME + "/#",
                Constants.CITIES_ID);
    }

    /*
     * 返回URI参数对应的数据的MIME类型
     *
     * @see android.content.ContentProvider#getType(android.net.Uri)
     */
    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case Constants.CITIES:
                return Constants.CITIES_CONTENT_TYPE;
            case Constants.CITIES_ID:
                return Constants.CITIES_CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException(UNKNOWN_URI_LOG + uri);
        }
    }

    /**
     * 只用第一次安装apk会调动onCreate()方法
     *
     * @return
     */
    @Override
    public boolean onCreate() {
        mOpenHelper = new DatabaseHelper(getContext());
        try {
            mOpenHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        db = SearchDB.getInstance();
//        String fileJson = FileUtils.getStringFormAssets(getContext(), "cities.json");
//        Gson gson = new Gson();
//        Cities cities = null;
//        if (!StringUtils.isEmpty(fileJson)) {
//            cities = gson.fromJson(fileJson, Cities.class);
//        }
//        final List<Cities.DataBean> data = cities.data;
//        Observable.from(data).subscribe(new Action1<Cities.DataBean>() {
//            @Override
//            public void call(Cities.DataBean dataBean) {
//                if (dataBean == null || dataBean.locationId == null || dataBean.rank == null || dataBean.acronym == null || dataBean.divisionStr == null) {
//                    ToastUtils.makeLongText(getContext(), dataBean.id + "");
//                }
//                insertCityLogDB(dataBean.id, dataBean.name, dataBean.pinyin,
//                        dataBean.rank, dataBean.acronym, dataBean.onlineTime, dataBean.divisionStr, dataBean.locationId,
//                        dataBean.lng, dataBean.lat,
//                        dataBean.isOpen);
//            }
//        });

        return true;
    }

    private SearchDB db;

    private void insertCityLogDB(int id, String name, String letter,
                                 String rank,
                                 String acronym,
                                 long onlineTime,
                                 String divisionStr,
                                 String locationId,
                                 double lng,
                                 double lat,
                                 boolean isOpen) {
        if (db != null) {
//            List<CityInfo> logs = DataSupport.where("name = ?", name).find(CityInfo.class);
//            if (logs.size() == 0) {
            CityInfo log = new CityInfo();
            log.setId(id);
            log.setName(name);
            log.setPinyin(letter);
            log.setAcronym(acronym);
            log.setDivisionStr(divisionStr);
            log.setLat(lat);
            log.setLng(lng);
            log.setOnlineTime(onlineTime);
            log.setOpen(isOpen);
            log.setRank(rank);
            log.setLocationId(locationId);
            db.saveCity(log);
//            }
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] finalSelectionArgs = selectionArgs;
        int matched = URI_MATCHER.match(uri);
        Uri regUri = uri;
        String finalSortOrder = sortOrder;
        String finalGrouping = null;
        String finalHaving = null;
        Cursor c;
        switch (matched) {
            case Constants.CITIES:
                qb.setTables(Constants.CITY_TABLE_NAME);

                break;
            case Constants.CITIES_ID:
                qb.setTables(Constants.CITY_TABLE_NAME);
                qb.appendWhere(Constants.CITY_ID + "=?");
                finalSelectionArgs = DatabaseUtilsCompat.appendSelectionArgs(
                        selectionArgs, new String[]{uri.getLastPathSegment()});
                break;
            default:
                break;
        }
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        c = qb.query(db, projection, selection, finalSelectionArgs,
                finalGrouping, finalHaving, finalSortOrder);

        c.setNotificationUri(getContext().getContentResolver(), regUri);
        return c;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int matched = URI_MATCHER.match(uri);
        String matchedTable = null;
        Uri baseInsertedUri = null;
        switch (matched) {
            case Constants.CITIES:
            case Constants.CITIES_ID:
                matchedTable = Constants.CITY_TABLE_NAME;
                break;
            default:
                break;
        }
        if (matchedTable == null) {
            throw new IllegalArgumentException(UNKNOWN_URI_LOG + uri);
        }
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        long rowId = db.insert(matchedTable, null, values);
        if (rowId >= 0) {
            Uri retUri = ContentUris.withAppendedId(baseInsertedUri, rowId);
            getContext().getContentResolver().notifyChange(retUri, null);
            return retUri;
        }
        throw new SQLException("Failed to insert row into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String finalWhere;
        int count = 0;
        int matched = URI_MATCHER.match(uri);
        Uri regUri = uri;
        switch (matched) {
            case Constants.CITIES:
                count = db.delete(Constants.CITY_TABLE_NAME, selection,
                        selectionArgs);
                break;
            case Constants.CITIES_ID:
                // 拼接地址
                finalWhere = DatabaseUtilsCompat.concatenateWhere(Constants.CITY_ID
                        + "=" + ContentUris.parseId(uri), selection);
                count = db.delete(Constants.CITY_TABLE_NAME, finalWhere,
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException(UNKNOWN_URI_LOG + uri);
        }
        getContext().getContentResolver().notifyChange(regUri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;
        String finalWhere;
        int matched = URI_MATCHER.match(uri);
        switch (matched) {
            case Constants.CITIES:
                count = db.update(Constants.CITY_TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            case Constants.CITIES_ID:
                // 拼接地址
                finalWhere = DatabaseUtilsCompat.concatenateWhere(Constants.CITY_ID
                        + "=" + ContentUris.parseId(uri), selection);
                count = db.update(Constants.CITY_TABLE_NAME, values, finalWhere,
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException(UNKNOWN_URI_LOG + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

}

