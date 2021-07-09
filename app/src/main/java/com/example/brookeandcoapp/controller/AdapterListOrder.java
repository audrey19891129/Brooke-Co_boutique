package com.example.brookeandcoapp.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.brookeandcoapp.R;
import com.example.brookeandcoapp.model.Book;
import com.example.brookeandcoapp.model.Cart;
import com.example.brookeandcoapp.model.Entry;
import com.example.brookeandcoapp.model.Order;
import com.example.brookeandcoapp.model.Product;
import com.example.brookeandcoapp.model.User;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class AdapterListOrder extends ArrayAdapter<Order> {
    private int layoutCartList;
    private Context context;
    ArrayList<Order> orders;
    ListView listView;
    public static ArrayList<Entry> entries;



    public AdapterListOrder(Context context, int layoutCartList,  ArrayList<Order> orders){
        super(context, layoutCartList, orders);
        this.context = context;
        this.orders = orders;
        this.layoutCartList = layoutCartList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layoutCartList, parent,false);
        Order order = orders.get(position);

            ConstraintLayout cons = convertView.findViewById(R.id.idCons);
            TextView txtTotal2 = convertView.findViewById(R.id.txtTotal2);
            TextView txtAddress = convertView.findViewById(R.id.txtAddress);
            TextView txtOrderDate = convertView.findViewById(R.id.txtOrderDate);
            TextView txtExpectDate = convertView.findViewById(R.id.txtExpectDate);
            TextView txtOrder = convertView.findViewById(R.id.txtOrderNumber);

            txtTotal2.setText(String.format("%.2f", order.getTotal()) + " $");
            txtAddress.setText(order.getDeliveryAddress().toString());
            txtOrderDate.setText(order.getOrder_date());
            txtExpectDate.setText(order.getDelivery_date());
            txtOrder.setText("ORDER NUMBER: " + order.getId());
            View finalConvertView1 = convertView;
            cons.setOnClickListener(v->{

                entries = order.getEntries();
                Navigation.findNavController(finalConvertView1).navigate(R.id.action_accountInfos_to_order_entries);
            });

            return convertView;
    }
}
