package com.zskjprojectj.andoubusinessside.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {

    public String order_sn;
    public String mname;
    public String logo_img;
    public String id;
    public int status;
    public String shipping_free;
    public String express_id;
    public String courier_num;
    public double order_money;
    public String created_at;


    public ArrayList<Goods> details;

    public class Goods implements Serializable {
        public String goods_id;
        public String merchant_id;
        public double price;
        public String num;
        public ArrayList<String> attr_value;
        public String name;
        public String img;
    }

    public enum STATE {
        ALL(0, "全部"),
        DAI_FU_KUAN(10, "待付款"),
        DAI_FA_HUO(20, "待发货"),
        YI_FA_HUO(40, "已发货"),
        YI_PING_JIA(60, "已评价"),
        JIAO_YI_CHENG_GONG(50, "交易成功");

        public int stateInt;
        public String stateStr;

        STATE(int stateInt, String stateStr) {
            this.stateInt = stateInt;
            this.stateStr = stateStr;
        }

        public static STATE findState(int status) {
            for (STATE value : STATE.values()) {
                if (value.stateInt == status) {
                    return value;
                }
            }
            return ALL;
        }
    }
}
