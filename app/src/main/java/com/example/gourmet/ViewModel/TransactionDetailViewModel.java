package com.example.gourmet.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.example.gourmet.DataElement.TransactionDetailElement;
import com.example.gourmet.DataElement.TransactionElement;
import com.example.gourmet.DatabaseComponent.Repository;

public class TransactionDetailViewModel extends AndroidViewModel {
    private final Repository repository;
    public TransactionDetailViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }
    public LiveData<List<TransactionDetailElement>> getTransactionDetailTotalList(){
        return repository.GetTransactionDetailList();
    }
    public LiveData<List<TransactionDetailElement>> getTransactionDetailList_OnTransaction(int transactionID){
        return repository.GetTransactionDetailList_OnTransaction(transactionID);
    }
    public void deleteTransactionDetailElement(TransactionDetailElement transactionDetailElement){
        repository.DeleteTransactionDetail(transactionDetailElement);
    }
    public void insertTransactionDetailElement(TransactionDetailElement transactionDetailElement){
        repository.InsertTransactionDetail(transactionDetailElement);
    }
    public void insertTransactionAndDetail(TransactionElement transactionElement){
        repository.InsertTransactionAndDetail(transactionElement);
    }
}
