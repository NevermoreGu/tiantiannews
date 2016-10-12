package com.tiantiannews.ui.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tiantiannews.R;
import com.tiantiannews.data.bean.ImageInfo;
import com.tiantiannews.utils.image.ImageLoader;
import com.tiantiannews.utils.image.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片选择Adapter
 */
public class ImageGridAdapter extends BaseAdapter {

    private static final int TYPE_CAMERA = 0;
    private static final int TYPE_NORMAL = 1;

    private Context mContext;

    private LayoutInflater mInflater;
    private boolean showCamera = true;

    private List<ImageInfo> mImages = new ArrayList<>();
    private List<ImageInfo> mSelectedImages = new ArrayList<>();

    private int mItemSize;
    private GridView.LayoutParams mItemLayoutParams;

    private int selectPosition;

    public interface OnCheckListener {
        void check(ImageInfo imageInfo);
    }

    private OnCheckListener listener;

    public void setOnCheckListener(OnCheckListener listener) {
        this.listener = listener;
    }

    public ImageGridAdapter(Context context, boolean showCamera) {
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.showCamera = showCamera;
        mItemLayoutParams = new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.MATCH_PARENT);
    }

    public void setShowCamera(boolean b) {
        if (showCamera == b) return;

        showCamera = b;
        notifyDataSetChanged();
    }

    public boolean isShowCamera() {
        return showCamera;
    }

    /**
     * 选择某个图片，改变选择状态
     *
     * @param imageInfo
     */
    public void select(ImageInfo imageInfo) {
        if (mSelectedImages.contains(imageInfo)) {
            mSelectedImages.remove(imageInfo);
        } else {
            mSelectedImages.add(imageInfo);
        }
        notifyDataSetChanged();
    }

    public void addOne(ImageInfo imageInfo) {
        if (mSelectedImages.contains(imageInfo)) {

        } else {
            mSelectedImages.add(imageInfo);
        }
        notifyDataSetChanged();
    }

    public void removeOne(ImageInfo imageInfo) {
        if (mSelectedImages.contains(imageInfo)) {
            mSelectedImages.remove(imageInfo);
        }
        notifyDataSetChanged();
    }

    public void removeOne(String path) {
        ImageInfo imageInfo = getImageByPath(path);
        if (mSelectedImages.contains(imageInfo)) {
            mSelectedImages.remove(imageInfo);
        }
        notifyDataSetChanged();
    }

    /**
     * 通过图片路径设置默认选择
     *
     * @param resultList
     */
    public void setSelectedList(ArrayList<String> resultList) {
        for (String path : resultList) {
            ImageInfo imageInfo = getImageByPath(path);
            if (imageInfo != null) {
                mSelectedImages.add(imageInfo);
            }
        }
        if (mSelectedImages.size() > 0) {
            notifyDataSetChanged();
        }
    }

    private ImageInfo getImageByPath(String path) {
        if (mImages != null && mImages.size() > 0) {
            for (ImageInfo imageInfo : mImages) {
                if (imageInfo.path.equalsIgnoreCase(path)) {
                    return imageInfo;
                }
            }
        }
        return null;
    }

    /**
     * 设置数据集
     *
     * @param imageInfos
     */
    public void setData(List<ImageInfo> imageInfos) {
        mSelectedImages.clear();

        if (imageInfos != null && imageInfos.size() > 0) {
            mImages = imageInfos;
        } else {
            mImages.clear();
        }
        notifyDataSetChanged();
    }

    public ArrayList<ImageInfo> getData() {
        return (ArrayList<ImageInfo>) mImages;
    }

    /**
     * 重置每个Column的Size
     *
     * @param columnWidth
     */
    public void setItemSize(int columnWidth) {

        if (mItemSize == columnWidth) {
            return;
        }

        mItemSize = columnWidth;

        mItemLayoutParams = new GridView.LayoutParams(mItemSize, mItemSize);

        notifyDataSetChanged();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (showCamera) {
            return position == 0 ? TYPE_CAMERA : TYPE_NORMAL;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getCount() {
        return showCamera ? mImages.size() + 1 : mImages.size();
    }

    @Override
    public ImageInfo getItem(int i) {
        if (showCamera) {
            if (i == 0) {
                return null;
            }
            return mImages.get(i - 1);
        } else {
            return mImages.get(i);
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        int type = getItemViewType(position);
        if (type == TYPE_CAMERA) {
            view = mInflater.inflate(R.layout.camerasdk_list_item_camera, viewGroup, false);

        } else if (type == TYPE_NORMAL) {
            ViewHolder holder;
            if (view == null || view.getTag() == null) {
                holder = new ViewHolder();
                view = mInflater.inflate(R.layout.camerasdk_list_item_image, viewGroup, false);
                holder.image = (ImageView) view.findViewById(R.id.image);
                holder.checkBox = (CheckBox) view.findViewById(R.id.checkmark);
                holder.mask = view.findViewById(R.id.mask);
                holder.llCheck = (LinearLayout) view.findViewById(R.id.ll_check);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            final ImageInfo data = getItem(position);
            if (data != null) {
                {
                    holder.llCheck.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listener != null) {
                                selectPosition = position;
                                listener.check(data);
                            }
                        }
                    });
                    //多选状态
                    holder.checkBox.setVisibility(View.VISIBLE);
                    if (mSelectedImages.contains(data)) {
                        // 设置选中状态
                        //indicator.setImageResource(R.drawable.camerasdk_checkbox_checked);
                        holder.checkBox.setChecked(true);
                        holder.mask.setVisibility(View.VISIBLE);
                        if (selectPosition == position) {
                            addAnimation(holder.checkBox);
                        }
                    } else {
                        // 未选择
                        //indicator.setImageResource(R.drawable.camerasdk_checkbox_normal);
                        holder.checkBox.setChecked(false);
                        holder.mask.setVisibility(View.GONE);
                    }

                    if (mItemSize > 0) {
//                image.setImageURI(Uri.parse("file://" + data.path));
                        ImageLoaderUtil imageLoaderUtil = ImageLoaderUtil.getInstance();
                        ImageLoader imageLoader = ImageLoader.builder().url(data.path).imgView(holder.image).placeHolder(R.drawable.default_img_bg).type(1).build();
                        imageLoaderUtil.loadImage(mContext, imageLoader);

                    }
                }
            }

        }

        /** Fixed View Size */
        GridView.LayoutParams lp = (GridView.LayoutParams) view.getLayoutParams();
        if (lp.height != mItemSize) {
            view.setLayoutParams(mItemLayoutParams);
        }

        return view;
    }


    /**
     * 给CheckBox加点击动画，利用开源库nineoldandroids设置动画
     *
     * @param view
     */
    private void addAnimation(View view) {
        float[] vaules = new float[]{0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f, 1.1f, 1.2f, 1.3f, 1.25f, 1.2f, 1.15f, 1.1f, 1.0f};
        AnimatorSet set = new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(view, "scaleX", vaules),
                ObjectAnimator.ofFloat(view, "scaleY", vaules));
        set.setDuration(150);
        set.start();
    }

    class ViewHolder {
        ImageView image;
        CheckBox checkBox;
        View mask;
        LinearLayout llCheck;
    }

}


