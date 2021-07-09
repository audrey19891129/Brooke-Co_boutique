package com.example.brookeandcoapp.model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

public class Address implements KvmSerializable {
    private static final String ID = "id";
    private static final String COUNTRY = "country";
    private static final String PROVINCE = "province";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String CIVICNUMBER = "civicnumber";
    private static final String APPARTMENT = "appartment";
    private static final String ZIPCODE = "zipcode";
    private static final String CLIENT_ID = "clientId";

    private int id, civicnumber, clientId ;
    private String country, province, city, street, appartment, zipcode;


    public Address() { }

    public Address(int id, String country, String province, String city, String street, int civicnumber, String appartment, String zipcode, int clientId) {
        this.id = id;
        this.country = country;
        this.province = province;
        this.city = city;
        this.street = street;
        this.civicnumber = civicnumber;
        this.appartment = appartment;
        this.zipcode = zipcode;
        this.clientId = clientId;
    }

    public Address(String country, String province, String city, String street, int civicnumber, String appartment, String zipcode) {
        this.country = country;
        this.province = province;
        this.city = city;
        this.street = street;
        this.civicnumber = civicnumber;
        this.appartment = appartment;
        this.zipcode = zipcode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCivicnumber() {
        return civicnumber;
    }

    public void setCivicnumber(int civicnumber) {
        this.civicnumber = civicnumber;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAppartment() {
        return appartment;
    }

    public void setAppartment(String appartment) {
        this.appartment = appartment;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        if(!(this.getAppartment().equalsIgnoreCase("0")))
            return civicnumber + ", rue " + street + " app: " + appartment + " , " + city + ", " + province + ", " + country + ", " + zipcode;
        else
            return civicnumber + ", rue " + street + " , " + city + ", " + province + ", " + country + ", " + zipcode;
    }

    @Override
    public Object getProperty(int i) {
        switch(i){
            case 0:
                return id;
            case 1:
                return country;
            case 2:
                return province;
            case 3:
                return city;
            case 4:
                return street;
            case 5:
                return civicnumber;
            case 6:
                return appartment;
            case 7:
                return zipcode;
            case 8:
                return clientId;
            default: break;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 9;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch(i){
            case 0:
                 id = Integer.parseInt(o.toString());
                 break;
            case 1:
                country = o.toString();
                break;
            case 2:
                province = o.toString();
                break;
            case 3:
                city = o.toString();
                break;
            case 4:
                street = o.toString();
                break;
            case 5:
                civicnumber = Integer.parseInt(o.toString());
                break;
            case 6:
                appartment = o.toString();
                break;
            case 7:
                zipcode = o.toString();
                break;
            case 8:
                clientId = Integer.parseInt(o.toString());
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
                propertyInfo.name = COUNTRY;
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            case 2:
                propertyInfo.name = PROVINCE;
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            case 3:
                propertyInfo.name = CITY;
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            case 4:
                propertyInfo.name = STREET;
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            case 5:
                propertyInfo.name = CIVICNUMBER;
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                break;
            case 6:
                propertyInfo.name = APPARTMENT;
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            case 7:
                propertyInfo.name = ZIPCODE;
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            case 8:
                propertyInfo.name = CLIENT_ID;
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                break;
            default: break;
        }
    }
}
