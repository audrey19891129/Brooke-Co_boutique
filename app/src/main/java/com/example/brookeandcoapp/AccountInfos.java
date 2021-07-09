package com.example.brookeandcoapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.brookeandcoapp.controller.AdapterListBooks;
import com.example.brookeandcoapp.controller.AdapterListCart;
import com.example.brookeandcoapp.controller.AdapterListOrder;
import com.example.brookeandcoapp.controller.BookController;
import com.example.brookeandcoapp.controller.OrderController;
import com.example.brookeandcoapp.model.Order;
import com.example.brookeandcoapp.model.Product;
import com.example.brookeandcoapp.model.User;
import com.example.brookeandcoapp.view.CategoriesBooks;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class AccountInfos extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ListView listView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountInfos() {
        // Required empty public constructor
    }

    public static AccountInfos newInstance(String param1, String param2) {
        AccountInfos fragment = new AccountInfos();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_infos, container, false);

        TextView txtName = view.findViewById(R.id.txtNameAccount);
        TextView txtEmail = view.findViewById(R.id.txtEmailAccount);
        TextView txtAddress = view.findViewById(R.id.txtAddressAccount);

        txtName.setText(User.getUser().getFirstname() + " " + User.getUser().getLastname());
        txtEmail.setText(User.getUser().getEmail());
        txtAddress.setText(User.getUser().getAddresses().get(0).toString());

        try {

            ArrayList<Order> ordersList = OrderController.getOrdersByClientId(User.getUser().getId());
            listView = view.findViewById(R.id.idOrdersList);
            AdapterListOrder adapter = new AdapterListOrder(getContext(), R.layout.fragment_orders_list, ordersList);
            listView.setAdapter(adapter);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }



        return view;
    }
}