package com.example.brookeandcoapp.model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.Hashtable;

public class Order implements KvmSerializable {
    public int id;
    public int clientId;
    public String order_date;
    public String status; // cart/wishlist/ongoing/paid
    public double subtotal;
    public ArrayList<Entry> entries;
    public String delivery_date;
    public double total;
    public String transaction;
    public Address deliveryAddress;


    public Order(){};

    public Order(int id, int clientId, String order_date, String status, double subtotal, ArrayList<Entry> entries,
                 String delivery_date, double total, String transaction, Address deliveryAddress) {
        super();
        this.id = id;
        this.clientId = clientId;
        this.order_date = order_date;
        this.status = status;
        this.subtotal = subtotal;
        this.entries = entries;
        this.delivery_date = delivery_date;
        this.total = total;
        this.transaction = transaction;
        this.deliveryAddress = deliveryAddress;
    }

    public Order(int id, int clientId, String status) {
        this.id = id;
        this.clientId = clientId;
        this.status = status;
        this.entries = new ArrayList<Entry>();
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", clientId=" + clientId + ", order_date=" + order_date + ", status=" + status
                + ", subtotal=" + subtotal + ", entries=" + entries + ", delivery_date=" + delivery_date + ", total="
                + total + ", transaction=" + transaction + ", deliveryAddress=" + deliveryAddress + "]";
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getOrder_id() {
        return id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    @Override
    public Object getProperty(int i) {
        switch (i){
            case 0:
                return id;
            case 1:
                return clientId;
            case 2:
                return order_date;
            case 3:
                return status;
            case 4:
                return subtotal;
            case 6:
                return delivery_date;
            case 7:
                return total;
            case 8:
                return transaction;
            default:
                return null;
        }
    }

    @Override
    public int getPropertyCount() {
        return 10;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch(i){
            case 0:
                id = Integer.parseInt(o.toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch(i){
            case 0:
                propertyInfo.name = "id";
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                break;
            case 1:
                propertyInfo.name = "clientId";
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                break;
            case 2:
                propertyInfo.name = "order_date";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            case 3:
                propertyInfo.name = "status";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            case 4:
                propertyInfo.name = "subtotal";
                propertyInfo.type = Double.class;
                break;
            case 5:
                propertyInfo.name = "entries";
                propertyInfo.type = ArrayList.class;
                break;
            case 6:
                propertyInfo.name = "delivery_date";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            case 7:
                propertyInfo.name = "total";
                propertyInfo.type = Double.class;
                break;
            case 8:
                propertyInfo.name = "transaction";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            case 9:
                propertyInfo.name = "deliveryAddress";
                propertyInfo.type = Address.class;
                break;
            default:
                break;
        }
    }
}
