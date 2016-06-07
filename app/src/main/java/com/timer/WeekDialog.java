package com.timer;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by ccc on 16/6/2.
 */
public class WeekDialog extends Activity {

    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_dialog);
        layout = (LinearLayout) findViewById(R.id.week_layout);
        layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    public void buttonOk(View v) {
        this.finish();
        //MainWeixin.instance.finish();//关闭Main 这个Activity
    }

    public void buttonCancel(View v) {
        this.finish();
    }
}
