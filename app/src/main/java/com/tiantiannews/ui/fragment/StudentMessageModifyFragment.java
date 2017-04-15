package com.tiantiannews.ui.fragment;

import android.widget.TextView;

import com.tiantiannews.R;
import com.tiantiannews.base.BaseFragment;

import butterknife.BindView;


public class StudentMessageModifyFragment extends BaseFragment {

    public static final String KEY_NAME = "key_name";
    public static final String VALUE = "value";

    @BindView(R.id.tv_key)
    TextView tvKey; 
    @BindView(R.id.et_value)
    TextView etValue;

    private String mKey;
    private String mValue;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_student_message_modify;
    }

    @Override
    public void initVariables() {

    }

    @Override
    public void initViews() {
        tvKey.setText(mKey);
        etValue.setText(mValue);
    }

    @Override
    public void loadData() {

    }

    @Override
    protected void handlerIntent() {
        super.handlerIntent();
        if (getArguments() != null) {
            mKey = getArguments().getString(KEY_NAME);
            mValue = getArguments().getString(VALUE);
        }
    }
}
