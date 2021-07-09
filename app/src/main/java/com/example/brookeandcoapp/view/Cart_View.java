package com.example.brookeandcoapp.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.brookeandcoapp.R;
import com.example.brookeandcoapp.controller.AdapterListCart;
import com.example.brookeandcoapp.model.Cart;
import com.example.brookeandcoapp.model.Entry;
import com.example.brookeandcoapp.model.Product;


import java.util.ArrayList;


public class Cart_View extends Fragment {

    TextView txtCartCount, txtTPS, txtTVQ, txtTotal;
    ListView listView;
    ImageButton btnBackToIndex;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Cart_View() {

    }

    public static Cart_View newInstance(String param1, String param2) {
        Cart_View fragment = new Cart_View();
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

        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        btnBackToIndex = view.findViewById(R.id.btnBackToIndex);
        txtCartCount = view.findViewById(R.id.txtCartCount);
        txtTPS = view.findViewById(R.id.txtTPS);
        txtTVQ = view.findViewById(R.id.txtTVQ);
        txtTotal = view.findViewById(R.id.txtTotal);

        btnBackToIndex.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_cart_to_index));

        Button CheckOut = view.findViewById(R.id.btnPasserCommander);
        CheckOut.setOnClickListener(v -> {

            int entries = Cart.getCount();
            if (entries > 0) {
                Navigation.findNavController(view).navigate(R.id.action_cart_to_payPalCheckOut);
            } else {
                new AlertDialog.Builder(getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setMessage("Votre panier est vide.")
                        .setNeutralButton("OK", (dialog, which) -> { })
                        .show();
            }

        });

        double subtotal = 0;
        int items = 0;

        ArrayList<Product> productsList = new ArrayList<>();
        for(Entry entry: Cart.getCart().getEntries()){
            productsList.add(entry.getProduct());
            subtotal += (entry.getQuantity() * entry.getPrice());
            items += entry.getQuantity();
        }
        double GST = (subtotal * 0.05);
        double QST = ((subtotal + GST) * 0.09975);
        double total = subtotal + GST + QST;
        txtTPS.setText(String.format("%.2f",GST) + " $");
        txtTVQ.setText(String.format("%.2f",QST) + " $");
        txtTotal.setText(String.format("%.2f",total) + " $");
        txtCartCount.setText("( " + String.valueOf(items) + " )");

        listView = view.findViewById(R.id.idCartItemList);
        AdapterListCart adapter = new AdapterListCart(getContext(), R.layout.fragment_cart__product__list, productsList);
        listView.setAdapter(adapter);

        return view;

    }

}
