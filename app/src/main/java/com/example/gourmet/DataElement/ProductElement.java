package com.example.gourmet.DataElement;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@Entity(tableName = "ProductTable")
public class ProductElement {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ProductID")
    int ProductID;
    @NonNull
    @ColumnInfo(name = "NameProduct")
    String NameProduct;
    @NonNull
    @ColumnInfo(name = "ImageUrl")
    String ImageUrl;
    @NonNull
    @ColumnInfo(name = "Price")
    String Price; // Price has the format "x.0000/ygr"
    @Nullable
    @ColumnInfo(name = "ProductDescription")
    String ProductDescription;

    ProductElement(int ProductId, String NameProduct, String ImageUrl, String Price, String ProductDescription){
        this.ProductID = ProductId;
        this.NameProduct = NameProduct;
        this.ImageUrl = ImageUrl;
        this.Price = Price;
        this.ProductDescription = ProductDescription;
    }

    public int getProductID() {
        return ProductID;
    }

    @NonNull
    public String getNameProduct() {
        return NameProduct;
    }

    @NonNull
    public String getImageUrl() {
        return ImageUrl;
    }

    @NonNull
    public String getPrice() {
        return Price;
    }

    @Nullable
    public String getProductDescription() {
        return ProductDescription;
    }
}
