package com.zskjprojectj.andoubusinessside.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {

    public String order_sn;
    public String mname;
    public String logo_img;
    public String id;
    public String pay_time;
    public int status;
    public int pay_way;
    public String shipping_free;
    public String express_id;
    public String courier_num;
    public double order_money;
    public String created_at;
    public String allnum;
    public double goodsPay;

    public Receiver userinfo;

    public class Receiver implements Serializable {
        public String name;
        public String address;
        public String mobile;
        public String province;
        public String city;
        public String area;

        public String getAddressDetail() {
            return province + city + area + address;
        }
    }

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

    public enum PayWay {
        NONE(0, "待付款"),
        WEI_XIN(1, "微信"),
        ZHI_FU_BAO(2, "支付宝"),
        YIN_LIAN(3, "银联"),
        YU_E_ZHI_FU(4, "余额支付"),
        OTHER(5, "其他");

        public int payWayInt;
        public String payWayStr;

        PayWay(int PayWayInt, String payWayStr) {
            this.payWayInt = PayWayInt;
            this.payWayStr = payWayStr;
        }

        public static PayWay findPayWay(int payWay) {
            for (PayWay value : PayWay.values()) {
                if (value.payWayInt == payWay) {
                    return value;
                }
            }
            return NONE;
        }
    }
}
