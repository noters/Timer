package com.timer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by ccc on 16/6/2.
 */
public class WeekDialog extends Activity {

    private static final String TAG = "WeekDialog";

    private LinearLayout layout;

    private int position;

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
        init();
    }

    private void init () {
        ViewHolder.weekCheckBox0 = (CheckBox) findViewById(R.id.weekCheckBox0);
        ViewHolder.weekCheckBox1 = (CheckBox) findViewById(R.id.weekCheckBox1);
        ViewHolder.weekCheckBox2 = (CheckBox) findViewById(R.id.weekCheckBox2);
        ViewHolder.weekCheckBox3 = (CheckBox) findViewById(R.id.weekCheckBox3);
        ViewHolder.weekCheckBox4 = (CheckBox) findViewById(R.id.weekCheckBox4);
        ViewHolder.weekCheckBox5 = (CheckBox) findViewById(R.id.weekCheckBox5);
        ViewHolder.weekCheckBox6 = (CheckBox) findViewById(R.id.weekCheckBox6);
        Bundle bundle = this.getIntent().getExtras();
        String weekStrig = bundle.getString("showRemark");
        position = bundle.getInt("position");
        String weeks = new Profile().useWeek(weekStrig);
        String[] week = weeks.split(",");
        for (int i = 0; i < week.length; i ++) {
            switch (week[i]) {
                case "0":
                    ViewHolder.weekCheckBox0.setChecked(true);
                    break;
                case "1":
                    ViewHolder.weekCheckBox1.setChecked(true);
                    break;
                case "2":
                    ViewHolder.weekCheckBox2.setChecked(true);
                    break;
                case "3":
                    ViewHolder.weekCheckBox3.setChecked(true);
                    break;
                case "4":
                    ViewHolder.weekCheckBox4.setChecked(true);
                    break;
                case "5":
                    ViewHolder.weekCheckBox5.setChecked(true);
                    break;
                case "6":
                    ViewHolder.weekCheckBox6.setChecked(true);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    public void buttonOk(View v) {
        String weeks = "";
        if (ViewHolder.weekCheckBox0.isChecked()) {
            weeks = weeks + "0,";
        }
        if (ViewHolder.weekCheckBox1.isChecked()) {
            weeks = weeks + "1,";
        }
        if (ViewHolder.weekCheckBox2.isChecked()) {
            weeks = weeks + "2,";
        }
        if (ViewHolder.weekCheckBox3.isChecked()) {
            weeks = weeks + "3,";
        }
        if (ViewHolder.weekCheckBox4.isChecked()) {
            weeks = weeks + "4,";
        }
        if (ViewHolder.weekCheckBox5.isChecked()) {
            weeks = weeks + "5,";
        }
        if (ViewHolder.weekCheckBox6.isChecked()) {
            weeks = weeks + "6,";
        }
        if (weeks.length() > 0) {
            weeks = weeks.substring(0, weeks.length() - 1);
        }
        String weekString = new Profile().showWeek(weeks);
        Log.e(TAG, "weekString=" + weekString);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("showRemark", weekString);
        bundle.putInt("position", position);
        intent.putExtras(bundle);
        //setResult(CONTEXT_RESTRICTED, intent);
        setResult(Activity.RESULT_OK, intent);
        this.finish();
    }

    public void buttonCancel(View v) {
        this.finish();
    }

    private static class ViewHolder {
        public static CheckBox weekCheckBox0;
        public static CheckBox weekCheckBox1;
        public static CheckBox weekCheckBox2;
        public static CheckBox weekCheckBox3;
        public static CheckBox weekCheckBox4;
        public static CheckBox weekCheckBox5;
        public static CheckBox weekCheckBox6;

        ViewHolder() {}

        ViewHolder(View view) {
            weekCheckBox0 = (CheckBox) view.findViewById(R.id.weekCheckBox0);
            weekCheckBox1 = (CheckBox) view.findViewById(R.id.weekCheckBox1);
            weekCheckBox2 = (CheckBox) view.findViewById(R.id.weekCheckBox2);
            weekCheckBox3 = (CheckBox) view.findViewById(R.id.weekCheckBox3);
            weekCheckBox4 = (CheckBox) view.findViewById(R.id.weekCheckBox4);
            weekCheckBox5 = (CheckBox) view.findViewById(R.id.weekCheckBox5);
            weekCheckBox6 = (CheckBox) view.findViewById(R.id.weekCheckBox6);
        }
    }
}
