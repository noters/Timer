package com.timer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ccc on 16/5/7.
 */
public class Profile implements Parcelable {

    // 时间
    private String time;

    // 状态
    private boolean status;

    // 重复
    private String repeat;

    // 操作
    private String operation;

    // 说明
    private String remark;

    private SlideView slideView;

    public Profile() {
    }

    public Profile(Parcel in) {
        this.time = in.readString();
        boolean[] val = {false};
        in.readBooleanArray(val);
        this.status = val[0];
        this.repeat = in.readString();
        this.operation = in.readString();
        this.remark = in.readString();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public SlideView getSlideView() {
        return slideView;
    }

    public void setSlideView(SlideView slideView) {
        this.slideView = slideView;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.getTime());
        parcel.writeBooleanArray(new boolean[]{this.isStatus()});
        parcel.writeString(this.getRepeat());
        parcel.writeString(this.getOperation());
        parcel.writeString(this.getRemark());
    }

    public static final Parcelable.Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }

        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }
    };

    @Override
    public String toString() {
        return "Profile{" +
                "time='" + time + '\'' +
                ", status=" + status +
                ", repeat='" + repeat + '\'' +
                ", operation='" + operation + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}
