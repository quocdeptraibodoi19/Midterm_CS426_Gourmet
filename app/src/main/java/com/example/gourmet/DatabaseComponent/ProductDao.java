package com.example.gourmet.DatabaseComponent;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.gourmet.DataElement.ProductElement;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void InsertProductDao(ProductElement productElement);

    @Delete
    void DeleteProductDao(ProductElement productElement);

    @Query("Select * from ProductTable")
    LiveData<List<ProductElement>> getListProductElement();

    @Query("Select * from ProductTable where ProductID=:id")
    LiveData<ProductElement> getProductById(int id);
}
