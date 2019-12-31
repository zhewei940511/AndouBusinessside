package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.BaseObserver;
import com.zskjprojectj.andoubusinessside.http.BaseResult;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;
import com.zskjprojectj.andoubusinessside.http.ListData;
import com.zskjprojectj.andoubusinessside.model.LoginUtil;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;
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
                .setPositiveButton("确定", (dialog, which) -> {
                    LoginUtil.logout();
                    Intent intent = new Intent(mActivity, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    ActivityUtils.startActivity(intent);
                    finish();
                })
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
                HttpRxObservable.getObservable(ApiUtils.getApiService().testList())
                        .subscribe(new BaseObserver<ListData<Order>>(mActivity) {
                            @Override
                            public void onSuccess(BaseResult<ListData<Order>> result) {

                            }

                            @Override
                            public void onFailure(String msg) {

                            }
                        });
                break;
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_setting;
    }
}
