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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import com.willy.ratingbar.ScaleRatingBar;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.adapter.BaseAdapter;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.RequestUtil;
import com.zskjprojectj.andoubusinessside.model.LoginInfo;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zskjprojectj.andoubusinessside.model.Review;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.ScreenUtil;
import com.zskjprojectj.andoubusinessside.utils.ToastUtil;
import com.zskjprojectj.andoubusinessside.utils.UrlUtil;

import static com.zskjprojectj.andoubusinessside.activity.OrderDetailActivity.KEY_ORDER;

public class ReviewDetailActivity extends BaseActivity {

    ReviewImgAdapter adapter = new ReviewImgAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "评价详情");
        adapter.bindToRecyclerView(findViewById(R.id.recyclerView));
        Order order = (Order) getIntent().getSerializableExtra(KEY_ORDER);
        RequestUtil.request(mActivity, true, true,
                () -> ApiUtils.getApiService().reviewDetail(
                        LoginInfo.getUid(),
                        LoginInfo.getMerchantId(),
                        order.order_sn,
                        LoginInfo.getType()
                ), result -> {
                    ((TextView) findViewById(R.id.nameTxt)).setText(result.data.name);
                    ((TextView) findViewById(R.id.reviewContentTxt)).setText(result.data.content);
                    ((TextView) findViewById(R.id.dateTxt)).setText(result.data.created_at);
                    Glide.with(mActivity)
                            .load(UrlUtil.getImageUrl(result.data.avator))
                            .apply(RequestOptions.circleCropTransform().placeholder(R.mipmap.temp1))
                            .into((ImageView) findViewById(R.id.avatarImg));
                    adapter.setNewData(result.data.image);
                    ((ScaleRatingBar) findViewById(R.id.ratingBar)).setRating(result.data.stars);
                    findViewById(R.id.deleteReviewBtn).setOnClickListener(v ->
                            showDeleteDialog(result.data));
                });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_review_detail;
    }

    private void showDeleteDialog(Review review) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_delete_review_dialog, null);
        AlertDialog dialog = new AlertDialog.Builder(this).setView(view).show();
        view.findViewById(R.id.closeBtn).setOnClickListener(view1 -> dialog.dismiss());
        view.findViewById(R.id.cancelBtn).setOnClickListener(view1 -> dialog.dismiss());
        ((TextView) view.findViewById(R.id.reviewContentTxt)).setText(review.content);
        view.findViewById(R.id.confirmBtn).setOnClickListener(view1 -> {
            dialog.dismiss();
            RequestUtil.request(mActivity, true, false,
                    () -> ApiUtils.getApiService().delReview(
                            LoginInfo.getUid(),
                            LoginInfo.getMerchantId(),
                            review.id
                    ), result -> {
                        ToastUtil.showToast(result.getMsg());
                        setResult(Activity.RESULT_OK);
                        finish();
                    });
        });
    }

    public static void start(Activity activity, Order order, int requestCode) {
        Intent intent = new Intent(activity, ReviewDetailActivity.class);
        intent.putExtra(KEY_ORDER, order);
        ActivityUtils.startActivityForResult(activity, intent, requestCode);
    }

    class ReviewImgAdapter extends BaseAdapter<String> {
        public ReviewImgAdapter() {
            super(R.layout.layout_review_img);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            Glide.with(mActivity)
                    .load(item)
                    .apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(ScreenUtil.dp2px(mActivity, 5)))
                            .placeholder(R.mipmap.ic_placeholder))
                    .into((ImageView) helper.itemView.findViewById(R.id.img));
        }
    }
}
