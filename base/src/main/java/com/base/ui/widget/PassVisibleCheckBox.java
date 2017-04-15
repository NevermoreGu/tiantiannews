package com.base.ui.widget;

import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class PassVisibleCheckBox extends CheckBox {

    private EditText editText;

    public PassVisibleCheckBox(Context context) {
        super(context);
        initView();
    }

    public PassVisibleCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PassVisibleCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (editText == null) {
                    return;
                }
                if(isChecked){
                    //设置为明文显示
                    editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //设置为密文显示
                    editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                //光标在最后显示
                editText.setSelection(editText.length());
            }
        });
    }

    public void setPassVisible(EditText editText) {
        this.editText = editText;
    }

}
