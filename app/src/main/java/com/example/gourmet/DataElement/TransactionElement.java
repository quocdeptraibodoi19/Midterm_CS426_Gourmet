package com.example.gourmet.DataElement;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Transaction_table",foreignKeys = {@ForeignKey(entity = StoreElement.class,parentColumns = "StoreID",childColumns = "StoreID",onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE)})
public class TransactionElement {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "TransactionID")
    private int ID;
    @NonNull
    @ColumnInfo(name = "TransactionDate")
    private String TransDate;
    @NonNull
    @ColumnInfo(name = "StoreID")
    private int StoreID;

    public TransactionElement(){
        ID = -1;
         TransDate = "Undefined";
        StoreID  =  -1;

    }

    public TransactionElement(@NonNull String transDate, int storeID) {
        TransDate = transDate;
        StoreID = storeID;
    }

    public TransactionElement(int ID, @NonNull String transDate, int storeID) {
        this.ID = ID;
        TransDate = transDate;
        StoreID = storeID;
    }

    public int getID() {
        return ID;
    }

    @NonNull
    public String getTransDate() {
        return TransDate;
    }


    public int getStoreID() {
        return StoreID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTransDate(@NonNull String transDate) {
        TransDate = transDate;
    }

    public void setStoreID(int storeID) {
        StoreID = storeID;
    }
}
