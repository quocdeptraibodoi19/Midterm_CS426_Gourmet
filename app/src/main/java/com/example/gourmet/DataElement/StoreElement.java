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

    @ColumnInfo(name = "Latitude")
    double Latitude;

    @ColumnInfo(name = "Longitude")
    double Longitude;

    public StoreElement(){
        this.Address = "";
        this.Longitude = 0.0;
        this.Latitude = 0.0;
    }

    public StoreElement(int storeID, @NonNull String address, double latitude, double longitude) {
        StoreID = storeID;
        Address = address;
        Latitude = latitude;
        Longitude = longitude;
    }

    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int storeID) {
        StoreID = storeID;
    }

    @NonNull
    public String getAddress() {
        return Address;
    }

    public void setAddress(@NonNull String address) {
        Address = address;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }
}
