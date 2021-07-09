package com.example.brookeandcoapp.model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.ArrayList;
import java.util.Hashtable;

public class Client implements KvmSerializable {
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";

    public int id;
    public String email;
    public String password;
    public String firstname;
    public String lastname;
    public ArrayList<Address> addresses;
    public ArrayList<Order> orders;

    public Client() {
        this.addresses = new ArrayList<Address>();
        this.orders = new ArrayList<Order>();
    }

    public Client(int id, String email, String password, String firstname, String lastname){
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.addresses = new ArrayList<Address>();
        this.orders = new ArrayList<Order>();
    }

    public Client(String email,String password, String firstname, String lastname)
    {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.addresses = new ArrayList<Address>();
        this.orders = new ArrayList<Order>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setName(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(ArrayList<Address> adresses) {
        this.addresses = adresses;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Client [id=" + id + ", email=" + email + ", password=" + password + ", name="
                + firstname + ", lastname=" + lastname + ", addresses=" + addresses + ", orders=" + orders + "]";
    }



    @Override
    public Object getProperty(int i) {
        switch (i){
            case 0:
                return id;
            case 1:
                return email;
            case 2:
                return password;
            case 3:
                return firstname;
            case 4:
                return lastname;
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
                email = o.toString();
                break;
            case 2:
                password = o.toString();
                break;
            case 3:
                firstname = o.toString();
                break;
            case 4:
                lastname = o.toString();
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
                propertyInfo.name = EMAIL;
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            case 2:
                propertyInfo.name = PASSWORD;
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            case 3:
                propertyInfo.name = FIRSTNAME;
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            case 4:
                propertyInfo.name = LASTNAME;
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            default: break;
        }

    }


}
