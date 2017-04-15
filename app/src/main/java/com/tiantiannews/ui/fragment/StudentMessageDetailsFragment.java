package com.tiantiannews.ui.fragment;

import android.view.View;

import com.tiantiannews.R;
import com.tiantiannews.base.BaseFragment;

import butterknife.OnClick;

public class StudentMessageDetailsFragment extends BaseFragment {

    private OnFabAddClickListener mOnFabAddClickListener;

    public interface OnFabAddClickListener {
        void onclick();
    }

    public void setOnFabAddClickListener(OnFabAddClickListener onFabAddClickListener) {
        this.mOnFabAddClickListener = onFabAddClickListener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_student_message_details;
    }

    @Override
    public void initVariables() {

    }

    @Override
    public void initViews() {

    }

    @Override
    public void loadData() {

    }

    @OnClick({R.id.fab_edit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_edit:
                if (mOnFabAddClickListener != null) {
                    mOnFabAddClickListener.onclick();
                }
                break;
        }
    }
}
