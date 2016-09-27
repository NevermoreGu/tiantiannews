package com.tiantiannews.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiantiannews.R;
import com.tiantiannews.data.bean.Explore;

import java.util.List;

public class ExploreAdapter extends BaseAdapter {


    private static final int TYPE_HEADER = 0;
    private static final int TYPE_LIST_ONE = 1;
    private static final int TYPE_LIST_TWO = 2;
    private static final int TYPE_FOOT = 3;

    private List<Explore> exploreList;

    public ExploreAdapter(List<Explore> exploreList) {
        this.exploreList = exploreList;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        int type = position % 4;
        if (type == 0) {
            return TYPE_HEADER;
        } else if (type == 1) {
            return TYPE_LIST_ONE;
        } else if (type == 2) {
            return TYPE_LIST_TWO;
        } else if (type == 3) {
            return TYPE_FOOT;
        }
        return 0;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Explore explore = exploreList.get(position);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (getItemViewType(position) == TYPE_HEADER) {
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_explore_head, parent, false);
                holder.imgHead = (ImageView) convertView.findViewById(R.id.nimg_item_explore_head);
                holder.tvName = (TextView) convertView.findViewById(R.id.tv_item_explore_name);
                holder.tvTime = (TextView) convertView.findViewById(R.id.tv_item_explore_time);
                holder.tvContent = (TextView) convertView.findViewById(R.id.tv_item_explore_content);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (explore != null && explore.getList() != null) {
                Explore.Lists lists = explore.getList();
                holder.tvName.setText(lists.getChatname() == null ? "" : lists.getChatname());
                holder.tvTime.setText(lists.getChattime() == null ? "" : lists.getChattime());
                holder.tvContent.setText(lists.getChatcontent() == null ? "" : lists.getChatcontent());
            }

        } else if (getItemViewType(position) == TYPE_LIST_ONE) {
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_explore_pics, parent, false);
                holder.gvPics = (GridView) convertView.findViewById(R.id.gv_item_explore_pics);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (explore != null && explore.getList() != null) {
                Explore.Lists lists = explore.getList();
            }

        } else if (getItemViewType(position) == TYPE_LIST_TWO) {

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_explore_reference_content, parent, false);
                holder.tvReferenceContent = (TextView) convertView.findViewById(R.id.tv_item_explore_reference_content);
                holder.gvReferencePics = (GridView) convertView.findViewById(R.id.gv_item_explore_reference_pics);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
        } else if (getItemViewType(position) == TYPE_FOOT) {
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_explore_foot, parent, false);
                holder.tvFoot = (TextView) convertView.findViewById(R.id.tv_item_explore_foot);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (explore != null && explore.getDiscuss() != null) {
                List<Explore.Discuss> discusses = explore.getDiscuss();

            }
        }

        return null;
    }

    static class ViewHolder {
        //head
        ImageView imgHead;
        TextView tvName;
        TextView tvTime;
        TextView tvContent;
        //pics
        GridView gvPics;
        //referenceContent
        TextView tvReferenceContent;
        GridView gvReferencePics;
        //foot
        TextView tvFoot;
    }
}
