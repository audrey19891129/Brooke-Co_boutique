package com.example.brookeandcoapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.brookeandcoapp.model.Cart;
import com.example.brookeandcoapp.model.CartHolder;
import com.example.brookeandcoapp.view.CategoriesBooks;
import com.example.brookeandcoapp.view.CategoriesGames;
import com.example.brookeandcoapp.view.CategoriesMovies;


public class Index extends Fragment {
    ImageView imgView;
    ImageButton btnCart,btnAccount;
    Button btnBooks, btnGames, btnMovies;
    TextView txtCartItemCount;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Index() { }

    public static Index newInstance(String param1, String param2) {
        Index fragment = new Index();
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
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        txtCartItemCount = view.findViewById(R.id.txtCartItemCount);
        btnCart = view.findViewById(R.id.btnCart);
        btnAccount = view.findViewById(R.id.btnAccount);
        imgView = view.findViewById(R.id.imgView);
        Glide.with(this).load("https://static-ca.ebgames.ca/images/products/754754/3max.jpg").into(imgView);
        btnBooks = view.findViewById(R.id.btnBooks); btnGames = view.findViewById(R.id.btnGames); btnMovies = view.findViewById(R.id.btnMovies);

        btnBooks.setOnClickListener(v -> {
            CategoriesBooks.selection = null; CategoriesGames.selection = null; CategoriesMovies.selection = null;
            Navigation.findNavController(view).navigate(R.id.action_index_to_categories_books);
        });
        btnGames.setOnClickListener(v -> {
            CategoriesBooks.selection = null; CategoriesGames.selection = null; CategoriesMovies.selection = null;
            Navigation.findNavController(view).navigate(R.id.action_index_to_categories_games);
        });
        btnMovies.setOnClickListener(v -> {
            CategoriesBooks.selection = null; CategoriesGames.selection = null; CategoriesMovies.selection = null;
            Navigation.findNavController(view).navigate(R.id.action_index_to_categories_movies);
        });

        txtCartItemCount.setText(String.valueOf(Cart.getCount()));
        btnCart.setOnClickListener(v -> {
                if(txtCartItemCount.getText().equals("0")){ // OR Cart.getCount() == 0
                    Navigation.findNavController(view).navigate(R.id.action_index_to_emptyCart);
                }else{
                    Navigation.findNavController(view).navigate(R.id.action_index_to_cart);
                }
            });

        btnAccount.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_index_to_accountInfos);
        });
        return view;
    }
}