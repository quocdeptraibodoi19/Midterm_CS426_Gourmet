package com.example.gourmet;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import androidx.lifecycle.LiveData;

import java.util.List;

@Dao
public interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.FAIL)
    void InsertTransactionDao(TransactionElement transactionElement);
    @Delete
    void DeleteTransactionDao(TransactionElement transactionElement);

    @Query("Select * from transaction_table")
    LiveData<List<TransactionElement>> GetTransactionElementList();

    @Query("Select * from StoreTable where StoreTable.StoreID in (select Transaction_table.StoreID from Transaction_table where Transaction_table.TransactionID = :TransactionID)")
    StoreElement GetStoreElement(int TransactionID);
}
