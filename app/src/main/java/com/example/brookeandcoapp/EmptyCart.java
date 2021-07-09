package com.example.brookeandcoapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmptyCart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmptyCart extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button btnBackToIndex;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EmptyCart() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EmptyCart.
     */
    // TODO: Rename and change types and number of parameters
    public static EmptyCart newInstance(String param1, String param2) {
        EmptyCart fragment = new EmptyCart();
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

        View view = inflater.inflate(R.layout.fragment_empty_cart, container, false);
        btnBackToIndex = view.findViewById(R.id.buttonBackIndex);
        btnBackToIndex.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_emptyCart_to_index));

        return view;
    }
}