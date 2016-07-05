package com.timer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccc on 16/6/2.
 */
public class OperationDialog extends Activity {

    private static final String TAG = "OperationDialog";

    private LinearLayout layout;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operation_dialog);
        layout = (LinearLayout) findViewById(R.id.operation_layout);
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
        ViewHolder.operationRadioButton0 = (RadioButton) findViewById(R.id.operationRadioButton0);
        ViewHolder.operationRadioButton1 = (RadioButton) findViewById(R.id.operationRadioButton1);
        ViewHolder.operationRadioButton2 = (RadioButton) findViewById(R.id.operationRadioButton2);
        ViewHolder.operationRadioButton3 = (RadioButton) findViewById(R.id.operationRadioButton3);
        Bundle bundle = this.getIntent().getExtras();
        String operationStrig = bundle.getString("showCode");
        position = bundle.getInt("position");
        //String operation = new Profile().enOperation(operationStrig);
        switch (operationStrig) {
            case "0":
                ViewHolder.operationRadioButton0.setChecked(true);
                break;
            case "1":
                ViewHolder.operationRadioButton0.setChecked(true);
                break;
            case "2":
                ViewHolder.operationRadioButton0.setChecked(true);
                break;
            case "3":
                ViewHolder.operationRadioButton0.setChecked(true);
                break;
            default:
                break;
        }
        List<RadioButton> list = new ArrayList<RadioButton>();
        list.add(ViewHolder.operationRadioButton0);
        list.add(ViewHolder.operationRadioButton1);
        list.add(ViewHolder.operationRadioButton2);
        list.add(ViewHolder.operationRadioButton3);
        setChecked(list);
    }

    private void setChecked(final List<RadioButton> list) {
        for (RadioButton radioButton : list) {
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.e(TAG, "radioButton id=" + buttonView.getId() + " isChecked=" + isChecked);
                    // 当选中时,点击的不是当前的就设置为取消选中
                    if (isChecked) {
                        for (RadioButton radioButton : list) {
                            if (buttonView.getId() != radioButton.getId()) {
                                radioButton.setChecked(false);
                            }
                        }
                    }
                }

            });
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    public void buttonOk(View v) {
        String operation = "";
        if (ViewHolder.operationRadioButton0.isChecked()) {
            operation = "0";
        }
        if (ViewHolder.operationRadioButton1.isChecked()) {
            operation = "1";
        }
        if (ViewHolder.operationRadioButton2.isChecked()) {
            operation = "2";
        }
        if (ViewHolder.operationRadioButton3.isChecked()) {
            operation = "3";
        }
        //String operationString = new Profile().showOperation(operation);
        Log.e(TAG, "operationString=" + operation);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("showCode", operation);
        bundle.putInt("position", position);
        bundle.putString("type", "operation");
        intent.putExtras(bundle);
        //setResult(CONTEXT_RESTRICTED, intent);
        setResult(Activity.RESULT_OK, intent);
        this.finish();
    }

    public void buttonCancel(View v) {
        this.finish();
    }

    private static class ViewHolder {
        public static RadioButton operationRadioButton0;
        public static RadioButton operationRadioButton1;
        public static RadioButton operationRadioButton2;
        public static RadioButton operationRadioButton3;

        ViewHolder() {}

        ViewHolder(View view) {
            operationRadioButton0 = (RadioButton) view.findViewById(R.id.operationRadioButton0);
            operationRadioButton1 = (RadioButton) view.findViewById(R.id.operationRadioButton1);
            operationRadioButton2 = (RadioButton) view.findViewById(R.id.operationRadioButton2);
            operationRadioButton3 = (RadioButton) view.findViewById(R.id.operationRadioButton3);
        }
    }
}
