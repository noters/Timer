package com.timer;

/*import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<Profile> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inview();
        listView = (ListView) findViewById(R.id.listView);
        ProfileAdapter profileAdapter = new ProfileAdapter(this, list);
        listView.setAdapter(profileAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 显示最下边黑边提示
                //Toast.makeText(MainActivity.this, list.get(position).toString(), Toast.LENGTH_SHORT).show();

                Profile profile = list.get(position);
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ProfilePropertyActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", profile.getName());
                bundle.putString("remark", profile.getRemark());
                bundle.putInt("position", position);
                intent.putExtras(bundle);
                startActivityForResult(intent, position);

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("长按");
                return true;
            }
        });

        // 按空白地方增加
        listView.setOnTouchListener(new AdapterView.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                System.out.println("空白");
                return true;
            }
        });
    }

    void inview() {
        list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            String id = String.valueOf(i);
            Profile profile = new Profile("name_" + id, "remark_" + id);
            list.add(profile);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("-----requestCode " + requestCode);
        if(resultCode == Activity.RESULT_OK) {
            SharedPreferences preferences = getSharedPreferences("Text", requestCode);

            String name = preferences.getString("name", null);
            String remark = preferences.getString("remark", null);

            System.out.println("-----" + name);
            System.out.println("-----" + remark);

            Profile profile = list.get(requestCode);
            profile.setName(name);
            profile.setRemark(remark);

            //刷新listView
            ProfileAdapter profileAdapter = (ProfileAdapter) listView.getAdapter();
            profileAdapter.notifyDataSetChanged();

        }
        //super.onActivityResult(requestCode, resultCode, data);
    }

}*/

