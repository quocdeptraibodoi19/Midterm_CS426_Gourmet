package com.example.gourmet.DataElement;

public class TransactionProductUnit {
    private ProductElement productElement;
    private int numProduct;

    public TransactionProductUnit(ProductElement productElement, int numProduct) {
        this.productElement = productElement;
        this.numProduct = numProduct;
    }

    public ProductElement getProductElement() {
        return productElement;
    }

    public int getNumProduct() {
        return numProduct;
    }
}
