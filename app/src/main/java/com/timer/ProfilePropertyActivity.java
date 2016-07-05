package com.timer;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfilePropertyActivity extends AppCompatActivity {

    private static final String TAG = "ProfilePropertyActivity";

    /*private EditText editTextName;
    private EditText editTextRemark;*/

    private int position;

    private ListView listView;
    private ProfilePropertyAdapter profilePropertyAdapter;
    private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

    /*private Button buttonOk;
    private Button buttonCancel;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_property);

        //添加回退按钮
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile_property, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            backCancel();
            return true;
        }

        if (id == R.id.action_profile_ok) {
            backOk();
            return true;
        }

        /*switch (item.getItemId()) {
            case R.id.action_profile_ok :

            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    private void init() {

        Bundle bundle = this.getIntent().getExtras();
        Profile profile = bundle.getParcelable("profile");
        position = bundle.getInt("position");

        Map<String, Object> map_0 = new HashMap<String, Object>();
        map_0.put("showName", "启动任务");
        map_0.put("showStatus", profile.isStatus());

        Map<String, Object> map_1 = new HashMap<String, Object>();
        map_1.put("showName", "时间");
        map_1.put("showRemark", profile.getTime());

        Map<String, Object> map_2 = new HashMap<String, Object>();
        // 转为中文字符串显示
        String weeks = profile.showWeek(profile.getRepeat());
        map_2.put("showName", "重复");
        map_2.put("showCode", profile.getRepeat());
        map_2.put("showRemark", weeks);

        Map<String, Object> map_3 = new HashMap<String, Object>();
        String operation = profile.showOperation(profile.getOperation());
        map_3.put("showName", "操作");
        map_3.put("showCode", profile.getOperation());
        map_3.put("showRemark", operation);

        list.add(map_0);
        list.add(map_1);
        list.add(map_2);
        list.add(map_3);

        listView = (ListView) findViewById(R.id.listViewProfileProperty);
        profilePropertyAdapter = new ProfilePropertyAdapter(this, list);
        listView.setAdapter(profilePropertyAdapter);
        listView.setOnItemClickListener(profilePropertyAdapter);

        /*editTextName = (EditText) findViewById(R.id.editTextName);
        editTextRemark = (EditText) findViewById(R.id.editTextRemark);

        editTextName.setText(profile.getName());
        editTextRemark.setText(profile.getRemark());*/

        /*buttonOk = (Button)findViewById(R.id.buttonOk);
        buttonCancel = (Button)findViewById(R.id.buttonCancel);
        buttonOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String remark = editTextRemark.getText().toString();
//                SharedPreferences preferences = getSharedPreferences("Text", position);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("name", name);
//                editor.putString("remark", remark);
//
//                if(editor.commit()) {
//                    setResult(Activity.RESULT_OK);
//                }
//                finish();

                Profile profile = new Profile();
                profile.setName(name);
                profile.setRemark(remark);

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable("profile", profile);
                bundle.putInt("position", position);
                intent.putExtras(bundle);
                //setResult(CONTEXT_RESTRICTED, intent);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });*/
    }

    private void backCancel() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    private void backOk() {
        /*String name = editTextName.getText().toString();
        String remark = editTextRemark.getText().toString();*/
        /*SharedPreferences preferences = getSharedPreferences("Text", position);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", name);
        editor.putString("remark", remark);
        if(editor.commit()) {
            setResult(Activity.RESULT_OK);
        }
        finish();*/

        String status = list.get(0).get("showStatus").toString();
        String time = list.get(1).get("showRemark").toString();
        String repeat = list.get(2).get("showCode").toString();
        String operation = list.get(3).get("showCode").toString();

        String remark = "后启动";

        Profile profile = new Profile();
        profile.setTime(time);
        profile.setStatus(Boolean.parseBoolean(status));
        // 解码为数字字符串传回主页面
        //repeat = profile.useWeek(repeat);
        profile.setRepeat(repeat);
        profile.setOperation(operation);
        profile.setRemark(remark);

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable("profile", profile);
        bundle.putInt("position", position);
        intent.putExtras(bundle);
        //setResult(CONTEXT_RESTRICTED, intent);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "requestCode=" + requestCode);
        if(resultCode == Activity.RESULT_OK) {

            Bundle bundle = data.getExtras();
            String showCode = bundle.getString("showCode");
            int position = bundle.getInt("position");
            String type = bundle.getString("type");

            Log.e(TAG, "showCode=" + showCode + " position=" + position + " type=" + type);

            Map<String, Object> map = list.get(position);
            if (type != null && "operation".equals(type)) {
                map.put("showCode", showCode);
                String operationString = new Profile().showOperation(showCode);
                map.put("showRemark", operationString);
            } else if (type != null && "repeat".equals(type)){
                map.put("showCode", showCode);
                String repeatString = new Profile().showWeek(showCode);
                map.put("showRemark", repeatString);
            } else {

            }

            //刷新listView
            //ProfilePropertyAdapter profileAdapter = (ProfilePropertyAdapter) listView.getAdapter();
            profilePropertyAdapter.notifyDataSetChanged();

        }
    }
}
