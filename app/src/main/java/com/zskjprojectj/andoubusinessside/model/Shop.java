package com.zskjprojectj.andoubusinessside.model;

import java.util.ArrayList;

public class Shop {
    private String name;
    private String contact;
    private String mobile;

    private String notice;
    private String addr;

    private ArrayList<String> icons;
    private String returnCotact;
    private String returnMobile;
    private String returnAddr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public ArrayList<String> getIcons() {
        return icons;
    }

    public void setIcons(ArrayList<String> icons) {
        this.icons = icons;
    }

    public String getReturnCotact() {
        return returnCotact;
    }

    public void setReturnCotact(String returnCotact) {
        this.returnCotact = returnCotact;
    }

    public String getReturnMobile() {
        return returnMobile;
    }

    public void setReturnMobile(String returnMobile) {
        this.returnMobile = returnMobile;
    }

    public String getReturnAddr() {
        return returnAddr;
    }

    public void setReturnAddr(String returnAddr) {
        this.returnAddr = returnAddr;
    }
}
