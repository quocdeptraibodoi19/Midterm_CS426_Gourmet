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
    @ColumnInfo(name = "TransactionID")
    private int ID;
    @NonNull
    @ColumnInfo(name = "TransactionDate")
    private String TransDate;
    @NonNull
    @ColumnInfo(name = "StoreID")
    private int StoreID;
    @ColumnInfo(name = "NameUser")
    private String nameUser;
    @ColumnInfo(name = "PhoneUser")
    private String phoneUser;
    @ColumnInfo(name = "AddressUser")
    private String addressUser;
    @ColumnInfo(name = "Total")
    private float total;
    public TransactionElement(){
        ID = -1;
         TransDate = "Undefined";
        StoreID  =  -1;

    }

    public TransactionElement(@NonNull String transDate, int storeID) {
        TransDate = transDate;
        StoreID = storeID;
    }

    public TransactionElement(@NonNull String transDate, int storeID, String nameUser, String phoneUser, String addressUser,float total) {
        TransDate = transDate;
        StoreID = storeID;
        this.nameUser = nameUser;
        this.phoneUser = phoneUser;
        this.addressUser = addressUser;
        this.total = total;
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

    public String getNameUser() {
        return nameUser;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public String getAddressUser() {
        return addressUser;
    }

    public float getTotal() {
        return total;
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

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }

    public void setAddressUser(String addressUser) {
        this.addressUser = addressUser;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
