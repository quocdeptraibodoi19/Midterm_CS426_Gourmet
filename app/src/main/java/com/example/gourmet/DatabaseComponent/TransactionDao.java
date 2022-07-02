package com.example.gourmet.DatabaseComponent;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.gourmet.DataElement.StoreElement;
import com.example.gourmet.DataElement.TransactionElement;

import java.util.List;

@Dao
public interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertTransactionDao(TransactionElement transactionElement);
    @Delete
    void DeleteTransactionDao(TransactionElement transactionElement);

    @Query("Select * from transaction_table")
    LiveData<List<TransactionElement>> GetTransactionElementList();

    @Query("Select * from StoreTable where StoreTable.StoreID in (select Transaction_table.StoreID from Transaction_table where Transaction_table.TransactionID = :TransactionID)")
    StoreElement GetStoreElement(int TransactionID);

    @Query("Select MAX(TransactionID) from transaction_table")
    int getLastestID();
}
