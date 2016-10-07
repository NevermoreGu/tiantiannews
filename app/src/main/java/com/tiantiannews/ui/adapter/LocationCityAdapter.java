package com.tiantiannews.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tiantiannews.R;
import com.tiantiannews.data.bean.City;
import com.tiantiannews.utils.StringUtils;

import java.util.List;

public class LocationCityAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<City> cities;

    public LocationCityAdapter(Context context, List<City> cities) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        this.cities = cities;
    }

    @Override
    public int getCount() {
        if (cities != null) {
            return cities.size();
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
        final ViewHolder holder;
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_location_city, parent, false);
            holder = new ViewHolder();
            holder.tvCity = (TextView) view.findViewById(R.id.tv_item_location_city);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        City city = cities.get(position);
        if (city != null) {
            holder.tvCity.setText(StringUtils.checkNull(cities.get(position).name));
        }
        return view;
    }

    static class ViewHolder {
        TextView tvCity;
    }
}
