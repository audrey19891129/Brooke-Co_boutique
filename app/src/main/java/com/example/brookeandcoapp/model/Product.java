package com.example.brookeandcoapp.model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

public class Product implements KvmSerializable {
    private static final String ID = "id";
    private static final String PCODE = "pcode";
    private static final String PICTURE = "picture";
    private static final String TYPE = "type";
    private static final String TITLE = "title";
    private static final String CATEGORY = "category";
    private static final String INVENTORY = "inventory";
    private static final String GENRE = "genre";
    private static final String PRICE = "price";
    private int id;
    private String pcode;
    private String picture;
    private String type;
    private String title;
    private String category;
    private int inventory;
    private String genre;
    private double price;

    public Product(){

    }

    public Product(int id, String pcode, String picture, String type, String title, String category, int inventory, String genre, double price){
        this.id = id;
        this.pcode = pcode;
        this.picture = picture;
        this.type = type;
        this.title = title;
        this.category = category;
        this.inventory = inventory;
        this.genre = genre;
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", pcode=" + pcode + ", picture=" + picture + ", type=" + type + ", title=" + title
                + ", category=" + category + ", inventory=" + inventory + ", genre=" + genre + ", price=" + price + "]";
    }

    @Override
    public Object getProperty(int i) {
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 0;
    }

    @Override
    public void setProperty(int i, Object o) {

    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch(i){
            case 0:
                propertyInfo.name = ID;
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            case 1:
                propertyInfo.name = PCODE;
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            case 2:
                propertyInfo.name = PICTURE;
                propertyInfo.type =  PropertyInfo.STRING_CLASS;
                break;
            case 3:
                propertyInfo.name = TYPE;
                propertyInfo.type =  PropertyInfo.STRING_CLASS;
                break;
            case 4:
                propertyInfo.name = TITLE;
                propertyInfo.type =  PropertyInfo.STRING_CLASS;
                break;
            case 5:
                propertyInfo.name = CATEGORY;
                propertyInfo.type =  PropertyInfo.STRING_CLASS;
                break;
            case 6:
                propertyInfo.name = INVENTORY;
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                break;
            case 7:
                propertyInfo.name = GENRE;
                propertyInfo.type =  PropertyInfo.STRING_CLASS;
                break;
            case 8:
                propertyInfo.name = PRICE;
                propertyInfo.type = Double.class;
                break;
            default:
                break;
        }
    }
}
