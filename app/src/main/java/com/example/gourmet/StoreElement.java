package com.example.gourmet;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import androidx.annotation.NonNull;

@Entity(tableName = "StoreTable")
public class StoreElement {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "StoreID")
    int StoreID;
    @NonNull
    @ColumnInfo(name = "Address")
    String Address;
    @NonNull
    @ColumnInfo(name = "MapCode")
    String MapCode;
    StoreElement(int StoreID, String Address, String Mapcode){
        this.StoreID = StoreID;
        this.Address = Address;
        this.MapCode = Mapcode;
    }
    String getAddress(){
        return Address;
    }
    String getMapCode(){
        return MapCode;
    }
}
