package com.timer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

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
                    viewHolder_0.showStatus = (CheckBox) view
                            .findViewById(R.id.checkBoxShowStatus);
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
        CheckBox showStatus;
    }

    public class ViewHolder_1 {
        TextView showName;
        TextView showRemark;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Map<String, Object> map = list.get(i);
        System.out.println(">>>>>" + i);
    }
}
