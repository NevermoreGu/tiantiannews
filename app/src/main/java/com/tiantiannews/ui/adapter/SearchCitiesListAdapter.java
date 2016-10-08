package com.tiantiannews.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tiantiannews.R;
import com.tiantiannews.data.bean.City;

import java.util.List;

public class SearchCitiesListAdapter extends BaseAdapter {

    private List<City> cities;
    private LayoutInflater inflater;
    private OnSelectCityLogListener listener;
    private TextView textView;
    private boolean visible;
    /**
     * 2种视图类型
     */
    private static final int TYPE_LIST = 0;
    private static final int TYPE_BOTTOM = 1;

    public SearchCitiesListAdapter(Context mContext, List<City> cities,boolean visible) {
        this.cities = cities;
        this.inflater = LayoutInflater.from(mContext);
        this.visible = visible;
    }

    public void updateAdapter(List<City> cities,boolean visible) {
        this.cities = cities;
        this.visible = visible;
        notifyDataSetChanged();
    }

    public interface OnSelectCityLogListener {
        public void selectCityLog(String cityLog);
    }

    public void setOnSelectCityLogListener(OnSelectCityLogListener listener) {
        this.listener = listener;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getCount() - 1) {
            return TYPE_BOTTOM;
        }
        return TYPE_LIST;
    }

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
            return cities.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        View view = convertView;
        if (getItemViewType(position) == TYPE_LIST) {
            if (view == null) {
                view = inflater.inflate(R.layout.item_search_all_city, parent, false);
                holder = new ViewHolder();
                holder.name = (TextView) view.findViewById(R.id.tv_city_name);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            if (cities != null) {
                City city = cities.get(position);
                holder.name.setText(city.name);
            }
        } else if (getItemViewType(position) == TYPE_BOTTOM) {
            if (view == null) {
                view = inflater.inflate(R.layout.item_search_log_info, parent, false);
                textView = (TextView) view.findViewById(R.id.tv_city_log);
            }
            //不可写在view=null中，view=null只是复用view，不可改变view属性
            if (visible){
                textView.setVisibility(View.VISIBLE);
                if (cities.size() == 0) {
                    textView.setText("没有搜索记录");
                } else {
                    textView.setText("清除搜索记录");
                }
            }else {
                textView.setVisibility(View.GONE);
            }
            final String text = textView.getText().toString();
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.selectCityLog(text);
                }
            });
        }
        return view;
    }

    static class ViewHolder {
        TextView name;
    }
}
