package com.tiantiannews.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tiantiannews.R;
import com.tiantiannews.base.BaseActivity;
import com.tiantiannews.base.Constants;
import com.tiantiannews.data.bean.FolderInfo;
import com.tiantiannews.data.bean.ImageInfo;
import com.tiantiannews.data.bean.SelectPicturesInfo;
import com.tiantiannews.ui.adapter.FolderAdapter;
import com.tiantiannews.ui.adapter.ImageGridAdapter;
import com.tiantiannews.utils.ActivityUtils;
import com.tiantiannews.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SelectPicturesActivity extends BaseActivity {

    @BindView(R.id.navigation)
    View toolBar;
    @BindView(R.id.rl_navigation_left)
    RelativeLayout tvToolBarLeft;
    @BindView(R.id.rl_navigation_right)
    RelativeLayout tvToolBarRight;
    @BindView(R.id.tv_navigation)
    TextView tvToolBarTitle;
    @BindView(R.id.gv_select_pictures)
    GridView mGridView;

    private SelectPicturesInfo mSelectPicturesInfo = new SelectPicturesInfo();
    private ArrayList<String> resultList = new ArrayList<>();// 结果数据
    private ArrayList<FolderInfo> mResultFolder = new ArrayList<>();// 文件夹数据

    // 不同loader定义
    private static final int LOADER_ALL = 0;
    private static final int LOADER_CATEGORY = 1;

    private PopupWindow mpopupWindow;
    private ImageGridAdapter mImageAdapter;
    private FolderAdapter mFolderAdapter;
    private boolean hasFolderGened = false;
    private File mTmpFile;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_pictures;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initExtra();
        getSupportLoaderManager().restartLoader(LOADER_ALL, null, mLoaderCallback);
    }

    @Override
    public void initViews() {
//        if (resultList.size() > 0) {
//            tvToolBarRight.setText("完成(" + resultList.size() + "/" + Constants.MAXSELECTPICTURES + ")");
//            tvToolBarRight.setBackgroundResource(R.drawable.bg_select_compete);
//        } else {
//            tvToolBarRight.setText("完成(0/" + Constants.MAXSELECTPICTURES + ")");
//            tvToolBarRight.setBackgroundResource(R.drawable.bg_select_uncompete);
//        }
        tvToolBarTitle.setText(getResources().getText(R.string.select_picture_album_all));
        Drawable drawable = getResources().getDrawable(R.drawable.message_popover_arrow);
        drawable.setBounds(10, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvToolBarTitle.setCompoundDrawables(null, null, drawable, null);
        tvToolBarLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mImageAdapter = new ImageGridAdapter(this, mSelectPicturesInfo.isShow_camera());
        mGridView.setAdapter(mImageAdapter);
        mFolderAdapter = new FolderAdapter(this);
    }

    @Override
    public void loadData() {
        tvToolBarTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupFolder(view);
            }
        });
        tvToolBarRight.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (resultList.size() > 0) {
                    selectComplate();
                }
            }
        });
        mGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int state) {

//                final Picasso picasso = Picasso.with(SelectPicturesActivity.this);
//                if (state == SCROLL_STATE_IDLE || state == SCROLL_STATE_TOUCH_SCROLL) {
//                    picasso.resumeTag(SelectPicturesActivity.this);
//                } else {
//                    picasso.pauseTag(SelectPicturesActivity.this);
//                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
        mGridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onGlobalLayout() {

                final int width = mGridView.getWidth();
                final int height = mGridView.getHeight();
                // mGridWidth = width;
                // mGridHeight = height;
                final int desireSize = getResources().getDimensionPixelOffset(R.dimen.offset_100);
                final int numCount = width / desireSize;
                final int columnSpace = getResources().getDimensionPixelOffset(R.dimen.offset_2);
                int columnWidth = (width - columnSpace * (numCount - 1)) / numCount;
                mImageAdapter.setItemSize(columnWidth);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    mGridView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });

        mGridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (mImageAdapter.isShowCamera()) {
                    if (i == 0) {
                        if (Constants.MAXSELECTPICTURES == resultList.size()) {
                            Toast.makeText(SelectPicturesActivity.this, R.string.select_picture_msg_amount_limit, Toast.LENGTH_SHORT).show();
                        } else {
                            showCameraAction();
                        }
                        return;
                    } else {
                        showPicturesBrowse(i);
                    }
                }
            }
        });
        mImageAdapter.setOnCheckListener(new ImageGridAdapter.OnCheckListener() {
            @Override
            public void check(ImageInfo imageInfo) {
                selectImageFromGrid(imageInfo);
            }
        });
    }


    //获取传过来的参数
    private void initExtra() {

        Intent intent = getIntent();
        try {
            mSelectPicturesInfo = (SelectPicturesInfo) intent.getSerializableExtra(SelectPicturesInfo.EXTRA_PARAMETER);
            resultList = mSelectPicturesInfo.getImage_list();
        } catch (Exception e) {

        }

    }

    /**
     * 选择相机
     */
    private void showCameraAction() {
        // 跳转到系统照相机
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            // 设置系统相机拍照后的输出路径
            // 创建临时文件
            mTmpFile = FileUtils.createTmpFile(this);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTmpFile));
            startActivityForResult(cameraIntent, SelectPicturesInfo.TAKE_PICTURE_FROM_CAMERA);
        } else {
            Toast.makeText(this, R.string.select_picture_msg_no_camera, Toast.LENGTH_SHORT).show();
        }
    }

    private void showPicturesBrowse(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putParcelableArrayList("pictures", mImageAdapter.getData());
        ActivityUtils.openActivity(this, PicturesBrowseActivity.class, bundle);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 相机拍照完成后，返回图片路径
        if (requestCode == SelectPicturesInfo.TAKE_PICTURE_FROM_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                if (mTmpFile != null) {
                    resultList.add(mTmpFile.getPath());
                    selectComplate();
                }
            } else {
                if (mTmpFile != null && mTmpFile.exists()) {
                    mTmpFile.delete();
                }
            }
        }
    }


    /**
     * 选择图片操作
     *
     * @param imageInfo
     */
    private void selectImageFromGrid(ImageInfo imageInfo) {
        if (imageInfo != null) {
            if (resultList.contains(imageInfo.path)) {
                resultList.remove(imageInfo.path);
            } else {
                // 判断选择数量问题
                if (Constants.MAXSELECTPICTURES == resultList.size()) {
                    Toast.makeText(this, R.string.select_picture_msg_amount_limit, Toast.LENGTH_SHORT).show();
                    return;
                }
                resultList.add(imageInfo.path);

            }
//            if (resultList.size() > 0) {
//                tvToolBarRight.setText("完成(" + resultList.size() + "/" + Constants.MAXSELECTPICTURES + ")");
//                tvToolBarRight.setBackgroundResource(R.drawable.bg_select_compete);
//            } else {
//                tvToolBarRight.setText("完成(0/" + Constants.MAXSELECTPICTURES + ")");
//                tvToolBarRight.setBackgroundResource(R.drawable.bg_select_uncompete);
//            }
            mImageAdapter.select(imageInfo);
        }
    }

    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallback = new LoaderManager.LoaderCallbacks<Cursor>() {

        private final String[] IMAGE_PROJECTION = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.SIZE};

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            if (id == LOADER_ALL) {
                CursorLoader cursorLoader = new CursorLoader(SelectPicturesActivity.this,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION,
                        null, null, IMAGE_PROJECTION[2] + " DESC");
                return cursorLoader;
            } else if (id == LOADER_CATEGORY) {
                CursorLoader cursorLoader = new CursorLoader(SelectPicturesActivity.this,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION,
                        IMAGE_PROJECTION[0] + " like '%" + args.getString("path") + "%'", null, IMAGE_PROJECTION[2] + " DESC");
                return cursorLoader;
            }

            return null;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            if (data != null) {

                List<ImageInfo> imageInfos = new ArrayList<ImageInfo>();
                int count = data.getCount();
                if (count > 0) {
                    data.moveToFirst();
                    do {

                        String path = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
                        String name = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));
                        long dateTime = data.getLong(data.getColumnIndexOrThrow(IMAGE_PROJECTION[2]));
                        int size = data.getInt(data.getColumnIndexOrThrow(IMAGE_PROJECTION[4]));
                        boolean show_flag = size > 1024 * 10; //是否大于10K
                        ImageInfo imageInfo = new ImageInfo(path, name, dateTime);
                        if (show_flag) {
                            imageInfos.add(imageInfo);
                        }

                        if (!hasFolderGened && show_flag) {
                            // 获取文件夹名称
                            File imageFile = new File(path);
                            File folderFile = imageFile.getParentFile();
                            FolderInfo folderInfo = new FolderInfo();
                            folderInfo.name = folderFile.getName();
                            folderInfo.path = folderFile.getAbsolutePath();
                            folderInfo.cover = imageInfo;
                            if (!mResultFolder.contains(folderInfo)) {
                                List<ImageInfo> imageList = new ArrayList<ImageInfo>();
                                imageList.add(imageInfo);
                                folderInfo.imageInfos = imageList;
                                mResultFolder.add(folderInfo);
                            } else {
                                // 更新
                                FolderInfo f = mResultFolder.get(mResultFolder.indexOf(folderInfo));
                                f.imageInfos.add(imageInfo);
                            }
                        }

                    } while (data.moveToNext());

                    mImageAdapter.setData(imageInfos);

                    // 设定默认选择
                    if (resultList != null && resultList.size() > 0) {
                        mImageAdapter.setSelectedList(resultList);
                    }

                    mFolderAdapter.setData(mResultFolder);
                    hasFolderGened = true;

                }
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };

    /**
     * 创建弹出的文件夹ListView
     */
    private void showPopupFolder(View v) {

        View view = getLayoutInflater().inflate(R.layout.camerasdk_popup_folder, null);
        LinearLayout ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
        ll_popup.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.camerasdk_push_up_in));

        ListView lsv_folder = (ListView) view.findViewById(R.id.lsv_folder);
        lsv_folder.setAdapter(mFolderAdapter);
        //if(mpopupWindow==null){

        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        mpopupWindow = new PopupWindow(this);
        mpopupWindow.setWidth(LayoutParams.MATCH_PARENT);
        mpopupWindow.setHeight(LayoutParams.MATCH_PARENT);

        mpopupWindow.setFocusable(true);
        mpopupWindow.setOutsideTouchable(true);
        //}
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mpopupWindow.dismiss();
            }
        });
        lsv_folder.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                mFolderAdapter.setSelectIndex(arg2);
                final int index = arg2;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mpopupWindow.dismiss();
                        if (index == 0) {
                            getSupportLoaderManager().restartLoader(LOADER_ALL, null, mLoaderCallback);
                            tvToolBarTitle.setText(R.string.select_picture_album_all);
                            mImageAdapter.setShowCamera(mSelectPicturesInfo.isShow_camera());
                        } else {
                            FolderInfo folderInfo = (FolderInfo) mFolderAdapter.getItem(index);
                            if (null != folderInfo) {
                                mImageAdapter.setData(folderInfo.imageInfos);
                                tvToolBarTitle.setText(folderInfo.name);
                                // 设定默认选择
                                if (resultList != null && resultList.size() > 0) {
                                    mImageAdapter.setSelectedList(resultList);
                                }
                            }
                            // mImageAdapter.setShowCamera(false);
                        }
                        // 滑动到最初始位置
                        mGridView.smoothScrollToPosition(0);

                    }
                }, 100);
            }
        });
        mpopupWindow.setContentView(view);
        mpopupWindow.setBackgroundDrawable(new ColorDrawable(0xb0000000));
        mpopupWindow.showAsDropDown(toolBar);
    }


    //选择完成实现跳转
    private void selectComplate() {

        mSelectPicturesInfo.setImage_list(resultList);
        Bundle b = new Bundle();
        b.putSerializable(SelectPicturesInfo.EXTRA_PARAMETER, mSelectPicturesInfo);

        Intent intent = new Intent();
        intent.putExtras(b);

        setResult(RESULT_OK, intent);
        finish();

    }

    //返回裁剪后的图片
    public void getForResultComplate(String path) {

        ArrayList<String> list = new ArrayList<String>();
        list.add(path);

        Intent intent = new Intent();
        mSelectPicturesInfo.setImage_list(list);
        Bundle b = new Bundle();
        b.putSerializable(SelectPicturesInfo.EXTRA_PARAMETER, mSelectPicturesInfo);
        intent.putExtras(b);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void initVariables() {

    }

}
