package com.timer;

import android.util.Log;
import android.view.View;

/**
 * Created by ccc on 16/5/24.
 */
public class PopWinOnClickLintener implements View.OnClickListener {

    private static final String TAG = "PopWinOnClickLintener";

    private PopWinShare popWinShare;

    private ProfileAdapter profileAdapter;

    public PopWinOnClickLintener() {
    }

    public PopWinOnClickLintener(PopWinShare popWinShare) {
        this.popWinShare = popWinShare;
    }

    public void setPopWinShare(PopWinShare popWinShare) {
        this.popWinShare = popWinShare;
    }

    public void setProfileAdapter(ProfileAdapter profileAdapter) {
        this.profileAdapter = profileAdapter;
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "View id=" + v.getId());
        switch (v.getId()) {
            case R.id.layout_add:
                Log.e(TAG, "View add");
                profileAdapter.addList();
                break;
            case R.id.layout_about:
                Log.e(TAG, "View about");

                break;
            default:
                Log.e(TAG, "View default");
                break;
        }
        popWinShare.dismiss();
    }

}
