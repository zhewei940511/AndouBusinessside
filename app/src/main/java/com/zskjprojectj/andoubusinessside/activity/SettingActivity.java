package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.zskjprojectj.andoubusinessside.R;
import com.zhuosongkj.android.library.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;
import com.zskjprojectj.andoubusinessside.model.LoginInfo;
import com.zhuosongkj.android.library.util.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.GlideEngine;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zskjprojectj.andoubusinessside.activity.EditInfoActivity.KEY_INFO;

public class SettingActivity extends BaseActivity {

    public static final int REQUEST_CODE_CHANGE_NICK_NAME = 1001;

    @BindView(R.id.nickNameTxt)
    TextView nickNameTxt;

    @OnClick(R.id.logoutBtn)
    void logout() {
        new AlertDialog.Builder(mActivity)
                .setTitle("退出登录")
                .setMessage("确定退出登录吗?")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialog, which) ->
                        HttpRxObservable.getObservable(mActivity, true, false,
                                ApiUtils.getApiService().logout(LoginInfo.getUid()),
                                result -> {
                                    LoginInfo.logout();
                                    LoginActivity.start(mActivity);
                                    finish();
                                }).subscribe())
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "设置");
        nickNameTxt.setOnClickListener(v ->
                EditInfoActivity.start(mActivity,
                        "修改昵称",
                        "请输入昵称",
                        nickNameTxt.getText().toString(),
                        REQUEST_CODE_CHANGE_NICK_NAME));
    }

    @OnClick(R.id.changeAvatarBtn)
    void onChangeAvatarBtnClick() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .isCamera(true)
                .isCameraAroundState(true)
                .enableCrop(true)
                .circleDimmedLayer(true)
                .showCropFrame(false)
                .showCropGrid(false)
                .loadImageEngine(GlideEngine.createGlideEngine())
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null) {
            return;
        }
        switch (requestCode) {
            case REQUEST_CODE_CHANGE_NICK_NAME:
                String newNickName = data.getStringExtra(KEY_INFO);

                break;
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_setting;
    }
}
