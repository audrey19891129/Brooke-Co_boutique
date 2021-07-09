package com.example.brookeandcoapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.brookeandcoapp.model.Cart;
import com.example.brookeandcoapp.model.CartHolder;

public class Navbar extends Fragment {
    ImageButton btnCart2;
    public static TextView txtCartItemCount2;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public Navbar() {

    }

    public static Navbar newInstance(String param1, String param2) {
        Navbar fragment = new Navbar();
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
        View view = inflater.inflate(R.layout.fragment_navbar, container, false);
        txtCartItemCount2 = view.findViewById(R.id.txtCartItemCount2); txtCartItemCount2.setText(String.valueOf(Cart.getCount()));
        btnCart2 = view.findViewById(R.id.btnCart2);
        return view;
    }


    public static void refreshCount(){
        txtCartItemCount2.setText(String.valueOf(CartHolder.getCount()));
    }
}