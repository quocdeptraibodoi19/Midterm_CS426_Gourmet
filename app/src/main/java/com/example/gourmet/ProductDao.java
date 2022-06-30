package com.example.gourmet;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import androidx.lifecycle.LiveData;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void InsertProductDao(ProductElement productElement);
    @Delete
    void DeleteProductDao(ProductElement productElement);

    @Query("Select * from ProductTable")
    LiveData<List<ProductElement>> GetListProductElement();
}
