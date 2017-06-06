package com.tiantiannews.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.base.ui.widget.CircleImageView;
import com.base.ui.widget.dialog.CircularLoadingDialog;
import com.base.ui.widget.progressbar.CircularLoadingProgressBar;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tiantiannews.R;
import com.tiantiannews.base.CheckPermissionsActivity;
import com.tiantiannews.data.bean.MaterialSimpleListItem;
import com.tiantiannews.utils.image.ImageLoader;
import com.tiantiannews.utils.image.ImageLoaderUtil;
import com.utils.ActivityUtils;
import com.utils.FileUtils;
import com.utils.camera.CameraUtils;
import com.utils.img.Luban.Luban;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by guqian on 2017/5/22.
 */

public class PersonInfoActivity extends CheckPermissionsActivity {

    @BindView(R.id.c_img_person_info_head_icon)
    CircleImageView cImgPersonInfoHeadIcon;

    private List<MaterialSimpleListItem> items = new ArrayList<>();
    private QuickAdapter mQuickAdapter;
    private File mTakeCameraFile;
    private String mIconPath;
    private MaterialDialog mMaterialDialog;

    @Override
    public void initViews() {

    }

    @Override
    public void loadData() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_info;
    }

    @OnClick({R.id.c_img_person_info_head_icon, R.id.ll_person_info_head_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.c_img_person_info_head_icon:
                if (!TextUtils.isEmpty(mIconPath)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("path", mIconPath);
                    ActivityUtils.openActivity(mContext, HeadIconActivity.class, bundle);
                }
                break;
            case R.id.ll_person_info_head_icon:
                items.clear();
                mQuickAdapter = new QuickAdapter();
                mQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        if (mMaterialDialog != null && mMaterialDialog.isShowing()) {
                            mMaterialDialog.dismiss();
                        }
                        if (position == 0) {

                        } else if (position == 1) {
                            cameraTask();
                        } else if (position == 2) {

                        }
                    }
                });
                mQuickAdapter.addData(new MaterialSimpleListItem.Builder(this)
                        .content("查看大图")
                        .icon(R.drawable.zl)
                        .backgroundColor(Color.WHITE)
                        .build());
                mQuickAdapter.addData(new MaterialSimpleListItem.Builder(this)
                        .content("照相")
                        .icon(R.drawable.zk)
                        .backgroundColor(Color.WHITE)
                        .build());
                mQuickAdapter.addData(new MaterialSimpleListItem.Builder(this)
                        .content("图片库")
                        .icon(R.drawable.zl)
                        .backgroundColor(Color.WHITE)
                        .build());
                mMaterialDialog = new MaterialDialog.Builder(this)
                        .title("设置您的靓照")
                        .adapter(mQuickAdapter, null)
                        .show();
                break;
        }
    }


    private class QuickAdapter extends BaseQuickAdapter<MaterialSimpleListItem, BaseViewHolder> {

        public QuickAdapter() {
            super(R.layout.md_simplelist_item, items);
        }

        @Override
        protected void convert(BaseViewHolder viewHolder, MaterialSimpleListItem item) {
            viewHolder.setImageDrawable(R.id.icon, item.getIcon())
                    .setText(R.id.title, item.getContent());
        }
    }

    @AfterPermissionGranted(CameraUtils.RC_CAMERA_PERM)
    public void cameraTask() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            mTakeCameraFile = CameraUtils.showCameraAction(mContext);
        } else {
            EasyPermissions.requestPermissions(this, "",
                    CameraUtils.RC_CAMERA_PERM, Manifest.permission.CAMERA);
        }
    }

    @Override
    protected void PermissionsDenied(int requestCode, List<String> perms) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (requestCode == CameraUtils.TAKE_PICTURE_FROM_CAMERA &&
                resultCode == RESULT_OK) {
            if (mTakeCameraFile != null) {
                final CircularLoadingProgressBar progressBar = new CircularLoadingProgressBar(mContext);
//                ViewGroup.LayoutParams lp = progressBar.getLayoutParams();
//                lp.width = 40;
//                lp.height = 40;
//                progressBar.setLayoutParams(lp);

                CircularLoadingDialog.Builder builder = new CircularLoadingDialog.Builder(mContext)
                        .setMessage("正在加载").setContentView(progressBar);
                final CircularLoadingDialog dialog = builder.build();
                dialog.show();
                progressBar.startAnim();
                Luban.get(this)
                        .setFile(FileUtils.getDiskCachePicture(mContext))
                        .load(mTakeCameraFile)
                        .putGear(Luban.THIRD_GEAR)
                        .asObservable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        })
                        .onErrorResumeNext(new Func1<Throwable, Observable<? extends File>>() {
                            @Override
                            public Observable<? extends File> call(Throwable throwable) {
                                return Observable.empty();
                            }
                        })
                        .subscribe(new Action1<File>() {
                            @Override
                            public void call(File file) {
                                if (file != null) {
                                    if (dialog != null && dialog.isShowing()) {
                                        progressBar.stopAnim();
                                        dialog.dismiss();
                                    }
                                    mIconPath = file.getAbsolutePath();
                                    ImageLoaderUtil imageLoaderUtil = ImageLoaderUtil.getInstance();
                                    ImageLoader imageLoader = ImageLoader.builder().url(mIconPath).imgView(cImgPersonInfoHeadIcon).placeHolder(R.drawable.ic_man).type(1).build();
                                    imageLoaderUtil.loadImage(mContext, imageLoader);
                                }
                            }
                        });
            }
        }
    }
}
