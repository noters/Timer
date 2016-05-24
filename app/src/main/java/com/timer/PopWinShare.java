package com.timer;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

/**
 * Created by ccc on 16/5/19.
 */
public class PopWinShare extends PopupWindow {
    private View mainView;
    private LinearLayout layoutShare, layoutCopy;

    public PopWinShare(Activity paramActivity, View.OnClickListener paramOnClickListener, int paramInt1, int paramInt2) {
        super(paramActivity);
        //窗口布局
        mainView = LayoutInflater.from(paramActivity).inflate(R.layout.popwin_share, null);
        //分享布局
        layoutShare = ((LinearLayout) mainView.findViewById(R.id.layout_add));
        //复制布局
        layoutCopy = (LinearLayout) mainView.findViewById(R.id.layout_about);
        //设置每个子布局的事件监听器
        if (paramOnClickListener != null) {
            layoutShare.setOnClickListener(paramOnClickListener);
            layoutCopy.setOnClickListener(paramOnClickListener);
        }
        setContentView(mainView);
        //设置宽度
        setWidth(paramInt1);
        //设置高度
        setHeight(paramInt2);
        //设置显示隐藏动画
        setAnimationStyle(R.style.AnimTools);
        //设置背景透明
        setBackgroundDrawable(new ColorDrawable(0));
    }
}
