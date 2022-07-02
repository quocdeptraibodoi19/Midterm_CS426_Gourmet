package com.example.gourmet.DatabaseComponent;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.gourmet.DataElement.ProductElement;
import com.example.gourmet.DataElement.StoreElement;
import com.example.gourmet.DataElement.TransactionDetailElement;
import com.example.gourmet.DataElement.TransactionElement;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ProductElement.class, StoreElement.class, TransactionElement.class, TransactionDetailElement.class},version = 4,exportSchema = false)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {
    public abstract ProductDao productDao();
    public abstract StoreDao storeDao();
    public abstract TransactionDao transactionDao();
    public abstract TransactionDetailDao transactionDetailDao();
    public static RoomDatabase instance;
    public static RoomDatabase getInstance(Application application){
        if(instance == null){
            synchronized (RoomDatabase.class){
                if(instance == null){
                    Log.d("Thi", "onCreate: populate database");
                    instance = Room.databaseBuilder(application,RoomDatabase.class,"GourmetRoomDatabase")
                            .createFromAsset("Database/Product.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProductDao dao;
        PopulateDbAsyncTask(RoomDatabase instance) {
            dao = instance.productDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            ProductElement productElement = new ProductElement(1,"Cà chua Đà Lạt","https://bizweb.dktcdn.net/100/021/951/products/tomatoes1-550x.jpg?v=1626769632373",25000,"Lorem ipsum, dolor sit amet consectetur adipisicing elit. Sit pariatur odio obcaecati iusto veritatis modi est explicabo doloremque veniam omnis aliquam deserunt, laboriosam quos hic repellat ratione vel. Aspernatur, omnis.","vegetable","500gr");
            Log.d("Thi", "doInBackground: "+productElement.getNameProduct());
            dao.InsertProductDao(productElement);
            return null;
        }
    }
}
