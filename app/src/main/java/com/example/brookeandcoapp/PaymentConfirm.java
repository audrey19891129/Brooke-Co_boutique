package com.example.brookeandcoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.brookeandcoapp.Interface.SoapRequest;
import com.example.brookeandcoapp.model.Cart;
import com.example.brookeandcoapp.model.Order;
import com.example.brookeandcoapp.model.Product;
import com.example.brookeandcoapp.model.User;

import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class PaymentConfirm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirm);

        Order newCart = new Order(0, User.getUser().getId(), "ongoing");
        try {
            SoapObject res = SoapRequest.newOrder(newCart);
            int orderId = Integer.parseInt(res.getProperty("return").toString());
            newCart.setId(orderId);
            Cart.setCart(newCart);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

    }
}