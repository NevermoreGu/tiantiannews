//package com.tiantiannews.base;
//
//import android.content.Context;
//import android.net.Uri;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.tiantiannews.R;
//import com.tiantiannews.utils.StringUtils;
//import com.tiantiannews.utils.TDevice;
//
//
//public class BaseListAdapter<T extends BaseModel> extends BaseAdapter {
//
//    public static final int STATE_EMPTY_ITEM = 0;
//    public static final int STATE_LOAD_MORE = 1;
//    public static final int STATE_NO_MORE = 2;
//    public static final int STATE_NO_DATA = 3;
//    public static final int STATE_LESS_ONE_PAGE = 4;
//    public static final int STATE_NETWORK_ERROR = 5;
//
//    protected int state = STATE_LESS_ONE_PAGE;
//
//    protected int mScreenWidth;
//
//    private LayoutInflater mInflater;
//
//    protected ListPageInfo<T> mListPageInfo;
//
//    public void setListPageInfo(ListPageInfo<T> listPageInfo) {
//        mListPageInfo = listPageInfo;
//    }
//
//    public ListPageInfo<T> getListPageInfo() {
//        return mListPageInfo;
//    }
//
//    protected LayoutInflater getLayoutInflater(Context context) {
//        if (mInflater == null) {
//            mInflater = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }
//        return mInflater;
//    }
//
//    public void setScreenWidth(int width) {
//        mScreenWidth = width;
//    }
//
//    public void setState(int state) {
//        this.state = state;
//    }
//
//    public int getState() {
//        return this.state;
//    }
//
//    @Override
//    public int getCount() {
//        if (mListPageInfo == null) {
//            return 0;
//        }
//        return mListPageInfo.getListLength();
//    }
//
//    @Override
//    public T getItem(int position) {
//        if (mListPageInfo == null) {
//            return null;
//        }
//        return mListPageInfo.getItem(position);
//    }
//
//    @Override
//    public long getItemId(int arg0) {
//        return arg0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        return getRealView(position, convertView, parent);
//    }
//
//    protected View getRealView(int position, View convertView, ViewGroup parent) {
//        return null;
//    }
//
//    protected void setText(TextView textView, String text, boolean needGone) {
//        if (needGone) {
//            textView.setVisibility(View.GONE);
//            return;
//        }
//        if (StringUtils.isEmpty(text)) {
//            textView.setVisibility(View.GONE);
//        } else {
//            textView.setText(text);
//        }
//    }
//
//    protected void setText(TextView textView, String text) {
//        setText(textView, text, false);
//    }
//
//    protected void setImage(NetworkImageView view, String imageUrl, Context context) {
//        view.setImageUrl(imageUrl,
//                VolleyUtils.getInstance(context).getImageLoader());
//    }
//
//    protected void setWifiDraweeImage(SimpleDraweeView view, String imageUrl) {
//        if (Constants.CheckWifi) {
//            if (TDevice.isWifiOpen()) {
//                setDraweeImage(view, imageUrl);
//            }
//        } else {
//            setDraweeImage(view, imageUrl);
//        }
//    }
//
//    protected void setDraweeImage(SimpleDraweeView view, String imageUrl) {
//        String url = "";
//        if (imageUrl == null) {
//
//        } else {
//            url = imageUrl;
//        }
//        Uri uri = Uri.parse(url);
//        view.setImageURI(uri);
//    }
//
//    protected void setDraweeImage(SimpleDraweeView view, String imageUrl, Context context) {
//        String url = "";
//        if (imageUrl == null) {
//
//        } else {
//            url = imageUrl;
//        }
//        Uri uri = Uri.parse(url);
//        view.setImageURI(uri);
//        view.setHierarchy(getHierarchy(context));
//    }
//
//    protected GenericDraweeHierarchy getHierarchy(Context context) {
//        GenericDraweeHierarchyBuilder builder =
//                new GenericDraweeHierarchyBuilder(context.getResources());
//        GenericDraweeHierarchy hierarchy = builder.setPlaceholderImage(context.getResources().getDrawable(R.drawable.default_img_bg))
//                .build();
//        return hierarchy;
//    }
//
//}
