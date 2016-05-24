package com.timer;

/*import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;*/

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ccc on 16/5/7.
 */
/*public class ProfileAdapter extends BaseAdapter {

    Context context;
    List<Profile> list;

    public ProfileAdapter(Context context, List<Profile> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Map<String, TextView> map = null;
        TextView textViewName = null;
        TextView textViewRemark = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.show_profile, null);
            map = new HashMap<String, TextView>();
            textViewName = (TextView) convertView.findViewById(R.id.textViewName);
            textViewRemark = (TextView) convertView.findViewById(R.id.textViewRemark);
            map.put("textViewName", textViewName);
            map.put("textViewRemark", textViewRemark);
            convertView.setTag(map);
        } else {
            map = (Map) convertView.getTag();
            textViewName = map.get("textViewName");
            textViewRemark = map.get("textViewRemark");
        }
        Profile profile = list.get(position);
        textViewName.setText(profile.getName());
        textViewRemark.setText(profile.getRemark());
        return convertView;
    }

}*/

public class ProfileAdapter extends BaseAdapter implements AdapterView.OnItemClickListener, View.OnClickListener,
        SlideView.OnSlideListener {

    private static final String TAG = "ProfileAdapter";

    private Context context;
    private List<Profile> list;

    private SlideView mLastSlideViewWithStatusOn;

    private LayoutInflater mInflater;

    ProfileAdapter() {
        super();
        //mInflater = getLayoutInflater();
    }

    public ProfileAdapter(Context context, List<Profile> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
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
            //View itemView = mInflater.inflate(R.layout.list_item, null);
            View itemView = LayoutInflater.from(context).inflate(R.layout.list_item, null);

            slideView = new SlideView(context);
            slideView.setContentView(itemView);

            holder = new ViewHolder(slideView);
            slideView.setOnSlideListener(this);
            slideView.setTag(holder);
        } else {
            holder = (ViewHolder) slideView.getTag();
        }
        Profile profile = list.get(position);
        profile.setSlideView(slideView);
        profile.getSlideView().shrink();

        holder.name.setText(profile.getName());
        holder.type.setText(profile.getType());
        holder.remark.setText(profile.getRemark());
        holder.status.setChecked(profile.isStatus());
        holder.deleteHolder.setOnClickListener(this);

        return slideView;
    }

    private static class ViewHolder {
        public TextView name;
        public TextView type;
        public TextView remark;
        public Switch status;
        public ViewGroup deleteHolder;

        ViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.name);
            type = (TextView) view.findViewById(R.id.type);
            remark = (TextView) view.findViewById(R.id.remark);
            status = (Switch) view.findViewById(R.id.status);
            deleteHolder = (ViewGroup)view.findViewById(R.id.holder);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Log.e(TAG, "onItemClick position=" + position);

        // 显示最下边黑边提示
        //Toast.makeText(MainActivity.this, list.get(position).toString(), Toast.LENGTH_SHORT).show();

        Profile profile = list.get(position);
        Intent intent = new Intent();
        intent.setClass(context, ProfilePropertyActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("profile", profile);
        bundle.putInt("position", position);
        intent.putExtras(bundle);

        ((AppCompatActivity) context).startActivityForResult(intent, position);
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
    public void onClick(View view) {
        if (view.getId() == R.id.holder) {
            Log.e(TAG, "onClick view=" + view);
            int hashCode = mLastSlideViewWithStatusOn.hashCode();
            int index = -1;
            for (int i = 0; i < list.size(); i ++) {
                Profile profile = list.get(i);
                int hashCodeTemp = profile.getSlideView().hashCode();
                if (hashCode == hashCodeTemp) {
                    index = i;
                    break;
                }
            }
            Log.e(TAG, "list remove index=" + index);
            if (index >= 0) {
                list.remove(index);
                ListViewCompat listViewCompat = ((MainActivity) context).getListViewCompat();
                ProfileAdapter profileAdapter = (ProfileAdapter) listViewCompat.getAdapter();
                profileAdapter.notifyDataSetChanged();
            }
        }
    }

    public void addList() {
        int position = list.size();
        Log.e(TAG, "add position=" + position);

        Profile profile = new Profile();
        Intent intent = new Intent();
        intent.setClass(context, ProfilePropertyActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("profile", profile);
        bundle.putInt("position", position);
        intent.putExtras(bundle);

        ((AppCompatActivity) context).startActivityForResult(intent, position);
    }

}
