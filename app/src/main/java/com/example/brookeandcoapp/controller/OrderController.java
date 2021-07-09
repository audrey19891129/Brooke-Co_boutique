package com.example.brookeandcoapp.controller;

import android.util.Log;

import com.example.brookeandcoapp.Interface.SoapRequest;
import com.example.brookeandcoapp.model.Entry;
import com.example.brookeandcoapp.model.Order;
import com.example.brookeandcoapp.model.Product;
import com.example.brookeandcoapp.model.User;

import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class OrderController {

    public static ArrayList<Order> getOrdersByClientId(int clientId) throws IOException, XmlPullParserException {

        SoapObject Orders = SoapRequest.getOrdersByClientId(clientId);
        ArrayList<Order> orders_list = new ArrayList<>();
        //Log.e("COUNT", String.valueOf( Orders.getPropertyCount()));

        for (int i = 0; i < Orders.getPropertyCount(); i++) {
            SoapObject Order = (SoapObject) Orders.getProperty(i);
            Order order = new Order();
            order.setId(Integer.parseInt(Order.getProperty("id").toString()));
            order.setClientId(Integer.parseInt(Order.getProperty("clientId").toString()));
            order.setStatus(Order.getProperty("status").toString());
            order.setOrder_date(Order.getProperty("order_date").toString());
            order.setDelivery_date(Order.getProperty("delivery_date").toString());
            order.setTransaction(Order.getProperty("transaction").toString());
            order.setSubtotal(Double.parseDouble((Order.getProperty("subtotal").toString())));
            order.setTotal(Double.parseDouble((Order.getProperty("total").toString())));
            order.setDeliveryAddress(User.getUser().getAddresses().get(0));

            SoapObject Entries = (SoapObject) Order.getProperty("entries");
            ArrayList<Entry> entries_list = new ArrayList<>();
            for (int j = 0; j < Entries.getPropertyCount(); j++) {
                SoapObject Entry = (SoapObject) Entries.getProperty(j);
                Entry entry = new Entry();
                entry.setId(Integer.parseInt(Entry.getProperty("id").toString()));
                entry.setOrderId(Integer.parseInt(Entry.getProperty("orderId").toString()));
                entry.setQuantity(Integer.parseInt(Entry.getProperty("quantity").toString()));
                entry.setPrice(Double.parseDouble(Entry.getProperty("price").toString()));

                SoapObject Product = (SoapObject) Entry.getProperty("product");
                com.example.brookeandcoapp.model.Product product = new Product();
                product.setId(Integer.parseInt(Product.getProperty("id").toString()));
                product.setPcode(Product.getProperty("pcode").toString());
                product.setPicture(Product.getProperty("picture").toString());
                product.setType(Product.getProperty("type").toString());
                product.setTitle(Product.getProperty("title").toString());
                product.setCategory(Product.getProperty("category").toString());
                product.setInventory(Integer.parseInt(Product.getProperty("inventory").toString()));
                product.setGenre(Product.getProperty("genre").toString());
                product.setPrice(Double.parseDouble(Product.getProperty("price").toString()));

                entry.setProduct(product);
                //Log.e("ENTRY", entry.toString());
                entries_list.add(entry);
            }
            order.setEntries(entries_list);
            orders_list.add(order);
            //Log.e("ORDER", order.toString());
        }
        return orders_list;
    }
}
