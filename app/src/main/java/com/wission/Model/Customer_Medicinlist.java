package com.wission.Model;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import java.io.Serializable;


public class Customer_Medicinlist implements Serializable {

    //Id auto generate
    @PrimaryKey(autoGenerate = true)
    private int m_id;

    @ColumnInfo(name = "medicine_name")
    private String medicine_name;
    @ColumnInfo(name = "medicine_qty")
    private String medicine_qty;
    @ColumnInfo(name = "medicine_price")
    private String medicine_price;

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public String getMedicine_qty() {
        return medicine_qty;
    }

    public void setMedicine_qty(String medicine_qty) {
        this.medicine_qty = medicine_qty;
    }

    public String getMedicine_price() {
        return medicine_price;
    }

    public void setMedicine_price(String medicine_price) {
        this.medicine_price = medicine_price;
    }
}
