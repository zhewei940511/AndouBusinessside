package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.willy.ratingbar.ScaleRatingBar;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;
import com.zskjprojectj.andoubusinessside.utils.ToastUtil;

import static com.zskjprojectj.andoubusinessside.activity.OrderInfoActivity.KEY_ORDER;

public class ReviewDetailActivity extends AppCompatActivity {
    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_detail);
        findViewById(R.id.backBtn).setOnClickListener(view -> finish());
        ((TextView) findViewById(R.id.actionBarTitleTxt)).setText("评价详情");
        Order order = (Order) getIntent().getSerializableExtra(KEY_ORDER);
        findViewById(R.id.deleteReviewBtn).setOnClickListener(view -> showDeleteDialog(order));
        order.setReviewAvatar("https://himg2.huanqiucdn.cn/attachment2010/2019/1214/20191214071048532.jpg");
        order.setReviewContent("评价内容评价内容评价内容评价内容评价内容评价内容评价内容评价内容评价内容评价内容评价内容评价内容评价内容评价内容评价内容");
        order.setReviewDate(System.currentTimeMillis());
        order.setReviewName("王杨");
        order.setReviewRate(4);
        progressBar = findViewById(R.id.progressBar);
        progressBar.postDelayed(() -> progressBar.setVisibility(View.GONE), 1000);

        ((TextView) findViewById(R.id.nameTxt)).setText(order.getReviewName());
        ((TextView) findViewById(R.id.reviewContentTxt)).setText(order.getReviewContent());
        ((TextView) findViewById(R.id.dateTxt)).setText(FormatUtil.getDateString2(order.getReviewDate()));
        Glide.with(this)
                .load(order.getReviewAvatar())
                .apply(RequestOptions.circleCropTransform().placeholder(R.mipmap.temp1))
                .into((ImageView) findViewById(R.id.avatarImg));
        ((ScaleRatingBar) findViewById(R.id.ratingBar)).setRating(order.getReviewRate());
    }

    private void showDeleteDialog(Order order) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_delete_review_dialog, null);
        AlertDialog dialog = new AlertDialog.Builder(this).setView(view).show();
        view.findViewById(R.id.closeBtn).setOnClickListener(view1 -> dialog.dismiss());
        view.findViewById(R.id.cancelBtn).setOnClickListener(view1 -> dialog.dismiss());
        ((TextView) view.findViewById(R.id.reviewContentTxt)).setText(order.getReviewContent());
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
}
