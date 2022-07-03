package com.example.gourmet.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import com.example.gourmet.DataElement.ProductElement;
import com.example.gourmet.DatabaseComponent.Repository;

public class ProductViewModel extends AndroidViewModel {
    private final Repository repository;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<ProductElement>> getProductList(String category){
        if(Objects.equals(category, "")){
            return repository.getProductList();
        }

        return repository.getProductByCategory(category);
    }

    public ProductElement getProductElement_ID_WithoutLiveData(int id) throws ExecutionException, InterruptedException {
        return repository.getProduct_ID_Without_Livedata(id);
    }

    public LiveData<List<ProductElement>> getProductByNameCategory(String patternName,String category){
        return repository.getProductByNameCategory(patternName,category);
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
