package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;

import com.zskjprojectj.andoubusinessside.R;
import com.zhuosongkj.android.library.app.BaseActivity;
import com.zhuosongkj.android.library.util.ActionBarUtil;

public class ChartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "店铺统计");
//        BarChart chart = findViewById(R.id.orderChart);
//        chart.getDescription().setEnabled(false);
//        chart.setDrawBarShadow(false);
//        chart.setTouchEnabled(false);
//        chart.setDragEnabled(false);
//        chart.setScaleEnabled(false);
//        chart.setDrawValueAboveBar(true);
//        chart.getAxisRight().setEnabled(false);
//
//
//        YAxis leftAxis = chart.getAxisLeft();
//        leftAxis.setTextSize(10);
//        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
//        leftAxis.setAxisMinimum(0f);
//        leftAxis.setTextColor(Color.parseColor("#ffa0a0a0"));
//
//        XAxis xAxis = chart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setDrawGridLines(false);
//        xAxis.setAxisLineColor(Color.parseColor("#fff0f0f0"));
//        xAxis.setEnabled(false);
//
//        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
//        for (int i = 1; i < 8; i++) {
//            ArrayList<BarEntry> values = new ArrayList<>();
//            values.add(new BarEntry(i, i * 10));
//            BarDataSet barDataSet = new BarDataSet(values, "09-0" + i);
//            barDataSet.setDrawValues(false);
//            barDataSet.setDrawIcons(false);
//            barDataSet.setColor(Color.parseColor("#5ED3AE"));
//            dataSets.add(barDataSet);
//        }
//        BarData data = new BarData(dataSets);
//        chart.setData(data);
//        data.setBarWidth(0.3f);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_chart;
    }
}
