package com.tiantiannews.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiantiannews.R;
import com.tiantiannews.data.bean.ListPageInfo;
import com.tiantiannews.utils.StringUtils;
import com.tiantiannews.utils.image.ImageLoader;
import com.tiantiannews.utils.image.ImageLoaderUtil;


public class BaseListAdapter<T extends BaseModel> extends BaseAdapter {

    public static final int STATE_EMPTY_ITEM = 0;
    public static final int STATE_LOAD_MORE = 1;
    public static final int STATE_NO_MORE = 2;
    public static final int STATE_NO_DATA = 3;
    public static final int STATE_LESS_ONE_PAGE = 4;
    public static final int STATE_NETWORK_ERROR = 5;

    protected int state = STATE_LESS_ONE_PAGE;

    private LayoutInflater mInflater;

    protected ListPageInfo<T> mListPageInfo;

    //adapter数据源
    public void setListPageInfo(ListPageInfo<T> listPageInfo) {
        mListPageInfo = listPageInfo;
    }

    public ListPageInfo<T> getListPageInfo() {
        return mListPageInfo;
    }

    protected LayoutInflater getLayoutInflater(Context context) {
        if (mInflater == null) {
            mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        return mInflater;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }

    @Override
    public int getCount() {
        if (mListPageInfo == null) {
            return 0;
        }
        return mListPageInfo.getListCount();
    }

    @Override
    public T getItem(int position) {
        if (mListPageInfo == null) {
            return null;
        }
        return mListPageInfo.getItem(position);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return getRealView(position, convertView, parent);
    }

    protected View getRealView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    protected void setText(TextView textView, String text, boolean needGone) {
        if (needGone) {
            textView.setVisibility(View.GONE);
            return;
        }
        if (StringUtils.isEmpty(text)) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText(text);
        }
    }

    protected void setText(TextView textView, String text) {
        setText(textView, text, false);
    }

    protected void setImage(ImageView imageView, String imageUrl, Context context) {
        ImageLoaderUtil imageLoaderUtil = ImageLoaderUtil.getInstance();
        ImageLoader imageLoader = ImageLoader.builder().url(imageUrl).imgView(imageView).placeHolder(R.drawable.default_img_bg).type(1).build();
        imageLoaderUtil.loadImage(context, imageLoader);
    }

}
