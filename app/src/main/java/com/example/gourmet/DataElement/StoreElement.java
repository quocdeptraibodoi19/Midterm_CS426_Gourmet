package com.example.gourmet.DataElement;



import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

    public StoreElement(){
        StoreID = -1;
        Address = "Undefined";
        MapCode = "Undefined";
    }
    public StoreElement(int StoreID, String Address, String Mapcode){
        this.StoreID = StoreID;
        this.Address = Address;
        this.MapCode = Mapcode;
    }

    public int getStoreID() {
        return StoreID;
    }

    public String getAddress(){
        return Address;
    }
    public String getMapCode(){
        return MapCode;
    }

    public void setStoreID(int storeID) {
        StoreID = storeID;
    }

    public void setAddress(@NonNull String address) {
        Address = address;
    }

    public void setMapCode(@NonNull String mapCode) {
        MapCode = mapCode;
    }
}
