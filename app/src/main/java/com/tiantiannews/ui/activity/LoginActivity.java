package com.tiantiannews.ui.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tiantiannews.R;
import com.tiantiannews.api.ApiParams;
import com.tiantiannews.base.BaseActivity;
import com.tiantiannews.base.BaseModel;
import com.tiantiannews.data.bean.UserRequest;
import com.tiantiannews.data.bean.UserResult;
import com.tiantiannews.ui.widget.DeleteEditText;
import com.tiantiannews.ui.widget.PassVisibleCheckBox;
import com.tiantiannews.utils.StringUtils;
import com.tiantiannews.utils.ToastUtils;
import com.tiantiannews.utils.ViewUtils;
import com.tiantiannews.utils.net.NetBuilder;
import com.tiantiannews.utils.net.NetCallBack;
import com.tiantiannews.utils.net.RetrofitNet;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

public class LoginActivity extends BaseActivity implements TextWatcher {

    @BindView(R.id.et_login_name)
    DeleteEditText etLoginName;
    @BindView(R.id.et_login_pass)
    DeleteEditText etLoginPass;
    @BindView(R.id.img_pass_visible)
    PassVisibleCheckBox imgPassVisible;
    @BindView(R.id.tv_login_forget_pass)
    TextView tvLoginForgetPass;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_login_register)
    TextView tvLoginRegister;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initVariables() {

    }

    @Override
    public void initViews() {

        etLoginName.addTextChangedListener(this);
        etLoginPass.addTextChangedListener(this);

        ViewUtils.addTouchColor(btnLogin, R.color.colorPrimaryDark, R.color.colorPrimary);
        imgPassVisible.setPassVisible(etLoginPass);
        btnLogin.setEnabled(false);
        btnLogin.setTextScaleX(1.2f);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String userName = etLoginName.getText().toString().trim();
        String userPass = etLoginPass.getText().toString().trim();
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userPass)) {
            btnLogin.setBackgroundColor(getResources().getColor(
                    R.color.colorPrimary));
            btnLogin.setEnabled(true);
        } else {
            btnLogin.setBackgroundColor(getResources().getColor(
                    R.color.white));
            btnLogin.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @OnClick({R.id.tv_login_forget_pass, R.id.btn_login, R.id.tv_login_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_forget_pass:
                break;
            case R.id.btn_login:
                String name = etLoginName.getText().toString().trim();
                String pass = etLoginPass.getText().toString().trim();
                if (StringUtils.isEmpty(name)) {
                    return;
                }
                if (StringUtils.isEmpty(pass)) {
                    return;
                }
                NetBuilder netBuilder = new NetBuilder.Builder().url("http://58.215.50.61:19080/").callback(new NetCallBack<BaseModel<UserResult>>() {

                    @Override
                    public void onResponse(BaseModel<UserResult> response) {
                        Toast.makeText(LoginActivity.this, response.resultNote, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onErrorResponse(String error) {
                        ToastUtils.makeText(mContext, "哈哈哈利的路上反馈", Toast.LENGTH_LONG);
                    }
                }).build();
                RetrofitNet retrofitNet = new RetrofitNet(netBuilder);
                UserRequest userRequest = ApiParams.getLoginParams(name, pass);
                Call userCall = retrofitNet.getService().login(userRequest);
                retrofitNet.addToRequestQueue(userCall);

                break;

            case R.id.tv_login_register:
                break;
            case R.id.img_pass_visible:
                break;
        }
    }
}
