package com.tiantiannews.ui.activity;

import android.content.AsyncQueryHandler;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tiantiannews.R;
import com.tiantiannews.base.Constants;
import com.tiantiannews.base.LocationActivity;
import com.tiantiannews.data.bean.Cities;
import com.tiantiannews.data.bean.City;
import com.tiantiannews.ui.adapter.CityListAdapter;
import com.tiantiannews.ui.widget.LetterView;
import com.tiantiannews.utils.FileUtils;
import com.tiantiannews.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Observable;

public class CityListActivity extends LocationActivity {

    @BindView(R.id.lv_city_list)
    ListView lvLocationCities;

    @BindView(R.id.tv_location_overlay)
    TextView tvLocationOverlay;

    @BindView(R.id.lv_location_letter)
    LetterView llLocationLetter;

    private CityListAdapter listAdapter;
    private List<City> allCities = null;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_city_list;
    }

    @Override
    public void initVariables() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initAppBar() {
        super.initAppBar();
        appBar.setAppBarTitle(R.string.select_city);
    }

    @Override
    public void initViews() {
        initAppBar();
    }

    @Override
    public void loadData() {
        super.loadData();
        startLocation();

        //城市列表
        String[] projection = {"cityid", "name", "pinyin"};
        Uri uri = Uri.parse("content://" + Constants.CITIES_AUTHORITY + "/" + Constants.CITY_TABLE_NAME);
        AsyncQueryHandler asyncQueryHandler = new AsyncQueryHandler(getContentResolver()) {
            @Override
            protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
                allCities = new ArrayList<>();
                while (cursor.moveToNext()) {
                    City city = new City();
                    city.id = cursor.getInt(0);
                    city.name = cursor.getString(1);
                    city.letter = StringUtils.getAlpha(cursor.getString(2));
                    allCities.add(city);
                }
                if (allCities.size() > 0) {
                    listAdapter = new CityListAdapter(mContext, allCities, "");
                    if (listAdapter != null) {
                        lvLocationCities.setAdapter(listAdapter);
                        listAdapter.setOnSelectCityListener(new CityListAdapter.OnSelectCityListener() {
                            @Override
                            public void selectCity(String cityName) {

                            }
                        });
                    }
                }
                cursor.close();
            }
        };
        asyncQueryHandler.startQuery(0, null, uri, projection, null, null, "pinyin COLLATE LOCALIZED asc");

        lvLocationCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position - 1 >= 0) {

                }
            }
        });

        //快速滚动条
        llLocationLetter.setTextView(tvLocationOverlay);
        llLocationLetter.setOnTouchingLetterChangedListener(new LetterView.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //强制让listView不滚动
                lvLocationCities.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_CANCEL, 0, 0, 0));
                if (s != null) {
                    int position = listAdapter.getPositionForSection(s.charAt(0));
                    if (position != -1) {
                        lvLocationCities.setSelection(position + 1);
                    }
                }
            }
        });
    }

    @Override
    public void onLocationSuccess(AMapLocation loc) {
        String cityName = loc.getCity();
    }

    @Override
    public void onLocationFail() {

    }
}
