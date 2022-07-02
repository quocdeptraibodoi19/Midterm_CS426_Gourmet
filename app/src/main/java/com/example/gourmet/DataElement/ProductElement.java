package com.example.gourmet.DataElement;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

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
    float Price;
    @Nullable
    @ColumnInfo(name = "ProductDescription")
    String ProductDescription;
    @NonNull
    @ColumnInfo(name = "Category")
    String Category;
    @NonNull
    @ColumnInfo(name = "DataUnit")
    String DataUnit;

    public ProductElement(){
        ProductID = -1;
        NameProduct = "Undefined";
        ImageUrl = "Undefined";
        Price = -1;
        ProductDescription = "Undefined";
        Category = "Undefined";
        DataUnit = "undefined";
    }
    public ProductElement(int ProductId, String NameProduct, String ImageUrl, float Price, String ProductDescription,String Category,String DataUnit){
        this.ProductID = ProductId;
        this.NameProduct = NameProduct;
        this.ImageUrl = ImageUrl;
        this.Price = Price;
        this.ProductDescription = ProductDescription;
        this.Category = Category;
        this.DataUnit = DataUnit;
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

    public void setPrice(@NonNull float price) {
        Price = price;
    }

    public void setProductDescription(@Nullable String productDescription) {
        ProductDescription = productDescription;
    }

    public void setCategory(@NonNull String category) {
        this.Category = category;
    }

    @NonNull
    public String getDataUnit() {
        return DataUnit;
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
    public float getPrice() {
        return Price;
    }

    @Nullable
    public String getProductDescription() {
        return ProductDescription;
    }

    @NonNull
    public String getCategory() {
        return Category;
    }

    public void setDataUnit(@NonNull String dataUnit) {
        DataUnit = dataUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductElement that = (ProductElement) o;
        return ProductID == that.ProductID && Float.compare(that.Price, Price) == 0 && NameProduct.equals(that.NameProduct) && ImageUrl.equals(that.ImageUrl) && Objects.equals(ProductDescription, that.ProductDescription) && Category.equals(that.Category) && DataUnit.equals(that.DataUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ProductID, NameProduct, ImageUrl, Price, ProductDescription, Category, DataUnit);
    }
}
