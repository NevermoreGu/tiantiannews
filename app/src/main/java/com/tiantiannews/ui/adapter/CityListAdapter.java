package com.tiantiannews.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.base.ui.widget.BaseGridView;
import com.tiantiannews.R;
import com.tiantiannews.data.bean.City;

import java.util.ArrayList;
import java.util.List;

public class CityListAdapter extends BaseAdapter implements SectionIndexer {
    private Context context;
    private List<City> cities;
    private String locationCity;
    private OnSelectCityListener listener;
    private LayoutInflater inflater;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_LIST = 1;

    public CityListAdapter(Context context, List<City> cities, String locationCity) {
        this.context = context;
        this.cities = cities;
        this.locationCity = locationCity;
        inflater = LayoutInflater.from(context);
    }

    public void updateAdapter(List<City> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }

    public interface OnSelectCityListener {
        void selectCity(String cityName);
    }

    public void setOnSelectCityListener(OnSelectCityListener listener) {
        this.listener = listener;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_LIST;
    }

    /**
     * 返回的count必须包括head部分
     *
     * @return
     */
    @Override
    public int getCount() {
        if (cities != null) {
            return cities.size() + 1;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (cities != null) {
            return cities.get(position - 1);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view = convertView;

        if (getItemViewType(position) == TYPE_HEADER) {
            if (view == null) {
                view = inflater.inflate(R.layout.item_city_list_head, parent, false);
                TextView tvCurrentCity = (TextView) view.findViewById(R.id.tv_item_city_list_current_city);
                TextView tvHistoryHint = (TextView) view.findViewById(R.id.tv_item_city_list_history_hint);
                BaseGridView gvLocationCity = (BaseGridView) view.findViewById(R.id.gv_item_city_list_location);
                BaseGridView gvHistoryCity = (BaseGridView) view.findViewById(R.id.gv_item_city_list_history);
                BaseGridView gvHotCity = (BaseGridView) view.findViewById(R.id.gv_item_city_list_hot);
                tvHistoryHint.setVisibility(View.GONE);
                gvHistoryCity.setVisibility(View.GONE);

                City city = new City(1, "南京", "nanjing");
                List<City> location = new ArrayList<>();
                location.add(city);
                gvLocationCity.setAdapter(new LocationCityAdapter(context, location));
                gvLocationCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
                List<City> cities = new ArrayList<City>();
                cities.add(new City("北京"));
                cities.add(new City("上海"));
                cities.add(new City("广州"));
                cities.add(new City("深圳"));
                cities.add(new City("杭州"));
                cities.add(new City("南京"));
                cities.add(new City("天津"));
                cities.add(new City("武汉"));
                cities.add(new City("重庆"));
                gvHotCity.setAdapter(new LocationCityAdapter(context, cities));
                gvHotCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
            }
        } else if (getItemViewType(position) == TYPE_LIST) {
            City city = cities.get(position - 1);
            if (view == null) {
                view = inflater.inflate(R.layout.item_all_city, parent, false);
                holder = new ViewHolder();
                holder.alpha = (TextView) view.findViewById(R.id.tv_all_city_alpha);
                holder.name = (TextView) view.findViewById(R.id.tv_all_city_name);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            int section = getSectionForPosition(position - 1);
            if (getPositionForSection(section) == (position - 1)) {
                holder.alpha.setVisibility(View.VISIBLE);
                holder.alpha.setText(city.letter);
            } else {
                holder.alpha.setVisibility(View.GONE);
            }
            holder.name.setText(city.name);
        }
        return view;
    }


    @Override
    public Object[] getSections() {
        return null;
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < cities.size(); i++) {
            String letter = cities.get(i).letter;
            char firstChar = letter.toUpperCase().charAt(0);
            if (firstChar == sectionIndex) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    @Override
    public int getSectionForPosition(int position) {
        return cities.get(position).letter.charAt(0);
    }

    static class ViewHolder {
        TextView alpha;
        TextView name;
    }
}
