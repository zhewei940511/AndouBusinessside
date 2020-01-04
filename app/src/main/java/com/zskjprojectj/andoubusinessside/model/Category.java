package com.zskjprojectj.andoubusinessside.model;

import java.io.Serializable;

public class Category implements Serializable {
    public Category(int id) {
        this.id = id;
    }

    public int id;
    public String name;
}
