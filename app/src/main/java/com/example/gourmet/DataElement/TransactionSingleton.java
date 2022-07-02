package com.example.gourmet.DataElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TransactionSingleton {
    private static TransactionSingleton transactionSingleton;
    private ArrayList<ProductElement> productElementArrayList;
    private Map<Integer,Integer> number_product_map;
    private String nameUser;
    private String phoneNumber;
    private String address;

    private TransactionSingleton(){
        productElementArrayList = new ArrayList<>();
        number_product_map = new HashMap<>();
        nameUser = "Nguyen Van A";
        phoneNumber = "0869314924";
        address = "123/456A Nguyễn Thị Minh Khai, phường 2, quận 13";
    }

    public static TransactionSingleton getInstance(){
        if(transactionSingleton == null)
            transactionSingleton = new TransactionSingleton();
        return transactionSingleton;
    }

    public String getNameUser() {
        return nameUser;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<ProductElement> getProductElementArrayList() {
        return productElementArrayList;
    }
    public int getNumberProduct_Id(int ProductID){
        if(number_product_map.containsKey(ProductID))
            return number_product_map.get(ProductID);
        return 0;
    }
    public void addProductElementArrayList(ProductElement productElement,int numProduct){
        if(number_product_map.containsKey(productElement.getProductID()))
            number_product_map.put(productElement.getProductID(),number_product_map.get(productElement.getProductID())+numProduct);
        else
        {
            productElementArrayList.add(productElement);
            number_product_map.put(productElement.getProductID(),numProduct);
        }
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void clear(){
        productElementArrayList = new ArrayList<>();
        number_product_map = new HashMap<>();
    }
}
