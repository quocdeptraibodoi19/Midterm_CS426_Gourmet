package com.example.gourmet;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StoreViewModel extends AndroidViewModel {
    private final Repository repository;
    public StoreViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }
    public LiveData<List<StoreElement>> getStoreList(){
        return repository.getStoreList();
    }
    public void insertStoreElement(StoreElement storeElement){
        repository.InsertStoreDao(storeElement);
    }
    public void deleteStoreElement(StoreElement storeElement){
        repository.DeleteStoreDao(storeElement);
    }
}
