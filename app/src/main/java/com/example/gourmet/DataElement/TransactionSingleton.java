package com.example.gourmet.DataElement;

import android.util.Log;

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
    private int storeid;

    private TransactionSingleton(){
        productElementArrayList = new ArrayList<>();
        number_product_map = new HashMap<>();
        nameUser = "Nguyen Van A";
        phoneNumber = "0869314924";
        address = "123/456A Nguyễn Thị Minh Khai, phường 2, quận 13";
        storeid = 1;
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

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
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
    public ArrayList<TransactionProductUnit> getTransactionProductUnitList(){
        ArrayList<TransactionProductUnit> productUnits = new ArrayList<>();
        for(int i=0; i < productElementArrayList.size(); i++)
            productUnits.add(new TransactionProductUnit(productElementArrayList.get(i),number_product_map.get(productElementArrayList.get(i).getProductID())));
        return productUnits;
    }
    public void setNumberProduct(int productId, int numberCount){
            if(numberCount == 0)
            {
                number_product_map.remove(productId);
                for(int i=0;i<productElementArrayList.size();i++)
                    if(productElementArrayList.get(i).getProductID() == productId)
                    {
                        Log.d("Test", "setNumberProduct: "+ productElementArrayList.get(i).getNameProduct());
                        productElementArrayList.remove(i);
                        break;
                    }
            }
            else number_product_map.put(productId,numberCount);
    }
    public void clear(){
        productElementArrayList = new ArrayList<>();
        number_product_map = new HashMap<>();
    }
    public float getTotalMoneyTransaction(){
        float res =0;
        for(int i=0;i<productElementArrayList.size();i++)
            res = res + productElementArrayList.get(i).getPrice()*number_product_map.get(productElementArrayList.get(i).getProductID());
        return res;
    }
}
