package com.zskjprojectj.andoubusinessside.utils;

import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.BaseResult;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;
import com.zskjprojectj.andoubusinessside.http.ListData;
import com.zskjprojectj.andoubusinessside.ui.RefreshHeaderView;

import io.reactivex.Observable;

public class PageLoadUtil<T> {
    public int page;
    private BaseQuickAdapter<T, BaseViewHolder> adapter;
    private SmartRefreshLayout refreshLayout;
    private BaseActivity activity;
    public ObservableProvider<T> observableProvider;
    private View loadingView;

    private PageLoadUtil(BaseActivity activity, RecyclerView recyclerView, BaseQuickAdapter<T, BaseViewHolder> adapter
            , SmartRefreshLayout refreshLayout) {
        this.activity = activity;
        this.adapter = adapter;
        this.refreshLayout = refreshLayout;
        adapter.setLoadMoreView(new LoadMoreView() {

            @Override
            public int getLayoutId() {
                return R.layout.layout_load_more_view;
            }

            @Override
            protected int getLoadingViewId() {
                return R.id.load_more_loading_view;
            }

            @Override
            protected int getLoadFailViewId() {
                return R.id.load_more_load_fail_view;
            }

            @Override
            protected int getLoadEndViewId() {
                return R.id.load_more_load_end_view;
            }
        });
        loadingView = LayoutInflater.from(activity).inflate(R.layout.layout_loading_view, null);
        startLoadingImage();
        refreshLayout.setRefreshHeader(new RefreshHeaderView(this.activity));
        refreshLayout.setOnRefreshListener(temp -> loadData(true));
        refreshLayout.setEnableLoadMore(false);
        adapter.setOnLoadMoreListener(() -> loadData(false), recyclerView);
        loadData(true);
    }

    private void startLoadingImage() {
        ((AnimationDrawable) ((ImageView) loadingView.findViewById(R.id.loadingImg)).getDrawable()).start();
        adapter.setEmptyView(loadingView);
    }

    private void loadData(boolean needRefresh) {
        startLoadingImage();
        if (observableProvider == null) return;
        if (needRefresh) {
            page = 1;
        }
        HttpRxObservable.getObservable(activity, false, false,
                observableProvider.getObservable()
                , result -> {
                    adapter.setEmptyView(R.layout.layout_empty_view);
                    page += 1;
                    if (needRefresh) {
                        if (result.data.getDataList().size() == 0) {
                            adapter.loadMoreEnd(true);
                        } else {
                            adapter.setNewData(result.data.getDataList());
                        }
                        refreshLayout.finishRefresh();
                    } else {
                        if (result.data.getDataList().size() == 0) {
                            adapter.loadMoreEnd();
                        } else {
                            adapter.addData(result.data.getDataList());
                            adapter.loadMoreComplete();
                        }
                    }
                }, msg -> {
                    if (needRefresh) {
                        refreshLayout.finishRefresh();
                        adapter.setEmptyView(R.layout.layout_retry_view);
                        adapter.getEmptyView().setOnClickListener(view -> loadData(true));
                    } else {
                        adapter.loadMoreFail();
                    }
                })
                .subscribe();
    }

    public void load() {
        loadData(true);
    }

    public static <T> PageLoadUtil<T> get(BaseActivity activity, RecyclerView recyclerView
            , BaseQuickAdapter<T, BaseViewHolder> adapter, SmartRefreshLayout refreshLayout) {
        return new PageLoadUtil<>(activity, recyclerView, adapter, refreshLayout);
    }

    public interface ObservableProvider<T> {
        Observable<BaseResult<ListData<T>>> getObservable();
    }
}
