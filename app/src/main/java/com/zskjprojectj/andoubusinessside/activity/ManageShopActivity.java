package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.model.Shop;

public class ManageShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_shop);
        findViewById(R.id.backBtn).setOnClickListener(view -> finish());
        ((TextView) findViewById(R.id.actionBarTitleTxt)).setText("店铺管理");
        View progressBar = findViewById(R.id.progressBar);
        progressBar.postDelayed(() -> progressBar.setVisibility(View.GONE), 1000);
        Shop shop = new Shop();
        shop.setAddr("重庆市南岸区万达广场");
        shop.setContact("王杨");
        shop.setMobile("13888888888");
        shop.setName("王杨的淘宝小店");
        shop.setNotice("王杨的淘宝小店的公告王杨的淘宝小店的公告王杨的淘宝小店的公告王杨的淘宝小店的公告王杨的淘宝小店的公告王杨的淘宝小店的公告");
        shop.setReturnAddr("重庆市南岸区万达广场重庆市南岸区万达广场重庆市南岸区万达广场重庆市南岸区万达广场");
        shop.setReturnCotact("王杨");
        shop.setReturnMobile("13888888888");

        ((TextView) findViewById(R.id.addrEdt)).setText(shop.getAddr());
        ((TextView) findViewById(R.id.contactNameEdt)).setText(shop.getContact());
        ((TextView) findViewById(R.id.mobileEdt)).setText(shop.getMobile());
        ((TextView) findViewById(R.id.shopNameEdt)).setText(shop.getName());
        ((TextView) findViewById(R.id.shopNoticeEdt)).setText(shop.getNotice());
        ((TextView) findViewById(R.id.returnAddrEdt)).setText(shop.getReturnAddr());
        ((TextView) findViewById(R.id.returnContactEdt)).setText(shop.getReturnCotact());
        ((TextView) findViewById(R.id.returnMobileEdt)).setText(shop.getReturnMobile());
    }
}
