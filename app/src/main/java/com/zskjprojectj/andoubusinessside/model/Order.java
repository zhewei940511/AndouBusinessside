package com.zskjprojectj.andoubusinessside.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {

    public String img;
    public String name;
    public String goods_id;
    public String merchant_id;
    public String order_id;
    public String mname;
    public String logo_img;
    public String num;
    public String id;
    public int status;
    public String shipping_free;
    public String express_id;
    public String courier_num;
    public float price;
    public float pay_money;

    public ArrayList<String> attr_value;

    public String getSpec() {
        StringBuilder builder = new StringBuilder();
        for (String value : attr_value) {
            builder.append(value).append("+");
        }
        return builder.substring(0, builder.length() - 1);
    }

    public enum STATE {
        ALL(-1, "全部"),
        DAI_FU_KUAN(10, "待付款"),
        DAI_FA_HUO(20, "待发货"),
        YI_FA_HUO(40, "已发货"),
        YI_PING_JIA(60, "已评价");

        public int stateInt;
        public String stateStr;

        STATE(int stateInt, String stateStr) {
            this.stateInt = stateInt;
            this.stateStr = stateStr;
        }
    }
}
