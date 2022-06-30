package com.example.gourmet.DataElement;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import androidx.annotation.NonNull;

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
}
