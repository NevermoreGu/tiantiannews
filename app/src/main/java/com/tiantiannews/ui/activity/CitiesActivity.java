package com.tiantiannews.ui.activity;

import android.content.AsyncQueryHandler;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;

import com.tiantiannews.R;
import com.tiantiannews.base.BaseFragmentActivity;
import com.tiantiannews.base.Constants;
import com.tiantiannews.data.bean.City;
import com.tiantiannews.data.event.CitiesEvent;
import com.tiantiannews.ui.fragment.CityListFragment;
import com.tiantiannews.ui.fragment.SearchCitiesFragment;
import com.tiantiannews.ui.widget.DeleteEditText;
import com.tiantiannews.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.greenrobot.event.EventBus;

public class CitiesActivity extends BaseFragmentActivity {

    @BindView(R.id.det_cities_search)
    DeleteEditText etCitiesSearch;

    CityListFragment cityListFragment;
    SearchCitiesFragment searchCitiesFragment;

    private FragmentTransaction fragmentTransaction;

    private List<City> allCities;

    @Override
    protected void initAppBar() {
        super.initAppBar();
        appBar.setAppBarTitle(R.string.select_city);
        appBar.setOnClickListenerAppBarLeft(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentManager.getBackStackEntryCount() == 1) {
                    fragmentManager.popBackStack();
                    etCitiesSearch.clearFocus();
                } else {
                    finish();
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cities;
    }

    @Override
    public void initVariables() {
        super.initVariables();
        allCities = new ArrayList<>();
        cityListFragment = new CityListFragment();
        searchCitiesFragment = new SearchCitiesFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    @Override
    public void initViews() {
        initAppBar();
    }

    @Override
    public void loadData() {

        fragmentTransaction.add(R.id.fl_cities, cityListFragment);
        fragmentTransaction.commit();

        etCitiesSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fl_cities, searchCitiesFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                } else {

                }
            }
        });

        etCitiesSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                filterData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //城市列表
        String[] projection = {"cityid", "name", "pinyin", "rank"};
        Uri uri = Uri.parse("content://" + Constants.CITIES_AUTHORITY + "/" + Constants.CITY_TABLE_NAME);
        AsyncQueryHandler asyncQueryHandler = new AsyncQueryHandler(mContext.getContentResolver()) {
            @Override
            protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
                while (cursor.moveToNext()) {
                    City city = new City();
                    city.id = cursor.getInt(0);
                    city.name = cursor.getString(1);
                    city.letter = StringUtils.getAlpha(cursor.getString(2));
                    city.rank = cursor.getString(3);
                    allCities.add(city);
                    if (allCities != null && allCities.size() > 0) {
                        EventBus.getDefault().post(new CitiesEvent(allCities));
                    }
                }
                cursor.close();
            }
        };
        asyncQueryHandler.startQuery(0, null, uri, projection, null, null, "pinyin COLLATE LOCALIZED asc");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            if (fragmentManager.getBackStackEntryCount() == 1) {
                fragmentManager.popBackStack();
                etCitiesSearch.clearFocus();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
