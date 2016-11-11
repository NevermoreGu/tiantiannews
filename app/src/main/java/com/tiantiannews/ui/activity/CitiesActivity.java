package com.tiantiannews.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.tiantiannews.R;
import com.tiantiannews.base.LocationActivity;
import com.tiantiannews.data.event.CitiesSearchInputEvent;
import com.tiantiannews.data.event.CityChangeEvent;
import com.tiantiannews.ui.fragment.CityListFragment;
import com.tiantiannews.ui.fragment.SearchCitiesFragment;
import com.tiantiannews.ui.widget.DeleteEditText;

import butterknife.BindView;
import de.greenrobot.event.EventBus;

public class CitiesActivity extends LocationActivity {

    @BindView(R.id.det_cities_search)
    DeleteEditText etCitiesSearch;

    CityListFragment cityListFragment;
    SearchCitiesFragment searchCitiesFragment;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

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
    public void initViews() {
        initAppBar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            cityListFragment = new CityListFragment();
            searchCitiesFragment = new SearchCitiesFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fl_cities, cityListFragment);
            fragmentTransaction.commit();
        } else {
            Fragment fragment = getVisibleFragment();
            if (fragment != null) {
                if (fragment instanceof CityListFragment) {
                    cityListFragment = (CityListFragment) fragment;
                    searchCitiesFragment = new SearchCitiesFragment();
                } else if (fragment instanceof CityListFragment) {
                    searchCitiesFragment = (SearchCitiesFragment) fragment;
                    cityListFragment = new CityListFragment();
                }
            }
        }
    }

    @Override
    public void loadData() {

        etCitiesSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Bundle bundle = new Bundle();

                    bundle.putParcelableArrayList("allCities", cityListFragment.getAllCities());
                    searchCitiesFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fl_cities, searchCitiesFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });

        etCitiesSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EventBus.getDefault().post(new CitiesSearchInputEvent(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onLocationSuccess(AMapLocation loc) {
        EventBus.getDefault().post(new CityChangeEvent(loc));
    }

    @Override
    public void onLocationFail() {
        EventBus.getDefault().post(new CityChangeEvent(null));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (fragmentManager.getBackStackEntryCount() == 1) {
                fragmentManager.popBackStack();
                etCitiesSearch.clearFocus();
                return true;
            }
            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }
}
