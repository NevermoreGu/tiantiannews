package com.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ToastUtils {

    /**
     * 系统toast
     * @param context
     * @param message
     */
    public static void showShortToast(Context context, String message) {
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void showShortToast(Context context, int messageId) {
        Toast.makeText(context.getApplicationContext(), context.getString(messageId), Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context context, String message) {
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    public static void showLongToast(Context context, int messageId) {
        Toast.makeText(context.getApplicationContext(), context.getString(messageId), Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义toast
     * @param context
     * @param textId
     * @param duration
     */
    public static void makeText(Context context, int textId, int duration) {
        Toast result = new Toast(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.toast_custom_layout, null);
        TextView textView = (TextView) layout.findViewById(R.id.text);
        textView.setText(context.getResources().getString(textId));

        result.setView(layout);
        result.setDuration(duration);
        result.show();
    }

    public static void makeText(Context context, String text, int duration) {
        Toast result = new Toast(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.toast_custom_layout, null);
        TextView textView = (TextView) layout.findViewById(R.id.text);
        textView.setText(text);

        result.setView(layout);
        result.setDuration(duration);
        result.show();
    }

    public static void makeLongText(Context context, int textId) {
        makeText(context, textId, Toast.LENGTH_LONG);
    }

    public static void makeLongText(Context context, String text) {
        makeText(context, text, Toast.LENGTH_LONG);
    }
}
