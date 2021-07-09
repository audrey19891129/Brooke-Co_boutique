package com.example.brookeandcoapp.model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.text.ParseException;
import java.util.Hashtable;

public class Entry implements KvmSerializable {
    private static final String ID = "id";
    private static final String ORDERID = "orderId";
    private static final String QUANTITY = "quantity";
    private static final String PRICE = "price";
    public int id;
    public int orderId;
    public Product product;
    public int quantity;
    public double price;


    public Entry() {

    }

    public Entry(int id, int orderId, Product product, int quantity, double price) {
        super();
        this.id = id;
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Entry [id=" + id + ", orderId=" + orderId + ", product=" + product + ", quantity=" + quantity
                + ", price=" + price + "]";
    }



    @Override
    public Object getProperty(int i) {
        switch (i){
            case 0:
                return id;
            case 1:
                return orderId;
            case 2:
                return product;
            case 3:
                return quantity;
            case 4:
                return price;
            default:
                break;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 5;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch(i){
            case 0:
                id = Integer.parseInt(o.toString());
                break;
            case 1:
                orderId = Integer.parseInt(o.toString());
                break;
            case 2:
                product = (Product) o;
                break;
            case 3:
                quantity = Integer.parseInt(o.toString());
                break;
            case 4:
                price = Double.parseDouble(o.toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch(i){
            case 0:
                propertyInfo.name = ID;
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                break;
            case 1:
                propertyInfo.name = ORDERID;
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                break;
            case 2:
                propertyInfo.name = "product";
                propertyInfo.type = Product.class;
                break;
            case 3:
                propertyInfo.name = QUANTITY;
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                break;
            case 4:
                propertyInfo.name = PRICE;
                propertyInfo.type = Double.class;
                break;
            default:
                break;
        }
    }
}
