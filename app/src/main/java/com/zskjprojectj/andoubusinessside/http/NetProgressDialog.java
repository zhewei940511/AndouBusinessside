package com.zskjprojectj.andoubusinessside.http;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.zskjprojectj.andoubusinessside.R;

import io.reactivex.annotations.NonNull;

public class NetProgressDialog extends Dialog {

    public NetProgressDialog(@NonNull Context context) {
        this(context, R.style.loading_dialog);
        setCancelable(false);
        setCanceledOnTouchOutside(true);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_load_view, null);
        setContentView(view, params);
        ImageView progressBar = findViewById(R.id.progressBar);
        ((AnimationDrawable) progressBar.getDrawable()).start();
        Window win = getWindow();
        WindowManager.LayoutParams layoutParams = win.getAttributes();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        win.setAttributes(layoutParams);
    }

    public NetProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }
}
