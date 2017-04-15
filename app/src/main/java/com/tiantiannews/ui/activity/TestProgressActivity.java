package com.tiantiannews.ui.activity;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.tiantiannews.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestProgressActivity extends AppCompatActivity {

    @BindView(R.id.img_clip)
    ImageView imgClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_progress);
        ButterKnife.bind(this);
        final ClipDrawable clipDrawable = (ClipDrawable) imgClip.getDrawable();
        final Handler handler = new Handler()
        {
            public void handleMessage(Message msg)
            {
                if(msg.what == 0x1233)
                {
                    //修改ClipDrawable的level值
                    clipDrawable.setLevel(clipDrawable.getLevel() +500);
                }
            }
        };
        final Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            public void run()
            {
                Message msg = new Message();
                msg.what = 0x1233;
                //发送消息,通知应用修改ClipDrawable对象的level值
                handler.sendMessage(msg);
                //取消定时器
                if(clipDrawable.getLevel() >= 10000)
                {
                    timer.cancel();
                }
            }
        },0,300);
    }
}
