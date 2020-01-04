package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.blankj.utilcode.util.ActivityUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zskjprojectj.andoubusinessside.model.OrderT;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;
import com.zskjprojectj.andoubusinessside.utils.ToastUtil;

import static com.zskjprojectj.andoubusinessside.activity.OrderInfoActivity.KEY_ORDER;

public class ReviewDetailActivity extends BaseActivity {
    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "评价详情");
        OrderT orderT = (OrderT) getIntent().getSerializableExtra(KEY_ORDER);
        findViewById(R.id.deleteReviewBtn).setOnClickListener(view -> showDeleteDialog(orderT));
        orderT.setReviewAvatar("https://himg2.huanqiucdn.cn/attachment2010/2019/1214/20191214071048532.jpg");
        orderT.setReviewContent("评价内容评价内容评价内容评价内容评价内容评价内容评价内容评价内容评价内容评价内容评价内容评价内容评价内容评价内容评价内容");
        orderT.setReviewDate(System.currentTimeMillis());
        orderT.setReviewName("王杨");
        orderT.setReviewRate(4);
        progressBar = findViewById(R.id.progressBar);
        progressBar.postDelayed(() -> progressBar.setVisibility(View.GONE), 1000);

        ((TextView) findViewById(R.id.nameTxt)).setText(orderT.getReviewName());
        ((TextView) findViewById(R.id.reviewContentTxt)).setText(orderT.getReviewContent());
        ((TextView) findViewById(R.id.dateTxt)).setText(FormatUtil.getDateString2(orderT.getReviewDate()));
        Glide.with(this)
                .load(orderT.getReviewAvatar())
                .apply(RequestOptions.circleCropTransform().placeholder(R.mipmap.temp1))
                .into((ImageView) findViewById(R.id.avatarImg));
//        ((ScaleRatingBar) findViewById(R.id.ratingBar)).setRating(orderT.getReviewRate());
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_review_detail;
    }

    private void showDeleteDialog(OrderT orderT) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_delete_review_dialog, null);
        AlertDialog dialog = new AlertDialog.Builder(this).setView(view).show();
        view.findViewById(R.id.closeBtn).setOnClickListener(view1 -> dialog.dismiss());
        view.findViewById(R.id.cancelBtn).setOnClickListener(view1 -> dialog.dismiss());
        ((TextView) view.findViewById(R.id.reviewContentTxt)).setText(orderT.getReviewContent());
        view.findViewById(R.id.confirmBtn).setOnClickListener(view1 -> {
            dialog.dismiss();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.postDelayed(() -> {
                progressBar.setVisibility(View.GONE);
                ToastUtil.showToast("删除成功");
                finish();
                //TODO 删除后得逻辑是否显示已经删除
            }, 1000);
        });
    }

    public static void start(Activity activity, Order order, int requestCode) {
        Intent intent = new Intent(activity, ReviewDetailActivity.class);
        intent.putExtra(KEY_ORDER, order);
        ActivityUtils.startActivityForResult(activity, intent, requestCode);
    }
}
