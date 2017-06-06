package com.tiantiannews.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.base.ui.widget.recycleview.splitline.DividerItemDecoration;
import com.tiantiannews.R;
import com.tiantiannews.base.BaseFragment;
import com.tiantiannews.base.adapter.recycleview.BaseQuickAdapter;
import com.tiantiannews.base.adapter.recycleview.BaseViewHolder;
import com.tiantiannews.base.adapter.recycleview.listener.OnItemClickListener;
import com.tiantiannews.data.bean.Student;
import com.tiantiannews.ui.activity.StudentMessageDetailsActivity;
import com.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class StudentMessageListFragment extends BaseFragment {

    @BindView(R.id.rv_student_message_list)
    RecyclerView mRecyclerView;

    private List<Student> mStudentMessageData = new ArrayList<>();
    private StudentMessageAdapter mStudentMessageAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_student_message_list;
    }

    @Override
    public void initVariables() {

    }

    @Override
    public void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                ActivityUtils.openActivity(getActivity(), StudentMessageDetailsActivity.class);
            }
        });
        mStudentMessageAdapter = new StudentMessageAdapter();
        mRecyclerView.setAdapter(mStudentMessageAdapter);
    }

    @Override
    public void loadData() {
        Student student;
        int j = 20;
        for (int i = 0; i < j; i++) {
            student = new Student(i, "曹操", "13", 1, "南京", "13260778082", "123", "456", "二班", "3年级", "caocao", "2017.2.7");
            mStudentMessageData.add(student);
        }
        mStudentMessageAdapter.notifyDataSetChanged();

    }

    public class StudentMessageAdapter extends BaseQuickAdapter<Student, BaseViewHolder> {
        public StudentMessageAdapter() {
            super(R.layout.item_student_message_list, mStudentMessageData);
        }

        @Override
        protected void convert(BaseViewHolder viewHolder, Student item) {
            viewHolder.setText(R.id.tv_item_student_message_name, item.name)
                    .setText(R.id.tv_item_student_message_last_date, item.gradeName + item.className)
                    .setText(R.id.tv_item_student_message_last_date, item.revisitDate);
        }
    }
}
