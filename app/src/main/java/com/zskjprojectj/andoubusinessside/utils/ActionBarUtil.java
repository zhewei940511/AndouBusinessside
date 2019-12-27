package com.zskjprojectj.andoubusinessside.utils;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;

public class ActionBarUtil {

    public static void setTitle(BaseActivity activity, String title) {
        setupActionBar(activity);
        TextView titleTxt = getTitleTxt(activity);
        titleTxt.setText(title);
    }

    private static void setupActionBar(BaseActivity activity) {
        ViewGroup actionBarContainer = activity.findViewById(R.id.actionBarContainer);
        if (actionBarContainer.getChildCount() == 0) {
            LayoutInflater.from(activity).inflate(R.layout.layout_action_bar, actionBarContainer);
        }
        actionBarContainer.findViewById(R.id.backBtn).setOnClickListener(view -> activity.onBackPressed());
    }

    private static TextView getTitleTxt(BaseActivity activity) {
        return activity.findViewById(R.id.actionBarTitleTxt);
    }
}
