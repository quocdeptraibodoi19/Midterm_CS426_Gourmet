package com.example.gourmet.DataElement;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

    public ProductElement(){
        ProductID = -1;
        NameProduct = "Undefined";
        ImageUrl = "Undefined";
        Price = "Undefined";
        ProductDescription = "Undefined";
    }
    public ProductElement(int ProductId, String NameProduct, String ImageUrl, String Price, String ProductDescription){
        this.ProductID = ProductId;
        this.NameProduct = NameProduct;
        this.ImageUrl = ImageUrl;
        this.Price = Price;
        this.ProductDescription = ProductDescription;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public void setNameProduct(@NonNull String nameProduct) {
        NameProduct = nameProduct;
    }

    public void setImageUrl(@NonNull String imageUrl) {
        ImageUrl = imageUrl;
    }

    public void setPrice(@NonNull String price) {
        Price = price;
    }

    public void setProductDescription(@Nullable String productDescription) {
        ProductDescription = productDescription;
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
