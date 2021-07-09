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

public class CategoriesMovies extends Fragment {
    public static String selection = null;
    TextView txtViewActionAdventure, txtViewComedy, txtViewDrama, txtViewScienceFiction;
    Button clickActionAdventure, clickComedy, clickDrama, clickScienceFiction;
    ImageView imgCategoryActionAdventure, imgCategoryComedy, imgCategoryDrama, imgCategoryScienceFiction;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CategoriesMovies() {

    }

    public static CategoriesMovies newInstance(String param1, String param2) {
        CategoriesMovies fragment = new CategoriesMovies();
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
        View view = inflater.inflate(R.layout.fragment_categories_movies, container, false);
        imgCategoryActionAdventure = view.findViewById(R.id.imgCategoryActionAdventure); imgCategoryComedy = view.findViewById(R.id.imgCategoryComedy); imgCategoryDrama = view.findViewById(R.id.imgCategoryDrama); imgCategoryScienceFiction = view.findViewById(R.id.imgCategoryScienceFiction);
        txtViewActionAdventure = view.findViewById(R.id.txtViewActionAdventure); txtViewComedy = view.findViewById(R.id.txtViewComedy); txtViewDrama = view.findViewById(R.id.txtViewDrama); txtViewScienceFiction = view.findViewById(R.id.txtViewScienceFiction);
        clickActionAdventure = view.findViewById(R.id.clickActionAdventure); clickComedy = view.findViewById(R.id.clickComedy); clickDrama = view.findViewById(R.id.clickDrama); clickScienceFiction = view.findViewById(R.id.clickScienceFiction);
        setDefaultImg();

        clickActionAdventure.setOnClickListener(v -> {
            selection = txtViewActionAdventure.getText().toString();
            Navigation.findNavController(view).navigate(R.id.action_categories_movies_to_prodcut_Layout_List);
        });
        clickComedy.setOnClickListener(v -> {
            selection = txtViewComedy.getText().toString();
            Navigation.findNavController(view).navigate(R.id.action_categories_movies_to_prodcut_Layout_List);
        });
        clickDrama.setOnClickListener(v -> {
            selection = txtViewDrama.getText().toString();
            Navigation.findNavController(view).navigate(R.id.action_categories_movies_to_prodcut_Layout_List);
        });
        clickScienceFiction.setOnClickListener(v -> {
            selection = txtViewScienceFiction.getText().toString();
            Navigation.findNavController(view).navigate(R.id.action_categories_movies_to_prodcut_Layout_List);
        });

        return view;
    }

    public void setDefaultImg(){
        Glide.with(this).load("https://multimedia.bbycastatic.ca/multimedia/products/500x500/m22/m2223/m2223215.jpg").into(imgCategoryActionAdventure);
        Glide.with(this).load("https://multimedia.bbycastatic.ca/multimedia/products/500x500/m22/m2219/m2219823.jpg").into(imgCategoryComedy);
        Glide.with(this).load("https://i5.walmartimages.ca/images/Enlarge/389/392/53363-0008539389392.jpg").into(imgCategoryDrama);
        Glide.with(this).load("https://i5.walmartimages.ca/images/Enlarge/568/368/568368.jpg").into(imgCategoryScienceFiction);
    }
}