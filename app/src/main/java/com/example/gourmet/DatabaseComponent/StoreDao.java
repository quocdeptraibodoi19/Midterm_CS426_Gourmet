package com.example.gourmet.DatabaseComponent;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.gourmet.DataElement.StoreElement;

import java.util.List;

@Dao
public interface StoreDao {
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    void InsertStoreDao(StoreElement storeElement);
    @Delete
    void DeleteStoreDao(StoreElement storeElement);
    @Query("Select * from StoreTable")
    LiveData<List<StoreElement>> GetListStoreElement();
}
