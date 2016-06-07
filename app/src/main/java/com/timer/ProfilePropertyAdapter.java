package com.timer;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.List;
import java.util.Map;

/**
 * Created by ccc on 16/5/28.
 */
public class ProfilePropertyAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

    private static final String TAG = "ProfilePropertyAdapter";

    private Context context;
    private List<Map<String, Object>> list;
    private LayoutInflater inflater;

    private final int VIEW_TYPE = 2;
    private final int TYPE_0 = 0;
    private final int TYPE_1 = 1;

    public ProfilePropertyAdapter(Context context, List<Map<String, Object>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_0;
        else
            return TYPE_1;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Log.e(TAG, "position=" + position);
        ViewHolder_0 viewHolder_0 = null;
        ViewHolder_1 viewHolder_1 = null;
        int type = getItemViewType(position);
        Map<String, Object> map = list.get(position);
        if (view == null) {
            inflater = LayoutInflater.from(context);
            // 按当前所需的样式，确定new的布局
            switch (type) {
                case TYPE_0:
                    view = inflater.inflate(R.layout.list_item_profile_property_a,
                            viewGroup, false);
                    viewHolder_0 = new ViewHolder_0();
                    viewHolder_0.showName = (TextView) view
                            .findViewById(R.id.textViewShowStatusName);
                    /*viewHolder_0.showStatus = (CheckBox) view
                            .findViewById(R.id.checkBoxShowStatus);
                    setCheckBoxClick(viewHolder_0.showStatus, map);*/
                    viewHolder_0.showStatus = (Switch) view
                            .findViewById(R.id.switchShowStatus);
                    setSwitchClick(viewHolder_0.showStatus, map);
                    view.setTag(viewHolder_0);
                    break;
                case TYPE_1:
                    view = inflater.inflate(R.layout.list_item_profile_property_b,
                            viewGroup, false);
                    viewHolder_1 = new ViewHolder_1();
                    viewHolder_1.showName = (TextView) view
                            .findViewById(R.id.textViewShowName);
                    viewHolder_1.showRemark = (TextView) view
                            .findViewById(R.id.textViewShowRemark);
                    view.setTag(viewHolder_1);
                    break;
                default:
                    break;
            }

        } else {
            switch (type) {
                case TYPE_0:
                    viewHolder_0 = (ViewHolder_0) view.getTag();
                    break;
                case TYPE_1:
                    viewHolder_1 = (ViewHolder_1) view.getTag();
                    break;
            }
        }
        // 设置资源
        switch (type) {
            case TYPE_0:
                viewHolder_0.showName.setText(map.get("showName").toString());
                boolean status = Boolean.parseBoolean(map.get("showStatus").toString());
                viewHolder_0.showStatus.setChecked(status);
                break;
            case TYPE_1:
                viewHolder_1.showName.setText(map.get("showName").toString());
                viewHolder_1.showRemark.setText(map.get("showRemark").toString());
                break;
        }
        return view;
    }

    public class ViewHolder_0 {
        TextView showName;
        //CheckBox showStatus;
        Switch showStatus;
    }

    public class ViewHolder_1 {
        TextView showName;
        TextView showRemark;
    }

    private void setCheckBoxClick(CheckBox showStatus, final Map<String, Object> map) {
        showStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println("CheckBox map=" + map);
                //System.out.println("CheckBox view=" + view);
                CheckBox checkBox = (CheckBox) view;
                boolean status = checkBox.isChecked();
                Log.e(TAG, "status=" + status);
                map.put("showStatus", status);
            }
        });
    }

    private void setSwitchClick(Switch showStatus, final Map<String, Object> map) {
        showStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println("Switch map=" + map);
                //System.out.println("Switch view=" + view);
                Switch switchButton = (Switch) view;
                boolean status = switchButton.isChecked();
                Log.e(TAG, "status=" + status);
                map.put("showStatus", status);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Map<String, Object> map = list.get(i);
        //System.out.println("map=" + map);
        //System.out.println("adapterView=" + adapterView);
        //System.out.println("view=" + view);
        Log.e(TAG, "item index=" + i);
        BaseAdapter baseAdapter = (BaseAdapter) adapterView.getAdapter();
        Object statusObject = map.get("showStatus");
        Object remarkObject = map.get("showRemark");

        //System.out.println("remarkObject=" + remarkObject);

        switch (i) {
            case 0:

                break;
            case 1:
                setTime(remarkObject, map, baseAdapter);
                break;
            case 2:
                Intent intent = new Intent(context, WeekDialog.class);
                ((AppCompatActivity) context).startActivityForResult(intent, i);
                break;
            case 3:

                break;
            default:
                break;
        }
    }

    private void setTime(Object hourMinuteObject, final Map<String, Object> map, final BaseAdapter baseAdapter) {
        Log.e(TAG, "time=" + hourMinuteObject);
        String hourMinute = hourMinuteObject.toString();
        if (hourMinute != null && !"".equals(hourMinute)) {
            int index = hourMinute.indexOf(":");
            if (index > 0) {
                String hourString = hourMinute.substring(0, index);
                String minuteString = hourMinute.substring(index + 1);
                int hour = Integer.parseInt(hourString);
                int minute = Integer.parseInt(minuteString);

                TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Log.e(TAG, "Time=" + hourOfDay + ":" + minute);
                                String hourString = String.valueOf(hourOfDay);
                                String minuteString = String.valueOf(minute);
                                if (hourOfDay < 10) {
                                    hourString = "0" + hourOfDay;
                                }
                                if (minute < 10) {
                                    minuteString = "0" + minute;
                                }
                                String hourMinute = hourString + ":" + minuteString;
                                Log.e(TAG, "new time=" + hourMinute);
                                map.put("showRemark", hourMinute);
                                baseAdapter.notifyDataSetChanged();
                            }

                        }, hour, minute, true);
                timePickerDialog.show();
            }
        }
    }

    private void setxn() {
        /*new AlertDialog.Builder(self)
                .setTitle("列表框")
                .setItems(new String[] {"列表项1","列表项2","列表项3"}, null)
                .setNegativeButton("确定", null)
                .show();*/
    }
}
