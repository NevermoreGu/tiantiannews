package com.tiantiannews.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiantiannews.R;
import com.tiantiannews.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class StudentMessageAddFragment extends BaseFragment {

    public static final String HEADICON = "head_icon";

    @BindView(R.id.tv_student_message_add_name)
    TextView tvName;
    @BindView(R.id.img_student_message_add_sex)
    ImageView imgSex;
    @BindView(R.id.tv_student_message_add_class)
    TextView tvClass;
    @BindView(R.id.tv_student_message_add_home_address)
    TextView tvHomeAddress;
    @BindView(R.id.tv_student_message_add_father_name)
    TextView tvFatherName;
    @BindView(R.id.tv_student_message_add_father_phone)
    TextView tvFatherPhone;
    @BindView(R.id.tv_student_message_add_mother_name)
    TextView tvMotherName;
    @BindView(R.id.tv_student_message_add_mother_phone)
    TextView tvMotherPhone;
    @BindView(R.id.tv_student_message_add_home_mobile)
    TextView tvHomeMobile;

    private OnModifyClickListener mOnModifyClickListener;

    public interface OnModifyClickListener {
        void onclick(String type, String key, String content);
    }

    public void setOnModifyClickListener(OnModifyClickListener onModifyClickListener) {
        this.mOnModifyClickListener = onModifyClickListener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_student_message_add;
    }

    @Override
    protected void initAppBar() {
        super.initAppBar();
        appBar.setAppBarTitle("个人信息");
    }

    @Override
    public void initVariables() {

    }

    @Override
    public void initViews() {
        initAppBar();
    }

    @Override
    public void loadData() {

    }

    @OnClick({R.id.rl_student_message_add_head_icon, R.id.ll_student_message_add_background, R.id.ll_student_message_add_name, R.id.ll_student_message_add_sex
            , R.id.rl_student_message_add_class, R.id.rl_student_message_add_home_address, R.id.rl_student_message_add_father_name, R.id.rl_student_message_add_father_phone
            , R.id.rl_student_message_add_mother_name, R.id.rl_student_message_add_mother_phone, R.id.rl_student_message_add_home_mobile})
    public void onClick(View view) {
        if (mOnModifyClickListener != null) {
            switch (view.getId()) {
                case R.id.rl_student_message_add_head_icon:
                    mOnModifyClickListener.onclick("img", getString(R.string.head_icon), "");
                    break;
                case R.id.ll_student_message_add_background:
                    mOnModifyClickListener.onclick("img", getString(R.string.background), "");
                    break;
                case R.id.ll_student_message_add_name:
                    mOnModifyClickListener.onclick("text", getString(R.string.name), tvName.getText().toString().trim());
                    break;
                case R.id.ll_student_message_add_sex:
                    mOnModifyClickListener.onclick("sex", getString(R.string.sex),"1");
                    break;
                case R.id.rl_student_message_add_class:
                    mOnModifyClickListener.onclick("text", getString(R.string.class_name), tvClass.getText().toString().trim());
                    break;
                case R.id.rl_student_message_add_home_address:
                    mOnModifyClickListener.onclick("text", getString(R.string.home_adress), tvHomeAddress.getText().toString().trim());
                    break;
                case R.id.rl_student_message_add_father_name:
                    mOnModifyClickListener.onclick("text", getString(R.string.father_name), tvFatherName.getText().toString().trim());
                    break;
                case R.id.rl_student_message_add_father_phone:
                    mOnModifyClickListener.onclick("text", getString(R.string.father_phone), tvFatherPhone.getText().toString().trim());
                    break;
                case R.id.rl_student_message_add_mother_name:
                    mOnModifyClickListener.onclick("text", getString(R.string.mother_name), tvMotherName.getText().toString().trim());
                    break;
                case R.id.rl_student_message_add_mother_phone:
                    mOnModifyClickListener.onclick("text", getString(R.string.mother_phone), tvMotherPhone.getText().toString().trim());
                    break;
                case R.id.rl_student_message_add_home_mobile:
                    mOnModifyClickListener.onclick("text", getString(R.string.home_mobile), tvHomeMobile.getText().toString().trim());
                    break;
            }
        }

    }
}
