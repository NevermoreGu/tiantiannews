package com.tiantiannews.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tiantiannews.R;
import com.tiantiannews.aidl.ImageInfo;

import java.util.List;

public class PictureBrowsePagerAdapter extends PagerAdapter {

    private List<ImageInfo> imageUrls;
    private Context mContext;

    public PictureBrowsePagerAdapter(List<ImageInfo> imageUrls, Context context) {
        this.mContext = context;
        this.imageUrls = imageUrls;
    }

    @Override
    public int getCount() {
        if (imageUrls != null) {
            return imageUrls.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pager_picture_browse, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.img_picture_browse);
        container.addView(view);
        Glide.with(mContext)
                .load(imageUrls.get(position).path)
                .placeholder(R.drawable.default_img_bg)
                .crossFade()
                .into(imageView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
