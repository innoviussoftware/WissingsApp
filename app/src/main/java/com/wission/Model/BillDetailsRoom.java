package com.wission.Model;


import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.wission.appUtils.Converters;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "billdetails")
public class BillDetailsRoom implements Serializable {
    //Create required table filled name with return type

    //Id auto generate
    @PrimaryKey(autoGenerate = true)
    private int c_id;

    @ColumnInfo(name = "customer_name")
    private String customer_name;
    @ColumnInfo(name = "customer_email")
    private String customer_email;
    @ColumnInfo(name = "customer_phone")
    private String customer_phone;

    @Embedded
    @TypeConverters(Converters.class)
    private ArrayList<Customer_Medicinlist> customer_medicinlist;

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public ArrayList<Customer_Medicinlist> getCustomer_medicinlist() {
        return customer_medicinlist;
    }

    public void setCustomer_medicinlist(ArrayList<Customer_Medicinlist> customer_medicinlist) {
        this.customer_medicinlist = customer_medicinlist;
    }
}
