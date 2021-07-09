package com.example.brookeandcoapp.model;

import android.util.Log;

import com.example.brookeandcoapp.Interface.SoapRequest;

import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class Cart extends Order{
    public static Order cart;

    public Cart(){};

    public static Order getCart() {
        return cart;
    }

    public static void setCart(Order cart) {
        Cart.cart = cart;
    }

   public static int getCount(){
       int items = 0;
       for(Entry entry: cart.getEntries()){
           items += entry.getQuantity();
           Log.e("ITEMS", String.valueOf(items));
       }
       return items;
   }

    public static void remove(int entryId) throws IOException, XmlPullParserException {
        ArrayList<Entry> entries = cart.getEntries();
        int index = 0;
        boolean bool = false;
        for(int i = 0; i<entries.size(); i++) {
            Entry entry = entries.get(i);
            if (entry.getId() == entryId) {
                bool = true;
                index = i;
                break;
            }
        }
        entries.remove(index);
        SoapObject resultRequestSoap = SoapRequest.deleteEntry(entryId);
    }

    public static void update(int entryId, int quantity) throws IOException, XmlPullParserException {
        ArrayList<Entry> entries = cart.getEntries();
        int index = 0;
        boolean bool = false;
        for(int i = 0; i<entries.size(); i++) {
            Entry entry = entries.get(i);
            if (entry.getId() == entryId) {
                bool = true;
                index = i;
                entry.setQuantity(quantity);
                entries.add(entry);
                break;
            }
        }
        entries.remove(index);
        SoapObject resultRequestSoap = SoapRequest.updateEntry(entryId, quantity);
    }

    public static void addToCart(Product product) throws IOException, XmlPullParserException {
        Log.e("PREVIOUS CART", cart.toString());
        ArrayList<Entry> entries = cart.getEntries();
        int index = 0;
        boolean bool = false;
        for(int i = 0; i<entries.size(); i++) {
            Entry entry = entries.get(i);
            if (entry.getProduct().getId() == product.getId()) {
                bool = true;
                index = i;
                break;
            }
        }
        Log.e("BOOL", String.valueOf(bool));

        if(bool == true){ //Product is already in cart
            Entry entry = entries.get(index);
            int quant = entry.getQuantity() + 1;
            SoapObject resultRequestSoap = SoapRequest.updateEntry(entry.getId(), quant);
            entry.setQuantity(quant);
            entries.remove(index);
            entries.add(entry);
            cart.setEntries(entries);

            Log.e("NEW CART", cart.toString());
        }
        else{
            Entry entry = new Entry(0, cart.getOrder_id(), product, 1, product.getPrice());
            SoapObject result = SoapRequest.addEntry(entry, product.getId());
            int id = Integer.parseInt(result.getProperty("return").toString());
            entry.setId(id);
            entries.add(entry);
            cart.setEntries(entries);
        }


    }

}
