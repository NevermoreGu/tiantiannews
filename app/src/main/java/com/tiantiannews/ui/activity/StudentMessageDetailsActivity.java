package com.tiantiannews.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;

import com.tiantiannews.R;
import com.tiantiannews.base.BaseActivity;
import com.tiantiannews.ui.fragment.StudentMessageAddFragment;
import com.tiantiannews.ui.fragment.StudentMessageDetailsFragment;
import com.tiantiannews.ui.fragment.StudentMessageModifyFragment;

public class StudentMessageDetailsActivity extends BaseActivity {

    private AlertDialog mSexAlertDialog;

    private StudentMessageDetailsFragment mStudentMessageDetailsFragment;
    private StudentMessageAddFragment mStudentMessageAddFragment;
    private StudentMessageModifyFragment mStudentMessageModifyFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_student_message_details;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void loadData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mStudentMessageDetailsFragment = new StudentMessageDetailsFragment();
            mStudentMessageAddFragment = new StudentMessageAddFragment();
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fl_students_message_details, mStudentMessageDetailsFragment);
            fragmentTransaction.commit();
        } else {
            Fragment fragment = getVisibleFragment();
            if (fragment != null) {
                if (fragment instanceof StudentMessageDetailsFragment) {
                    mStudentMessageDetailsFragment = (StudentMessageDetailsFragment) fragment;
                    mStudentMessageAddFragment = new StudentMessageAddFragment();
                } else if (fragment instanceof StudentMessageAddFragment) {
                    mStudentMessageAddFragment = (StudentMessageAddFragment) fragment;
                    mStudentMessageDetailsFragment = new StudentMessageDetailsFragment();
                }else if (fragment instanceof StudentMessageModifyFragment) {
                    mStudentMessageModifyFragment = (StudentMessageModifyFragment) fragment;
                }
            }
        }
        mStudentMessageDetailsFragment.setOnFabAddClickListener(new StudentMessageDetailsFragment.OnFabAddClickListener() {
            @Override
            public void onclick() {
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_students_message_details, mStudentMessageAddFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        mStudentMessageAddFragment.setOnModifyClickListener(new StudentMessageAddFragment.OnModifyClickListener() {
            @Override
            public void onclick(String type, String key, String content) {
                if (type.equals("img")) {

                } else if (type.equals("img")) {

                } else if (type.equals("sex")) {
                    mSexAlertDialog = new AlertDialog.Builder(mContext).setSingleChoiceItems(new String[]{"男", "女"}, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mSexAlertDialog.dismiss();
                        }
                    }).create();
                    mSexAlertDialog.show();
                } else if (type.equals("text")) {

                    mStudentMessageModifyFragment = new StudentMessageModifyFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(StudentMessageModifyFragment.KEY_NAME, key);
                    bundle.putString(StudentMessageModifyFragment.VALUE, content);
                    mStudentMessageModifyFragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fl_students_message_details, mStudentMessageModifyFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

            }
        });
    }

}
