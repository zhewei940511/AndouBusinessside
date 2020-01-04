package com.zskjprojectj.andoubusinessside.model;

import java.io.Serializable;
import java.util.ArrayList;

public class UserT implements Serializable {
    public String id;
    public String token;
    public String avatar;
    public String name;
    public int rate;
    public int todayOrderCount;
    public int todayfinishOrderCount;
    public int unSendCount;
    public int finishCount;
    public int unPayCount;
    public int sendedCount;
    public int refundCount;
    public int goodsCount;
    public int itemCount;
    public double accountCount;
    public String state;
    public boolean isVip;
    public ArrayList<Type> types = new ArrayList<>();
    public int unUseCount;
    public int cancelCount;
    public int dateOrderCount;
    public Type currentType;

    public enum Type {
        MALL, HOTEL, RESTAURANT
    }
}
