package com.example.gourmet.DatabaseComponent;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.gourmet.DataElement.ProductElement;
import com.example.gourmet.DataElement.StoreElement;
import com.example.gourmet.DataElement.TransactionDetailElement;
import com.example.gourmet.DataElement.TransactionElement;
import com.example.gourmet.DataElement.TransactionSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Repository {
    private final ProductDao productDao;
    private final StoreDao storeDao;
    private final TransactionDao transactionDao;
    private final TransactionDetailDao transactionDetailDao;
    public Repository(Application application){
        RoomDatabase roomDatabase = RoomDatabase.getInstance(application);
        productDao = roomDatabase.productDao();
        storeDao = roomDatabase.storeDao();
        transactionDetailDao = roomDatabase.transactionDetailDao();
        transactionDao = roomDatabase.transactionDao();
    }
    // ProductDAO
    public ProductElement getProduct_ID_Without_Livedata(int id) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Callable<ProductElement> productElementCallable = new Callable<ProductElement>() {
            @Override
            public ProductElement call() throws Exception {
                return productDao.getProductById_WithoutLiveData(id);
            }
        };
        Future<ProductElement> productElementFuture = service.submit(productElementCallable);
        return productElementFuture.get();
    }
    public LiveData<List<ProductElement>> getProductList(){
        return productDao.getListProductElement();
    }
    public void InsertProduct(ProductElement productElement){
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                productDao.InsertProductDao(productElement);
            }
        });
        service.shutdown();
    }
    public void DeleteProduct(ProductElement productElement){
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                productDao.DeleteProductDao(productElement);
            }
        });
        service.shutdown();
    }

    public LiveData<ProductElement> getProductById(int id){
        return productDao.getProductById(id);
    }

    public LiveData<List<ProductElement>> getProductByCategory(String category){
        return productDao.getProductsByCategory(category);
    }

    public LiveData<List<ProductElement>> getProductByNameCategory(String patternName,String category){
        return productDao.getProductByNameCategory(patternName,category);
    }

    public LiveData<List<ProductElement>> getProductByNameAll(String patternName){
        return productDao.getProductByNameAll(patternName);
    }

    // StoreDAO
    public LiveData<List<StoreElement>> getStoreList() {
        return storeDao.GetListStoreElement();
    }
    public void InsertStoreDao(StoreElement storeElement){
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                storeDao.InsertStoreDao(storeElement);
            }
        });
        service.shutdown();
    }
    public void DeleteStoreDao(StoreElement storeElement){
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                storeDao.DeleteStoreDao(storeElement);
            }
        });
        service.shutdown();
    }

//    // TransactionDAO
    public LiveData<List<TransactionElement>> GetTransactionList(){
        return transactionDao.GetTransactionElementList();
    }
    public StoreElement GetStoreOfTransaction(int transactionID){
        ExecutorService service = Executors.newSingleThreadExecutor();
        Callable<StoreElement> callable = new Callable<StoreElement>() {
            @Override
            public StoreElement call() throws Exception {
                return transactionDao.GetStoreElement(transactionID);
            }
        };
        Future<StoreElement> storeElementFuture = service.submit(callable);
        service.shutdown();
        try {
            return storeElementFuture.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    //TODO: Just want to test the runnable
    public void InsertTransaction(TransactionElement transactionElement){
//        ExecutorService service = Executors.newSingleThreadExecutor();
//        Future<?> future = service.submit(new Runnable() {
//            @Override
//            public void run() {
//                transactionDao.InsertTransactionDao(transactionElement);
//            }
//        });
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                transactionDao.InsertTransactionDao(transactionElement);
                int id = transactionDao.getLastestID();
                Log.d("Thi", "run: latestID"+ String.valueOf(id));
            }
        });
        service.shutdown();
    }
    public void DeleteTransaction(TransactionElement transactionElement){
        ExecutorService service= Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                transactionDao.DeleteTransactionDao(transactionElement);
            }
        });
        service.shutdown();
    }

    //TransactionDetailDAO
    public LiveData<List<TransactionDetailElement>> GetTransactionDetailList(){
        return transactionDetailDao.GetListTransactionDetailElementList();
    }
    public LiveData<List<TransactionDetailElement>> GetTransactionDetailList_OnTransaction(int transactionid){
        return transactionDetailDao.GetListTransactionDetailElementList_OnTransaction(transactionid);
    }
    public void DeleteTransactionDetail(TransactionDetailElement transactionDetailElement){
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                transactionDetailDao.DeleteTransactionDetailDao(transactionDetailElement);
            }
        });
        service.shutdown();
    }
    public void InsertTransactionDetail(TransactionDetailElement transactionDetailElement){
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                transactionDetailDao.InsertTransactionDetailDao(transactionDetailElement);
            }
        });
        service.shutdown();
    }
    public void InsertTransactionAndDetail(TransactionElement transactionElement){
        TransactionSingleton singleton = TransactionSingleton.getInstance();
        ExecutorService service = Executors.newSingleThreadExecutor();
            service.execute(new Runnable() {
                @Override
                public void run() {
                    transactionDao.InsertTransactionDao(transactionElement);
                    int id = transactionDao.getLastestID();
                    Log.d("min", "run: new id+ "+ String.valueOf(id));
                    TransactionDetailElement transactionDetailElement;
                    ArrayList<ProductElement> productElementArrayList = singleton.getProductElementArrayList();
                    for(int i=0;i< productElementArrayList.size();i++){
                        int productId = productElementArrayList.get(i).getProductID();
                        transactionDetailElement = new TransactionDetailElement(id,productId, singleton.getNumberProduct_Id(productId));
                        transactionDetailDao.InsertTransactionDetailDao(transactionDetailElement);
                    }
                    singleton.clear();
                    Log.d("min", "run: "+ singleton.getAddress());
                }
            });
            service.shutdown();
    }
}
