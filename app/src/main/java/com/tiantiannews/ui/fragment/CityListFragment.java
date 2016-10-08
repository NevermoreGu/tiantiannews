package com.tiantiannews.ui.fragment;

import android.content.SharedPreferences;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.eventbus.Subscribe;
import com.tiantiannews.R;
import com.tiantiannews.base.BaseFragment;
import com.tiantiannews.data.bean.City;
import com.tiantiannews.data.event.CitiesEvent;
import com.tiantiannews.ui.adapter.CityListAdapter;
import com.tiantiannews.ui.widget.LetterView;

import java.util.List;

import butterknife.BindView;
import de.greenrobot.event.EventBus;

public class CityListFragment extends BaseFragment {

    @BindView(R.id.lv_city_list)
    ListView lvLocationCities;

    @BindView(R.id.tv_location_overlay)
    TextView tvLocationOverlay;

    @BindView(R.id.lv_location_letter)
    LetterView llLocationLetter;

    private CityListAdapter listAdapter;
    private List<City> allCities;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_city_list;
    }

    @Override
    public void initVariables() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void loadData() {
//        //城市列表
//        String[] projection = {"cityid", "name", "pinyin", "rank"};
//        Uri uri = Uri.parse("content://" + Constants.CITIES_AUTHORITY + "/" + Constants.CITY_TABLE_NAME);
//        AsyncQueryHandler asyncQueryHandler = new AsyncQueryHandler(mContext.getContentResolver()) {
//            @Override
//            protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
//                allCities = new ArrayList<>();
//                while (cursor.moveToNext()) {
//                    City city = new City();
//                    city.id = cursor.getInt(0);
//                    city.name = cursor.getString(1);
//                    city.letter = StringUtils.getAlpha(cursor.getString(2));
//                    city.rank = cursor.getString(3);
//                    allCities.add(city);
//                }
//                if (allCities.size() > 0) {
//                    listAdapter = new CityListAdapter(mContext, allCities, "");
//                    if (listAdapter != null) {
//                        lvLocationCities.setAdapter(listAdapter);
//                        listAdapter.setOnSelectCityListener(new CityListAdapter.OnSelectCityListener() {
//                            @Override
//                            public void selectCity(String cityName) {
//
//                            }
//                        });
//                    }
//                }
//                cursor.close();
//            }
//        };
//        asyncQueryHandler.startQuery(0, null, uri, projection, null, null, "pinyin COLLATE LOCALIZED asc");

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

    @Subscribe
    public void onEvent(CitiesEvent event) {
        allCities = event.getCities();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
