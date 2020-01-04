package com.zskjprojectj.andoubusinessside.http;

import java.util.List;

public class RoleInfoResponse {

    public RoleInfo info;

    public class RoleInfo {
        public String name;
        public String logo_img;
        public int stars_all;
    }

    public String payment;
    public String today;
    public String deliver;
    public String all;
    public String affirm;
    public String manage;
    public String cancel;
    public String shipments;
    public List<Balance> balance;

    public class Balance {
        public double money;
    }
}
