package com.example.gourmet.DatabaseComponent;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import androidx.lifecycle.LiveData;

import com.example.gourmet.DataElement.TransactionDetailElement;

import java.util.List;

@Dao
public interface TransactionDetailDao {
    @Insert(onConflict = OnConflictStrategy.FAIL)
    void InsertTransactionDetailDao(TransactionDetailElement transactionDetailElement);
    @Delete
    void DeleteTransactionDetailDao(TransactionDetailElement transactionDetailElement);
    @Query("Select * from TransactionDetailTable")
    LiveData<List<TransactionDetailElement>> GetListTransactionDetailElementList();
    @Query("Select * from TransactionDetailTable inner join Transaction_table on Transaction_table.TransactionID = TransactionDetailTable.TransactionID where  TransactionDetailTable.TransactionID = :TransactionID")
    LiveData<List<TransactionDetailElement>> GetListTransactionDetailElementList_OnTransaction(int TransactionID);
}
