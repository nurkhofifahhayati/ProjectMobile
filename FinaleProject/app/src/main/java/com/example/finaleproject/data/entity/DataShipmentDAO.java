package com.example.finaleproject.data.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataShipmentDAO {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    Long insertData(DataShipment dataShipment);

    @Query("Select * from shipment_db")
    List<DataShipment> getData();

    @Update
    int updateData(DataShipment item);

    @Delete
    void deleteData(DataShipment item);

}
