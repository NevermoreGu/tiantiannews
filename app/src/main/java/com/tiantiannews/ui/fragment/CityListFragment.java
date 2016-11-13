package com.tiantiannews.ui.fragment;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.eventbus.Subscribe;
import com.tiantiannews.R;
import com.tiantiannews.base.BaseFragment;
import com.tiantiannews.base.Constants;
import com.tiantiannews.data.bean.City;
import com.tiantiannews.data.event.CityChangeEvent;
import com.tiantiannews.ui.adapter.CityListAdapter;
import com.tiantiannews.ui.widget.LetterView;
import com.tiantiannews.utils.StringUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
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
    private ArrayList<City> allCities;

    private AsyncQueryHandler asyncQueryHandler;

    private MyHandler myHandler;
    private MyHandler1 myHandler1;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("allCities", allCities);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_city_list;
    }

    @Override
    public void initVariables() {
        EventBus.getDefault().register(this);
        allCities = new ArrayList<>();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            allCities = savedInstanceState.getParcelableArrayList("allCities");
        }
    }

    @Override
    public void initViews() {

    }


    @Override
    public void loadData() {
        //城市列表
        String[] projection = {"cityid", "name", "pinyin", "rank"};
        Uri uri = Uri.parse("content://" + Constants.CITIES_AUTHORITY + "/" + Constants.CITY_TABLE_NAME);
        asyncQueryHandler = new AsyncQueryHandler(mContext.getContentResolver()) {
            @Override
            protected void onQueryComplete(int token, Object cookie, Cursor cursor) {

                City city;
                while (cursor.moveToNext()) {
                    city = new City();
                    city.id = cursor.getInt(0);
                    city.name = cursor.getString(1);
                    city.letter = StringUtils.getAlpha(cursor.getString(2));
                    city.rank = cursor.getString(3);
                    allCities.add(city);
                }
                if (allCities != null && allCities.size() > 0) {
                    listAdapter = new CityListAdapter(mContext, allCities, "");

                    lvLocationCities.setAdapter(listAdapter);
                    listAdapter.setOnSelectCityListener(new CityListAdapter.OnSelectCityListener() {
                        @Override
                        public void selectCity(String cityName) {

                        }
                    });
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
        myHandler = new MyHandler(getActivity());
        myHandler1 = new MyHandler1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myHandler.sendEmptyMessage(0);
                myHandler1.sendEmptyMessage(0);
            }
        }).start();


    }

    public ArrayList<City> getAllCities() {
        return allCities;
    }

    @Subscribe
    public void onEvent(CityChangeEvent event) {
        if (event != null) {
            event.getCity();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacksAndMessages(null);
        asyncQueryHandler.cancelOperation(0);
        EventBus.getDefault().unregister(this);
    }

    static class MyHandler extends Handler {

        private final WeakReference<Activity> mTarget;

        public MyHandler(Activity activity) {
            mTarget = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Activity target = mTarget.get();
            if (target != null) {
                if (msg.what == 0) {

                    List<City> c = new ArrayList<>();
                    City city = new City();
                    c.add(city);
                }
            }
        }
    }

    class MyHandler1 extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0) {

                List<City> c = new ArrayList<>();
                City city = new City();
                c.add(city);
            }

        }
    }

    static class CitiesAsyncQueryHandler extends AsyncQueryHandler {

        private final WeakReference<Activity> mTarget;

        public CitiesAsyncQueryHandler(ContentResolver cr, Activity activity) {
            super(cr);
            mTarget = new WeakReference<>(activity);
        }

        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            Activity target = mTarget.get();
            if (target != null) {

            }
        }
    }

}
