package com.zskjprojectj.andoubusinessside.model;

import java.io.Serializable;

public class User implements Serializable {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    private String avatar;
    private String name;
    private int rate;
    private int todayOrderCount;

    public int getTodayfinishOrderCount() {
        return todayfinishOrderCount;
    }

    public void setTodayfinishOrderCount(int todayfinishOrderCount) {
        this.todayfinishOrderCount = todayfinishOrderCount;
    }

    private int todayfinishOrderCount;
    private int unSendCount;
    private int finishCount;
    private int unPayCount;
    private int sendedCount;
    private int refundCount;
    private int goodsCount;
    private float accountCount;
    private String state;
    private boolean isVip;
    private int type;
    private int unUseCount;
    private int cancelCount;
    private int dateOrderCount;

    public int getDateOrderCount() {
        return dateOrderCount;
    }

    public void setDateOrderCount(int dateOrderCount) {
        this.dateOrderCount = dateOrderCount;
    }

    public int getCancelCount() {
        return cancelCount;
    }

    public void setCancelCount(int cancelCount) {
        this.cancelCount = cancelCount;
    }

    public int getUnUseCount() {
        return unUseCount;
    }

    public void setUnUseCount(int unUseCount) {
        this.unUseCount = unUseCount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getTodayOrderCount() {
        return todayOrderCount;
    }

    public void setTodayOrderCount(int todayOrderCount) {
        this.todayOrderCount = todayOrderCount;
    }

    public int getUnSendCount() {
        return unSendCount;
    }

    public void setUnSendCount(int unSendCount) {
        this.unSendCount = unSendCount;
    }

    public int getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(int finishCount) {
        this.finishCount = finishCount;
    }

    public int getUnPayCount() {
        return unPayCount;
    }

    public void setUnPayCount(int unPayCount) {
        this.unPayCount = unPayCount;
    }

    public int getSendedCount() {
        return sendedCount;
    }

    public void setSendedCount(int sendedCount) {
        this.sendedCount = sendedCount;
    }

    public int getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(int refundCount) {
        this.refundCount = refundCount;
    }

    public int getItemCount() {
        return goodsCount;
    }

    public void setItemCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public float getAccountCount() {
        return accountCount;
    }

    public void setAccountCount(float accountCount) {
        this.accountCount = accountCount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }
}
