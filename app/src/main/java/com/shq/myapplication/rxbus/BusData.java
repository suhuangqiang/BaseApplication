package com.shq.myapplication.rxbus;

/**
 * RxBus data
 * Created by gorden on 2016/7/8.
 */
public class BusData {
    String id;
    String status;
    int position;
    public BusData() {}
    public BusData(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
