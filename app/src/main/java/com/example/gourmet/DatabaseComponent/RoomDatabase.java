package com.example.gourmet.DatabaseComponent;

import android.app.Application;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;

import com.example.gourmet.DataElement.ProductElement;
import com.example.gourmet.DataElement.StoreElement;
import com.example.gourmet.DataElement.TransactionDetailElement;
import com.example.gourmet.DataElement.TransactionElement;

@Database(entities = {ProductElement.class, StoreElement.class, TransactionElement.class, TransactionDetailElement.class},version = 1,exportSchema = false)
public abstract class RoomDatabase extends android.arch.persistence.room.RoomDatabase {
    public abstract ProductDao productDao();
    public abstract StoreDao storeDao();
    public abstract TransactionDao transactionDao();
    public abstract TransactionDetailDao transactionDetailDao();
    public static RoomDatabase instance;
    public static RoomDatabase getInstance(Application application){
        if(instance == null){
            synchronized (RoomDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(application,RoomDatabase.class,"GourmetRoomDatabase")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}
