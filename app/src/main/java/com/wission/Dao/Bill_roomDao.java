package com.wission.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wission.Model.BillDetailsRoom;
import com.wission.Model.UserDetails_Room;

import java.util.List;

@Dao
public interface Bill_roomDao {

    //Todo: Using BillDetailsRoom.class regarding set table name (tableName = "billdetails")
    @Query("SELECT * FROM billdetails")
    List<BillDetailsRoom>  getAll();

    @Insert
    void insertBillAll(BillDetailsRoom task);

    @Delete
    void deleteBillAll(BillDetailsRoom task);

    @Update
    void updateBillAll(BillDetailsRoom task);


    @Delete
    void deleteMedicine(BillDetailsRoom task);

    @Update
    void updateMedicine(BillDetailsRoom task);


}
