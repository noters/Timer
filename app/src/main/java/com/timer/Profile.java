package com.timer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ccc on 16/5/7.
 */
public class Profile implements Parcelable {

    private String name;

    private String type;

    private String remark;

    private boolean status;

    private SlideView slideView;

    public Profile() {
    }

    public Profile(Parcel in) {
        this.name = in.readString();
        this.type = in.readString();
        this.remark = in.readString();
        boolean[] val = {false};
        in.readBooleanArray(val);
        this.status = val[0];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
        parcel.writeString(this.getName());
        parcel.writeString(this.getType());
        parcel.writeString(this.getRemark());
        parcel.writeBooleanArray(new boolean[]{this.isStatus()});

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
}
