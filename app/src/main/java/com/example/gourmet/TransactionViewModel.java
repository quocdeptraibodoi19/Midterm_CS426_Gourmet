package com.example.gourmet;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TransactionViewModel extends AndroidViewModel {
    private final Repository repository;
    public TransactionViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }
    public LiveData<List<TransactionElement>> getTransactionList(){
        return repository.GetTransactionList();
    }
    public StoreElement getStoreOfTransaction(int transactionID){
        return repository.GetStoreOfTransaction(transactionID);
    }
    public void insertTransactionElement(TransactionElement transactionElement){
        repository.InsertTransaction(transactionElement);
    }
    public void deleteTransactionElement(TransactionElement transactionElement){
        repository.DeleteTransaction(transactionElement);
    }
}
