package com.example.brookeandcoapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.brookeandcoapp.R;

public class CategoriesGames extends Fragment {
    public static String selection = null;
    TextView txtViewPS4, txtViewWiiU, txtViewXboxOne;
    Button clickPS4, clickWiiU, clickXboxOne;
    ImageView imgCategoryPS4, imgCategoryWiiU, imgCategoryXboxOne;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CategoriesGames() {

    }

    public static CategoriesGames newInstance(String param1, String param2) {
        CategoriesGames fragment = new CategoriesGames();
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
        View view = inflater.inflate(R.layout.fragment_categories_games, container, false);
        imgCategoryPS4 = view.findViewById(R.id.imgCategoryPS4); imgCategoryWiiU = view.findViewById(R.id.imgCategoryWiiU); imgCategoryXboxOne = view.findViewById(R.id.imgCategoryXboxOne);
        txtViewPS4 = view.findViewById(R.id.txtViewPS4); txtViewWiiU = view.findViewById(R.id.txtViewWiiU); txtViewXboxOne = view.findViewById(R.id.txtViewXboxOne);
        clickPS4 = view.findViewById(R.id.clickPS4); clickWiiU = view.findViewById(R.id.clickWiiU); clickXboxOne = view.findViewById(R.id.clickXboxOne);

        setDefaultImg();
        clickPS4.setOnClickListener(v -> {
            selection = txtViewPS4.getText().toString();
            Navigation.findNavController(view).navigate(R.id.action_categories_games_to_prodcut_Layout_List);
        });
        clickWiiU.setOnClickListener(v -> {
            selection = txtViewWiiU.getText().toString();
            Navigation.findNavController(view).navigate(R.id.action_categories_games_to_prodcut_Layout_List);
        });
        clickXboxOne.setOnClickListener(v -> {
            selection = txtViewXboxOne.getText().toString();
            Navigation.findNavController(view).navigate(R.id.action_categories_games_to_prodcut_Layout_List);
        });

        return view;
    }

    public void setDefaultImg(){
        Glide.with(this).load("https://static-ca.ebgames.ca/images/products/739918/3max.jpg").into(imgCategoryPS4);
        Glide.with(this).load("https://i5.walmartimages.com/asr/1f6f91e2-7d57-4efd-beb6-da8072204680_1.412466e6b8cd86814e13ab1548cb6c42.jpeg").into(imgCategoryWiiU);
        Glide.with(this).load("https://static-ca.ebgames.ca/images/products/714536/3max.jpg").into(imgCategoryXboxOne);
    }
}