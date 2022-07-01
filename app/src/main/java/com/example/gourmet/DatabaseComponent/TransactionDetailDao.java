package com.example.gourmet.DatabaseComponent;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

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
