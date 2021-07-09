package com.example.brookeandcoapp;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.brookeandcoapp.Interface.SoapRequest;
import com.example.brookeandcoapp.model.Address;
import com.example.brookeandcoapp.model.Cart;
import com.example.brookeandcoapp.model.Client;
import com.example.brookeandcoapp.model.Entry;
import com.example.brookeandcoapp.model.Order;
import com.example.brookeandcoapp.model.Product;
import com.example.brookeandcoapp.model.User;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


public class Login extends Fragment {
    EditText txtEmail, txtPassword;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Login() { }

    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getContext().getTheme().applyStyle(R.style.ThemeBrookeAndCoApp, true);
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtPassword = view.findViewById(R.id.txtPassword);
        Button btnRegister = view.findViewById(R.id.btnRegister);
        Button btnLogin = view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            Client client = Login(txtEmail.getText().toString(), txtPassword.getText().toString());
            if(client.getId() != 0){
                Navigation.findNavController(view).navigate(R.id.action_login_to_index);
            }else{
                new AlertDialog.Builder(getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Erreur")
                        .setMessage("Veuillez vérifier votre courriel et votre mot de passe")
                        .setNeutralButton("OK", (dialog, which) -> { })
                        .show();
            }
        });
        btnRegister.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_login_to_register));
        return view;
    }

    public Client Login(String email, String password){
        try{
            SoapObject resultRequestSoap = SoapRequest.login_Request("CLIENT_Login", email, password);
            SoapObject response = (SoapObject) resultRequestSoap.getProperty(0);
            Log.e("CLIENT", response.toString());
            Client client = new Client();
            client.setId(Integer.parseInt(response.getProperty("id").toString()));
            if(client.getId() != 0){
                client.setEmail(response.getProperty("email").toString());
                client.setName(response.getProperty("firstname").toString());
                client.setLastname(response.getProperty("lastname").toString());

                SoapObject Addresses = (SoapObject) response.getProperty("addresses");
                ArrayList<Address> addresses_list = new ArrayList<>();
                for(int i=0; i<Addresses.getPropertyCount(); i++){
                    SoapObject Address = (SoapObject) Addresses.getProperty(i);
                    Address address = new Address();
                    address.setId(Integer.parseInt(Address.getProperty("id").toString()));
                    address.setClientId(Integer.parseInt(Address.getProperty("clientId").toString()));
                    address.setAppartment(Address.getProperty("appartment").toString());
                    address.setCity(Address.getProperty("city").toString());
                    address.setCivicnumber(Integer.parseInt(Address.getProperty("civicnumber").toString()));
                    address.setCountry(Address.getProperty("country").toString());
                    address.setStreet(Address.getProperty("street").toString());
                    address.setProvince(Address.getProperty("province").toString());
                    address.setZipcode(Address.getProperty("zipcode").toString());
                    addresses_list.add(address);
                }

                SoapObject Orders = (SoapObject) response.getProperty("orders");
                ArrayList<Order> orders_list = new ArrayList<>();
                for(int i=0; i<Orders.getPropertyCount(); i++) {
                    SoapObject Order = (SoapObject) Orders.getProperty(i);
                    Order order = new Order();
                    order.setId(Integer.parseInt(Order.getProperty("id").toString()));
                    order.setClientId(Integer.parseInt(Order.getProperty("clientId").toString()));
                    order.setStatus(Order.getProperty("status").toString());

                    SoapObject Entries = (SoapObject) Order.getProperty("entries");
                    ArrayList<Entry> entries_list = new ArrayList<>();
                    for(int j=0; j<Entries.getPropertyCount(); j++){
                        SoapObject Entry = (SoapObject) Entries.getProperty(j);
                        Entry entry = new Entry();
                        entry.setId(Integer.parseInt(Entry.getProperty("id").toString()));
                        entry.setOrderId(Integer.parseInt(Entry.getProperty("orderId").toString()));
                        entry.setQuantity(Integer.parseInt(Entry.getProperty("quantity").toString()));
                        entry.setPrice(Double.parseDouble(Entry.getProperty("price").toString()));

                        SoapObject Product = (SoapObject) Entry.getProperty("product");
                        Product product = new Product();
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
                        entries_list.add(entry);
                    }
                    order.setEntries(entries_list);
                    orders_list.add(order);
                    if(order.getStatus().equalsIgnoreCase("ongoing")){
                        Cart.setCart(order);
                    }
                }

                client.setAddresses(addresses_list);
                client.setOrders(orders_list);
                User.setUser(client);
                Log.e("CLIENT", client.toString());
            }
            return client;
        } catch (Exception e){ Log.e("ERROR", e.getMessage() + " ERROR"); }
        return null;
    }
}