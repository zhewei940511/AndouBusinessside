package com.zskjprojectj.andoubusinessside.model;

public class GoodsCategory {
    private int id;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
