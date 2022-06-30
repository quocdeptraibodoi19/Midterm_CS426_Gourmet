package com.example.gourmet.DatabaseComponent;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import androidx.lifecycle.LiveData;

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
