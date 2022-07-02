package com.example.gourmet.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.example.gourmet.DataElement.ProductElement;
import com.example.gourmet.DatabaseComponent.Repository;

public class ProductViewModel extends AndroidViewModel {
    private final Repository repository;
    public ProductViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<ProductElement>> getProductList(){
        return repository.getProductList();
    }

    public void insertProductElement(ProductElement productElement){
        repository.InsertProduct(productElement);
    }

    public void deleteProductElement(ProductElement productElement){
        repository.DeleteProduct(productElement);
    }

    public LiveData<ProductElement> getProductById(int id){
        return repository.getProductById(id);
    }
}
