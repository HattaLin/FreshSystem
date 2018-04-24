package com.hand.bean;

import java.util.Date;

public class Category {
    private int c_id;
    private String c_name;
    private String place;
    private Date c_create_date;
    private int type;

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getC_create_date() {
        return c_create_date;
    }

    public void setC_create_date(Date c_create_date) {
        this.c_create_date = c_create_date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}
