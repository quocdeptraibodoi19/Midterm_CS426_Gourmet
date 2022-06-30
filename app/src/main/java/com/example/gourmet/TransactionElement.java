package com.example.gourmet;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@Entity(tableName = "Transaction_table",foreignKeys = {@ForeignKey(entity = StoreElement.class,parentColumns = "StoreID",childColumns = "StoreID",onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE)})
public class TransactionElement {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "TransactionID")
    int ID;
    @NonNull
    @ColumnInfo(name = "TransactionDate")
    String TransDate;
    @Nullable
    @ColumnInfo(name = "TransactionID")
    int TransDetailID;
    @NonNull
    @ColumnInfo(name = "StoreID")
    int StoreID;

    public TransactionElement(int ID, @NonNull String transDate, int transDetailID, int storeID) {
        this.ID = ID;
        TransDate = transDate;
        TransDetailID = transDetailID;
        StoreID = storeID;
    }

    public int getID() {
        return ID;
    }

    @NonNull
    public String getTransDate() {
        return TransDate;
    }

    public int getTransDetailID() {
        return TransDetailID;
    }

    public int getStoreID() {
        return StoreID;
    }
}
