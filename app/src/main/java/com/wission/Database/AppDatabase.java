package com.wission.Database;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.wission.Dao.Bill_roomDao;
import com.wission.Dao.User_roomDao;
import com.wission.Model.BillDetailsRoom;
import com.wission.Model.UserDetails_Room;
import com.wission.appUtils.Converters;


//Todo:Create Database,
// version = 1 :means set database version
// entities = set all class file as per set table in using this database

@Database(entities = {UserDetails_Room.class, BillDetailsRoom.class}, version = 2)
@TypeConverters({Converters.class})
public abstract  class AppDatabase extends RoomDatabase {

    //Todo: Create and set Dao file as per this database thru table under data/values insert,update,delete etc express
    public abstract User_roomDao userDao();

    public abstract Bill_roomDao billDao();
}
