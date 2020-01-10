package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zskjprojectj.andoubusinessside.R;
import com.zhuosongkj.android.library.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;
import com.zskjprojectj.andoubusinessside.model.LoginInfo;
import com.zhuosongkj.android.library.util.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.ToastUtil;
import com.zskjprojectj.andoubusinessside.utils.UrlUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ManageShopActivity extends BaseActivity {

    @BindView(R.id.shopNameEdt)
    EditText shopNameEdt;
    @BindView(R.id.mobileEdt)
    EditText mobileEdt;
    @BindView(R.id.shopNoticeEdt)
    EditText shopNoticeEdt;
    @BindView(R.id.addrEdt)
    EditText addrEdt;
    @BindView(R.id.returnContactEdt)
    EditText returnContactEdt;
    @BindView(R.id.returnMobileEdt)
    EditText returnMobileEdt;
    @BindView(R.id.returnAddrEdt)
    EditText returnAddrEdt;
    @BindView(R.id.contactNameEdt)
    EditText contactNameEdt;
    @BindView(R.id.logoImg)
    ImageView logoImg;
    private String logoImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "店铺管理");
        HttpRxObservable.getObservable(mActivity, true, true,
                ApiUtils.getApiService().shopInfo(
                        LoginInfo.getUid(),
                        LoginInfo.getMerchantId(),
                        LoginInfo.getMerchantTypeId()
                ), result -> {
                    addrEdt.setText(result.data.address);
                    contactNameEdt.setText(result.data.nickname);
                    mobileEdt.setText(result.data.mobile);
                    shopNameEdt.setText(result.data.name);
                    shopNoticeEdt.setText(result.data.desc);
                    returnAddrEdt.setText(result.data.return_address);
                    returnContactEdt.setText(result.data.nickname);
                    returnMobileEdt.setText(result.data.tel);
                    logoImagePath = UrlUtil.getImageUrl(result.data.banner_img);
                    Glide.with(mActivity)
                            .load(UrlUtil.getImageUrl(result.data.banner_img))
                            .apply(RequestOptions
                                    .centerCropTransform()
                                    .placeholder(R.mipmap.ic_placeholder))
                            .into(logoImg);
                }).subscribe();
    }

    @OnClick(R.id.confirmBtn)
    void onConfirmBtnClick() {
        String name = shopNameEdt.getText().toString();
        String contactName = contactNameEdt.getText().toString();
        String mobile = mobileEdt.getText().toString();
        String notice = shopNoticeEdt.getText().toString();
        String address = addrEdt.getText().toString();
        String returnContact = returnContactEdt.getText().toString();
        String returnMobile = returnMobileEdt.getText().toString();
        String returnAddress = returnAddrEdt.getText().toString();
        if (TextUtils.isEmpty(name)) {
            ToastUtil.showToast("请输入店铺名称");
        } else if (TextUtils.isEmpty(contactName)) {
            ToastUtil.showToast("请输入联系人");
        } else if (TextUtils.isEmpty(mobile)) {
            ToastUtil.showToast("请输入联系方式");
        } else if (TextUtils.isEmpty(notice)) {
            ToastUtil.showToast("请输入商家公告");
        } else if (TextUtils.isEmpty(address)) {
            ToastUtil.showToast("请输入商家地址");
        } else if (TextUtils.isEmpty(returnContact)) {
            ToastUtil.showToast("请输入退货联系人");
        } else if (TextUtils.isEmpty(returnMobile)) {
            ToastUtil.showToast("请输入退货联系电话");
        } else if (TextUtils.isEmpty(returnAddress)) {
            ToastUtil.showToast("请输入退货地址");
        } else if (TextUtils.isEmpty(logoImagePath)) {
            ToastUtil.showToast("请上传商家形象图");
        } else {
            HttpRxObservable.getObservable(mActivity, true, false,
                    ApiUtils.getApiService().saveShopInfo(
                            LoginInfo.getUid(),
                            LoginInfo.getMerchantId(),
                            LoginInfo.getMerchantTypeId(),
                            name,
                            contactName,
                            mobile,
                            notice,
                            address,
                            "Banner_IMG",
                            returnContact,
                            returnMobile,
                            returnAddress),
                    result -> {
                        ToastUtil.showToast(result.getMsg());
                        setResult(Activity.RESULT_OK);
                        finish();
                    })
                    .subscribe();
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_manage_shop;
    }
}
