package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;
import com.zskjprojectj.andoubusinessside.model.ADArea;
import com.zskjprojectj.andoubusinessside.model.ADCity;
import com.zskjprojectj.andoubusinessside.model.ADProvince;
import com.zskjprojectj.andoubusinessside.model.Address;
import com.zskjprojectj.andoubusinessside.model.LoginInfo;
import com.zskjprojectj.andoubusinessside.model.User;
import com.zskjprojectj.andoubusinessside.ui.AddressBottomDialog;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.BitmapUtil;
import com.zskjprojectj.andoubusinessside.utils.GlideEngine;
import com.zskjprojectj.andoubusinessside.utils.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import chihane.jdaddressselector.AddressProvider;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.zskjprojectj.andoubusinessside.model.User.Role.KEY_TYPE;

public class JoinInfoUploadActivity extends BaseActivity {

    private static final int REQUEST_CODE_SELECT_LOGO = 666;
    private static final int REQUEST_CODE_SELECT_BANNER = 667;
    private static final int REQUEST_CODE_SELECT_LICENSE = 668;

    @BindView(R.id.nameEdt)
    EditText nameEdt;

    @BindView(R.id.contactNameEdt)
    EditText contactNameEdt;

    @BindView(R.id.contactMobileEdt)
    EditText contactMobileEdt;

    @BindView(R.id.addressTxt)
    TextView addressTxt;

    @BindView(R.id.addressDetailEdt)
    EditText addressDetailEdt;

    @BindView(R.id.descriptionEdt)
    EditText descriptionEdt;

    @BindView(R.id.logoImg)
    ImageView logoImg;
    @BindView(R.id.bannerImg)
    ImageView bannerImg;
    @BindView(R.id.licenseImg)
    ImageView licenseImg;

    Address address;
    int type;

