package com.example.brookeandcoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.brookeandcoapp.Interface.SoapRequest;
import com.example.brookeandcoapp.model.Address;
import com.example.brookeandcoapp.model.Cart;
import com.example.brookeandcoapp.model.Entry;
import com.example.brookeandcoapp.model.Order;
import com.example.brookeandcoapp.model.Product;
import com.example.brookeandcoapp.model.User;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class PayPalCheckOut extends AppCompatActivity {

        TextView txtSub, txtDeliv, txtTPS, txtTVQ, txtTotal, txtAddress, txtExp;
        public double Amount = 0;
        Order cart = new Order();

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId("AZPXTSbap596knasJ3epXRbo1hAd3lW909Tp-PUiH9-JXqHB1RTrMnzgA4lQG-IccW3nwKydU0NXEM80");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_pal_check_out);

        cart.setId(Cart.getCart().getId());
        cart.setStatus("ongoing");
        cart.setClientId(Cart.getCart().getClientId());

        final Button button = (Button) findViewById(R.id.paypal_button);
        txtSub = findViewById(R.id.txtSubtotal);
        txtDeliv = findViewById(R.id.txtDelivery);
        txtTPS =  findViewById(R.id.txtTPS2);
        txtTVQ =  findViewById(R.id.txtTVQ2);
        txtTotal = findViewById(R.id.txtTotal2);
        txtAddress = findViewById(R.id.txtAddress);
        txtExp = findViewById(R.id.txtExpectDate);

        double subtotal = 0;

        ArrayList<Product> productsList = new ArrayList<>();
        for(Entry entry: Cart.getCart().getEntries()){
            productsList.add(entry.getProduct());
            subtotal += (entry.getQuantity() * entry.getPrice());
        }
        txtSub.setText(String.format("%.2f",subtotal) + " $");
        subtotal += 5.00;
        cart.setSubtotal(subtotal);
        double GST = (subtotal * 0.05);
        double QST = ((subtotal + GST) * 0.09975);
        double total = subtotal + GST + QST;
        Amount = total;
        cart.setTotal(total);

        txtTPS.setText(String.format("%.2f",GST) + " $");
        txtTVQ.setText(String.format("%.2f",QST) + " $");
        txtTotal.setText(String.format("%.2f",total) + " $");
        txtDeliv.setText(String.format("%.2f",5.00) + " $");

        Address address = User.getUser().getAddresses().get(0);
        txtAddress.setText(address.toString());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        cart.setOrder_date(dateFormat.format(today));
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DATE, 7);
        Date ExpDate = c.getTime();
        txtExp.setText(dateFormat.format(ExpDate));
        cart.setDelivery_date(dateFormat.format(ExpDate));

        Button simulation = (Button) findViewById(R.id.btnPaymentSimulation);
        simulation.setOnClickListener(v -> {

            char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
            StringBuilder sb = new StringBuilder(15);
            Random random = new Random();
            for (int i = 0; i < 15; i++) {
                char C = chars[random.nextInt(chars.length)];
                sb.append(C);
            }
            String transaction = sb.toString();
            cart.setTransaction(transaction);
            Log.e("ORDER", cart.toString());

            try {
                Address a = User.getUser().getAddresses().get(0);
                SoapObject result = SoapRequest.updateOrder(cart, a.getId());
                String bool = result.getProperty("return").toString();
                if(bool.equalsIgnoreCase("true")){
                    Intent myIntent = new Intent(this, PaymentConfirm.class);
                    startActivity(myIntent);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        });
    }

    public void continueSimulation(View view){

    }

    public void beginPayment(View view){
        Intent serviceConfig = new Intent(this, PayPalService.class);
        serviceConfig.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(serviceConfig);
        PayPalPayment payment = new PayPalPayment(new BigDecimal(Amount),
                "CAD", "Brooke and Co order", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent paymentConfig = new Intent(this, PaymentActivity.class);
        paymentConfig.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        paymentConfig.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(paymentConfig, 0);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm = data.getParcelableExtra(
                    PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                try {
                    Log.i("sampleapp", confirm.toJSONObject().toString(4));
                    // TODO: send 'confirm' to your server for verification



                } catch (JSONException e) {
                    Log.e("sampleapp", "no confirmation data: ", e);
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i("sampleapp", "The user canceled.");
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Log.i("sampleapp", "Invalid payment / config set");
        }
    }

    @Override
    public void onDestroy(){
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

}