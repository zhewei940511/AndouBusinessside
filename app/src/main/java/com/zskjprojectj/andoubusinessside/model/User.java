package com.zskjprojectj.andoubusinessside.model;

import java.util.ArrayList;

public class User {

    public ArrayList<Role> roles;

    public static class Role {
        public String id;
        public String name;
        public String img;
        public int merchant_type_id;

        public enum Type {
            MALL(2), HOTEL(3), RESTAURANT(4);

            public int typeInt;

            Type(int typeInt) {
                this.typeInt = typeInt;
            }
        }
    }
}
