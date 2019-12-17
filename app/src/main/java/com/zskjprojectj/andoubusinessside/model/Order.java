package com.zskjprojectj.andoubusinessside.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private String num;
    private String state;
    private String icon;
    private String title;
    private String spec;
    private float price;
    private int count;
    private float total;
    private Long date;
    private String receiver;
    private String mobile;
    private String addr;
    private float score;
    private float freight;
    private String reviewName;
    private String reviewAvatar;
    private long reviewDate;
    private float reviewRate;
    private String reviewContent;
    private ArrayList<String> reviewPics;

    public String getReviewName() {
        return reviewName;
    }

    public void setReviewName(String reviewName) {
        this.reviewName = reviewName;
    }

    public String getReviewAvatar() {
        return reviewAvatar;
    }

    public void setReviewAvatar(String reviewAvatar) {
        this.reviewAvatar = reviewAvatar;
    }

    public long getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(long reviewDate) {
        this.reviewDate = reviewDate;
    }

    public float getReviewRate() {
        return reviewRate;
    }

    public void setReviewRate(float reviewRate) {
        this.reviewRate = reviewRate;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public ArrayList<String> getReviewPics() {
        return reviewPics;
    }

    public void setReviewPics(ArrayList<String> reviewPics) {
        this.reviewPics = reviewPics;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getFreight() {
        return freight;
    }

    public void setFreight(float freight) {
        this.freight = freight;
    }
}
