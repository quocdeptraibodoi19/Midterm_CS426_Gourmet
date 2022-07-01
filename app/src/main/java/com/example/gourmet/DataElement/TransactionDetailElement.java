package com.example.gourmet.DataElement;



import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "TransactionDetailTable",primaryKeys = {"TransactionID","ProductID"}
        ,foreignKeys ={@ForeignKey(entity = TransactionElement.class,parentColumns = "TransactionID",childColumns = "TransactionID",onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE),
                        @ForeignKey(entity = ProductElement.class, parentColumns = "ProductID", childColumns = "ProductID",onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE)})
public class TransactionDetailElement {
    @NonNull
    @ColumnInfo(name = "TransactionID")
    int TransactionID;
    @NonNull
    @ColumnInfo(name = "ProductID")
    int ProductID;
    @NonNull
    @ColumnInfo(name = "NumberOfProduct")
    int NumOfProduct;

    public TransactionDetailElement(){
        TransactionID = -1;
        ProductID = -1;
        NumOfProduct = -1;
    }

    public TransactionDetailElement(int transactionID, int productID, int numOfProduct) {
        TransactionID = transactionID;
        ProductID = productID;
        NumOfProduct = numOfProduct;
    }

    public int getTransactionID() {
        return TransactionID;
    }

    public int getProductID() {
        return ProductID;
    }

    public int getNumOfProduct() {
        return NumOfProduct;
    }

    public void setTransactionID(int transactionID) {
        TransactionID = transactionID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public void setNumOfProduct(int numOfProduct) {
        NumOfProduct = numOfProduct;
    }
}
