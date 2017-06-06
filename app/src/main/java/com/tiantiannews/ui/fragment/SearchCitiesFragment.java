package com.tiantiannews.ui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tiantiannews.R;
import com.tiantiannews.base.BaseFragment;
import com.tiantiannews.data.bean.City;
import com.tiantiannews.data.bean.CityInfo;
import com.tiantiannews.data.database.SearchDB;
import com.tiantiannews.data.event.CitiesSearchInputEvent;
import com.tiantiannews.ui.adapter.SearchCitiesListAdapter;
import com.tiantiannews.utils.PinyinComparator;
import com.utils.CharacterParser;
import com.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

public class SearchCitiesFragment extends BaseFragment {

    @BindView(R.id.lv_filter_cities)
    ListView listView;

    private SearchCitiesListAdapter adapter;
    private CharacterParser characterParser;
    private PinyinComparator pinyinComparator;
    private ArrayList<City> allCities;
    private ArrayList<City> filterCities;
    private SearchDB db;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_cities;
    }

    @Override
    protected void handlerIntent() {
        allCities = getArguments().getParcelableArrayList("allCities");
    }

    @Override
    public void initVariables() {
        EventBus.getDefault().register(this);
        filterCities = new ArrayList<>();
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        db = SearchDB.getInstance();
    }

    @Override
    public void initViews() {

    }

    @Override
    public void loadData() {
        List<CityInfo> cityLogs = db.getLogCities();
        if (cityLogs.size() > 0) {
            for (CityInfo log : cityLogs) {
                City city = new City();
                city.id = log.getId();
                city.name = log.getName();
                city.letter = StringUtils.getAlpha(log.getPinyin());
                filterCities.add(city);
            }
        }
        adapter = new SearchCitiesListAdapter(mContext, filterCities, true);
        adapter.setOnSelectCityLogListener(new SearchCitiesListAdapter.OnSelectCityLogListener() {
            @Override
            public void selectCityLog(String cityLog) {
                if (cityLog == "清除搜索记录") {
                    DataSupport.deleteAll(CityInfo.class);
                    mContext.finish();
                }
            }
        });
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == filterCities.size()) {
                    //错误
                    adapter.setOnSelectCityLogListener(new SearchCitiesListAdapter.OnSelectCityLogListener() {
                        @Override
                        public void selectCityLog(String cityLog) {
                            if (cityLog == "清除搜索记录") {
                                DataSupport.deleteAll(CityInfo.class);

                            }
                        }
                    });
                } else {
                    City city = filterCities.get(position);
                    insertCityLogDB(city.id, city.name, city.letter);
                }
            }
        });
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param content
     */
    private void filterData(String content) {
        filterCities.clear();
        if (TextUtils.isEmpty(content)) {
            List<CityInfo> cityLogs = db.getLogCities();
            if (cityLogs.size() > 0) {
                for (CityInfo log : cityLogs) {
                    City city = new City();
                    city.id = log.getId();
                    city.name = log.getName();
                    city.letter = StringUtils.getAlpha(log.getPinyin());
                    filterCities.add(city);
                }
            }
            adapter.updateAdapter(filterCities, true);
        } else {
            for (City city : allCities) {
                String name = city.name;
                if (name.indexOf(content) != -1 || characterParser.getSelling(name).startsWith(
                        content)) {
                    filterCities.add(city);
                }
            }
            Collections.sort(filterCities, pinyinComparator);
            adapter.updateAdapter(filterCities, false);
        }
    }

    private void insertCityLogDB(int id, String name, String letter) {
        if (db != null) {
            List<CityInfo> logs = DataSupport.where("name = ?", name).find(CityInfo.class);
            if (logs.size() == 0) {
                CityInfo log = new CityInfo();
                log.setId(id);
                log.setName(name);
                log.setPinyin(letter);
                db.saveCity(log);
            }

        }
    }

    @Subscribe
    public void onEvent(CitiesSearchInputEvent event) {
        filterData(event.getInputContent());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