    final List<ADProvince> adProvinces = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getIntExtra(KEY_TYPE, User.Role.Type.HOTEL.typeInt);
        if (type == User.Role.Type.HOTEL.typeInt) {
            ActionBarUtil.setTitle(mActivity, "酒店商家入驻");
        } else if (type == User.Role.Type.MALL.typeInt) {
            ActionBarUtil.setTitle(mActivity, "商城商家入驻");
        } else if (type == User.Role.Type.RESTAURANT.typeInt) {
            ActionBarUtil.setTitle(mActivity, "饭店商家入驻");
            findViewById(R.id.restaurantTypeContainer).setVisibility(View.VISIBLE);
            findViewById(R.id.restaurantLicenseContainer).setVisibility(View.VISIBLE);
        }
        HttpRxObservable.getObservable(mActivity, true, true,
                ApiUtils.getApiService().districts()
                , result -> adProvinces.addAll(result.data))
                .subscribe();
        addressTxt.setOnClickListener(v -> {
            AddressBottomDialog dialog = AddressBottomDialog.show(mActivity);
            dialog.setAddressProvider(new AddressProvider() {
                @Override
                public void provideProvinces(AddressReceiver<Province> addressReceiver) {
                    ArrayList<Province> provinces = new ArrayList<>();
                    for (ADProvince adProvince : adProvinces) {
                        Province province = new Province();
                        province.id = adProvince.id;
                        province.name = adProvince.name;
                        provinces.add(province);
                    }
                    addressReceiver.send(provinces);
                }

                @Override
                public void provideCitiesWith(int provinceId, AddressReceiver<City> addressReceiver) {
                    ArrayList<City> cities = new ArrayList<>();
                    for (ADProvince adProvince : adProvinces) {
                        if (adProvince.id == provinceId) {
                            for (ADCity adCity : adProvince.cities) {
                                City city = new City();
                                city.id = adCity.id;
                                city.name = adCity.name;
                                city.province_id = adCity.pid;
                                cities.add(city);
                            }
                            break;
                        }
                    }
                    addressReceiver.send(cities);
                }

                @Override
                public void provideCountiesWith(int cityId, AddressReceiver<County> addressReceiver) {
                    ArrayList<County> counties = new ArrayList<>();
                    for (ADProvince adProvince : adProvinces) {
                        for (ADCity adCity : adProvince.cities) {
                            if (adCity.id == cityId) {
                                for (ADArea adArea : adCity.getAreas()) {
                                    County county = new County();
                                    county.id = adArea.id;
                                    county.name = adArea.name;
                                    county.city_id = adArea.pid;
                                    counties.add(county);
                                }
                                break;
                            }
                        }
                    }
                    addressReceiver.send(counties);
                }

                @Override
                public void provideStreetsWith(int countyId, AddressReceiver<Street> addressReceiver) {
                    addressReceiver.send(null);
                }
            });
            dialog.setOnAddressSelectedListener((province, city, county, street) -> {
                address = new Address(province, city, county);
                addressTxt.setText(address.toString());
                dialog.dismiss();
            });
        });
        logoImg.setOnClickListener(v -> startSelectPic(REQUEST_CODE_SELECT_LOGO));
        bannerImg.setOnClickListener(v -> startSelectPic(REQUEST_CODE_SELECT_BANNER));
        licenseImg.setOnClickListener(v -> startSelectPic(REQUEST_CODE_SELECT_LICENSE));
    }

    private void startSelectPic(int requestCode) {
        PictureSelector.create(mActivity)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .isCamera(true)
                .loadImageEngine(GlideEngine.createGlideEngine())
                .forResult(requestCode);
    }

    @OnClick(R.id.confirmBtn)
    void onConfirmBtn() {
        String name = nameEdt.getText().toString();
        String contactName = contactNameEdt.getText().toString();
        String contactMobile = contactMobileEdt.getText().toString();
        String addressStr = addressTxt.getText().toString();
        String addressDetail = addressDetailEdt.getText().toString();
        String description = descriptionEdt.getText().toString();
        if (TextUtils.isEmpty(name)) {
            ToastUtil.showToast("请输入商户名称!");
        } else if (TextUtils.isEmpty(contactName)) {
            ToastUtil.showToast("请输入联系人!");
        } else if (TextUtils.isEmpty(contactMobile)) {
            ToastUtil.showToast("请输入联系电话!");
        } else if (TextUtils.isEmpty(contactMobile)) {
            ToastUtil.showToast("请输入联系电话!");
        } else if (TextUtils.isEmpty(addressStr)) {
            ToastUtil.showToast("请选择店铺地址!");
        } else if (TextUtils.isEmpty(addressDetail)) {
            ToastUtil.showToast("请选择详细地址!");
        } else if (TextUtils.isEmpty(description)) {
            ToastUtil.showToast("请选择商家简介!");
        } else if (TextUtils.isEmpty((String) logoImg.getTag())) {
            ToastUtil.showToast("请添加商家Logo图!");
        } else if (TextUtils.isEmpty((String) bannerImg.getTag())) {
            ToastUtil.showToast("请添加商家海报图!");
        } else if (TextUtils.isEmpty((String) licenseImg.getTag())) {
            ToastUtil.showToast("请添加营业执照!");
        } else {
            HttpRxObservable.getObservable(mActivity, true, false,
                    ApiUtils.getApiService().uploadMerchantsInfo(
                            LoginInfo.getUid(),
                            type,
                            name,
                            contactName,
                            contactMobile,
                            address.province.id,
                            address.city.id,
                            address.county.id,
                            addressStr,
                            description,
                            (String) bannerImg.getTag(),
                            (String) logoImg.getTag(),
                            (String) licenseImg.getTag()
                    ), result -> {
                        ToastUtil.showToast(result.getMsg());
                        setResult(Activity.RESULT_OK);
                        finish();
                    }).subscribe();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        String path = PictureSelector.obtainMultipleResult(data).get(0).getPath();
        ImageView imageView = null;
        switch (requestCode) {
            case REQUEST_CODE_SELECT_LOGO:
                imageView = logoImg;
                break;
            case REQUEST_CODE_SELECT_BANNER:
                imageView = bannerImg;
                break;
            case REQUEST_CODE_SELECT_LICENSE:
                imageView = licenseImg;
                break;
        }
        File file = new File(path);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("img", file.getName(), requestFile);
        RequestBody uid = RequestBody.create(MediaType.parse("multipart/form-data"), LoginInfo.getUid());
        ImageView finalImageView = imageView;
        HttpRxObservable.getObservable(mActivity, true, false,
                ApiUtils.getApiService().uploadImg(uid, body),
                result -> {
                    finalImageView.setTag(path);
                    BitmapUtil.recycle(finalImageView);
                    finalImageView.setImageBitmap(BitmapFactory.decodeFile(path));
                }).subscribe();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_join_info_upload;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BitmapUtil.recycle(logoImg);
        BitmapUtil.recycle(bannerImg);
        BitmapUtil.recycle(licenseImg);
    }

    public static void start(Activity activity, User.Role.Type type, int requestCode) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_TYPE, type.typeInt);
        ActivityUtils.startActivityForResult(bundle, activity, JoinInfoUploadActivity.class, requestCode);
    }
}
