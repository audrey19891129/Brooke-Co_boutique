package com.example.brookeandcoapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.brookeandcoapp.R;
import com.example.brookeandcoapp.controller.AdapterListEntries;
import com.example.brookeandcoapp.controller.AdapterListOrder;

public class order_entries extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ListView listView;

    private String mParam1;
    private String mParam2;

    public order_entries() {
    }

    public static order_entries newInstance(String param1, String param2) {
        order_entries fragment = new order_entries();
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
        View view = inflater.inflate(R.layout.fragment_order_entries, container, false);

        listView = view.findViewById(R.id.idEntries);
        AdapterListEntries adapter = new AdapterListEntries(getContext(), R.layout.fragment_entries_list, AdapterListOrder.entries);
        listView.setAdapter(adapter);

        return view;
    }
}