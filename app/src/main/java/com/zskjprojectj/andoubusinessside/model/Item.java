package com.zskjprojectj.andoubusinessside.model;

import com.zskjprojectj.andoubusinessside.utils.ListUtil;

import java.util.ArrayList;

public class Item {
    public static final int STATE_ON = 1;
    public static final int STATE_OFF = 0;

    public String id;
    public String name;
    public String desc;
    public String img;
    public double price;
    public int is_sale;
    public String store_num;
    public ArrayList<String> attr_value;

    public static String getSpec(ArrayList<String> attr_value) {
        if (ListUtil.isEmpty(attr_value)) return "";
        StringBuilder builder = new StringBuilder();
        for (String value : attr_value) {
            builder.append(value).append("+");
        }
        return builder.substring(0, builder.length() - 1);
    }
}
