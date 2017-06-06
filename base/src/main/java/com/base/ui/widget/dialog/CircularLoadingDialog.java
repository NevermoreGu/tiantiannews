package com.base.ui.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.R;


public class CircularLoadingDialog extends Dialog {

    public CircularLoadingDialog(Context context) {
        this(context, 0);
    }

    public CircularLoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context context;
        private String message;
        private View contentView;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Set the Dialog message from resource
         *
         * @return
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public CircularLoadingDialog build() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final CircularLoadingDialog dialog = new CircularLoadingDialog(context);
            View layout = inflater.inflate(R.layout.dialog_normal_layout, null);
            dialog.addContentView(layout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            if (contentView != null) {
                ((FrameLayout) layout.findViewById(R.id.fl_loading_dialog_content))
                        .removeAllViews();
                ((FrameLayout) layout.findViewById(R.id.fl_loading_dialog_content)).addView(
                        contentView, new FrameLayout.LayoutParams(
                                FrameLayout.LayoutParams.WRAP_CONTENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT));
            }
            if (!TextUtils.isEmpty(message)) {
                ((TextView) layout.findViewById(R.id.tv_loading_dialog_message)).setText(message);

            }

            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setContentView(layout);
            WindowManager m = ((Activity) context).getWindowManager();
            Display d = m.getDefaultDisplay();
            android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();
            p.width = (int) (d.getWidth() * 0.7);
            dialog.getWindow().setAttributes(p);
            return dialog;
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