/*import java.util.ArrayList;
import java.util.List;

import com.timer.SlideView.OnSlideListener;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnItemClickListener, OnClickListener,
        OnSlideListener {

    private static final String TAG = "MainActivity";

    private ListViewCompat mListView;

    private List<MessageItem> mMessageItems = new ArrayList<MessageItem>();

    private SlideView mLastSlideViewWithStatusOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mListView = (ListViewCompat) findViewById(R.id.list);

        for (int i = 0; i < 20; i++) {
            MessageItem item = new MessageItem();
            if (i % 2 == 0) {
                item.title = "8:30";
                item.type = "每天";
                item.msg = "30分钟后启动";
                item.switchId = false;
            } else {
                item.title = "12:35";
                item.type = "周日";
                item.msg = "12小时后启动";
                item.switchId = true;
            }
            mMessageItems.add(item);
        }
        mListView.setAdapter(new SlideAdapter());
        mListView.setOnItemClickListener(this);
    }

    private class SlideAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        SlideAdapter() {
            super();
            mInflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            return mMessageItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mMessageItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            SlideView slideView = (SlideView) convertView;
            if (slideView == null) {
                View itemView = mInflater.inflate(R.layout.list_item, null);

                slideView = new SlideView(MainActivity.this);
                slideView.setContentView(itemView);

                holder = new ViewHolder(slideView);
                slideView.setOnSlideListener(MainActivity.this);
                slideView.setTag(holder);
            } else {
                holder = (ViewHolder) slideView.getTag();
            }
            MessageItem item = mMessageItems.get(position);
            item.slideView = slideView;
            item.slideView.shrink();

            holder.title.setText(item.title);
            holder.type.setText(item.type);
            holder.msg.setText(item.msg);
            holder.switchId.setChecked(item.switchId);
            holder.deleteHolder.setOnClickListener(MainActivity.this);

            return slideView;
        }

    }

    public class MessageItem {
        public String title;
        public String type;
        public String msg;
        public boolean switchId;
        public SlideView slideView;
    }

    private static class ViewHolder {
        public TextView title;
        public TextView type;
        public TextView msg;
        public Switch switchId;
        public ViewGroup deleteHolder;

        ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.title);
            type = (TextView) view.findViewById(R.id.type);
            msg = (TextView) view.findViewById(R.id.msg);
            switchId = (Switch) view.findViewById(R.id.switchId);
            deleteHolder = (ViewGroup)view.findViewById(R.id.holder);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Log.e(TAG, "onItemClick position=" + position);
    }

    @Override
    public void onSlide(View view, int status) {
        if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
            mLastSlideViewWithStatusOn.shrink();
        }

        if (status == SLIDE_STATUS_ON) {
            mLastSlideViewWithStatusOn = (SlideView) view;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.holder) {
            Log.e(TAG, "onClick v=" + v);
        }
    }
}*/


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ListViewCompat listViewCompat;

    private List<Profile> list = new ArrayList<Profile>();

    private PopWinShare popWinShare;

    private ProfileAdapter profileAdapter;

    public ListViewCompat getListViewCompat() {
        return listViewCompat;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        initView();

        /*final ImageButton imageButton = (ImageButton) findViewById(R.id.title_btn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //titlePopup.show(v);
                if (popWinShare == null) {
                    //自定义的单击事件
                    OnClickLintener paramOnClickListener = new OnClickLintener();
                    popWinShare = new PopWinShare(MainActivity.this, paramOnClickListener, Util.dip2px(MainActivity.this, 160), Util.dip2px(MainActivity.this, 160));
                    //监听窗口的焦点事件，点击窗口外面则取消显示
                    popWinShare.getContentView().setOnFocusChangeListener(new View.OnFocusChangeListener() {

                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (!hasFocus) {
                                popWinShare.dismiss();
                            }
                        }
                    });
                }
                //设置默认获取焦点
                popWinShare.setFocusable(true);
                //以某个控件的x和y的偏移量位置开始显示窗口
                popWinShare.showAsDropDown(imageButton, 0, 0);
                //如果窗口存在，则更新
                popWinShare.update();
            }
        });*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        if (id == R.id.action_add) {
            showPopWinShare(findViewById(R.id.toolbar));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        listViewCompat = (ListViewCompat) findViewById(R.id.list);

        for (int i = 0; i < 3; i++) {
            Profile profile = new Profile();
            if (i % 2 == 0) {
                profile.setName("8:30");
                profile.setType("每天");
                profile.setRemark("30分钟后启动");
                profile.setStatus(false);
            } else {
                profile.setName("12:35");
                profile.setType("周日");
                profile.setRemark("12小时后启动");
                profile.setStatus(true);
            }
            list.add(profile);
        }
        
        profileAdapter = new ProfileAdapter(this, list);
        listViewCompat.setAdapter(profileAdapter);
        listViewCompat.setOnItemClickListener(profileAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "requestCode=" + requestCode);
        if(resultCode == Activity.RESULT_OK) {

            Bundle bundle = data.getExtras();
            Profile profileTemp = bundle.getParcelable("profile");
            int position = bundle.getInt("position");
            String name = profileTemp.getName();
            String remark = profileTemp.getRemark();

            Log.e(TAG, "name=" + name);
            Log.e(TAG, "remark=" + remark);

            Profile profile = null;
            if (list.size() == position) {
                profile = new Profile();
                list.add(profile);
            } else {
                profile = list.get(position);
            }
            //Profile profile = list.get(position);
            profile.setName(name);
            profile.setRemark(remark);

            //刷新listView
            ProfileAdapter profileAdapter = (ProfileAdapter) listViewCompat.getAdapter();
            profileAdapter.notifyDataSetChanged();

        }
    }

    private void showPopWinShare (View view) {
        if (popWinShare == null) {
            //自定义的单击事件
            PopWinOnClickLintener paramOnClickListener = new PopWinOnClickLintener();
            popWinShare = new PopWinShare(MainActivity.this, paramOnClickListener, Util.dip2px(MainActivity.this, 140), Util.dip2px(MainActivity.this, 82));
            paramOnClickListener.setPopWinShare(popWinShare);
            paramOnClickListener.setProfileAdapter(profileAdapter);
            //监听窗口的焦点事件，点击窗口外面则取消显示
            popWinShare.getContentView().setOnFocusChangeListener(new View.OnFocusChangeListener() {

                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        popWinShare.dismiss();
                    }
                }
            });
        }
        //设置默认获取焦点
        popWinShare.setFocusable(true);
        //以某个控件的x和y的偏移量位置开始显示窗口
        //popWinShare.showAsDropDown(view, 400, 0);
        popWinShare.showAtLocation(view, Gravity.TOP | Gravity.RIGHT, 10, 165);
        //如果窗口存在，则更新
        popWinShare.update();
    }

    /*class OnClickLintener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.layout_add:

                    break;
                case R.id.layout_about:

                    break;

                default:
                    break;
            }

        }

    }*/

    /*private void showChangeLife () {
        //调用
        ChangeLifeHallWindow mLifeHallWindow = new ChangeLifeHallWindow(RobNewActivity.this, itemsOnClick);
        mLifeHallWindow.showAtLocation(RobNewActivity.this.findViewById(R.id.base_mune), Gravity.TOP|Gravity.RIGHT, 10, 230);
        //设置layout在PopupWindow中显示的位置
        // 为弹出窗口实现监听类
        private OnClickListener itemsOnClick = new OnClickListener(){
            public void onClick(View v) {
                mLifeHallWindow.dismiss();
            }
        };
    }*/
}