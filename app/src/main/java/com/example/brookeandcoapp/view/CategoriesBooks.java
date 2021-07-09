package com.example.brookeandcoapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.brookeandcoapp.R;
import com.example.brookeandcoapp.controller.BookController;
import com.example.brookeandcoapp.model.Book;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;


public class CategoriesBooks extends Fragment {
    public static String selection = null;
    TextView txtViewGeography, txtViewProgramming, txtViewLiterature;
    Button clickGeography, clickProgramming, clickLiterature;
    ImageView imgCategoryGeo, imgCategoryPro, imgCategoryLit;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CategoriesBooks() {

    }

    public static CategoriesBooks newInstance(String param1, String param2) {
        CategoriesBooks fragment = new CategoriesBooks();
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
        View view = inflater.inflate(R.layout.fragment_categories_books, container, false);
        imgCategoryGeo = view.findViewById(R.id.imgCategoryGeo); imgCategoryPro = view.findViewById(R.id.imgCategoryPro); imgCategoryLit = view.findViewById(R.id.imgCategoryLit);
        txtViewGeography = view.findViewById(R.id.txtViewGeography); txtViewProgramming = view.findViewById(R.id.txtViewProgramming); txtViewLiterature = view.findViewById(R.id.txtViewLiterature);
        clickGeography = view.findViewById(R.id.clickGeography); clickProgramming = view.findViewById(R.id.clickProgramming); clickLiterature = view.findViewById(R.id.clickLiterature);

        setDefaultImg();
        clickGeography.setOnClickListener(v -> {
            selection = txtViewGeography.getText().toString();
            Navigation.findNavController(view).navigate(R.id.action_categories_books_to_prodcut_Layout_List);
        });
        clickProgramming.setOnClickListener(v -> {
            selection = txtViewProgramming.getText().toString();
            Navigation.findNavController(view).navigate(R.id.action_categories_books_to_prodcut_Layout_List);
        });
        clickLiterature.setOnClickListener(v -> {
            selection = txtViewLiterature.getText().toString();
            Navigation.findNavController(view).navigate(R.id.action_categories_books_to_prodcut_Layout_List);
        });
        return view;
    }

    public void setDefaultImg(){
        Glide.with(this).load("https://kbimages1-a.akamaihd.net/0b1824a7-2bc4-40e5-9c00-3950cfb03d8c/353/569/90/False/the-geography-of-wine.jpg").into(imgCategoryGeo);
        Glide.with(this).load("https://images-na.ssl-images-amazon.com/images/I/918OMmOh1bL.jpg").into(imgCategoryPro);
        Glide.with(this).load("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1415008395l/21468700.jpg").into(imgCategoryLit);
    }

    public void categorySelected(String category) throws IOException, XmlPullParserException {
        ArrayList<Book> books = BookController.getAllBooksByCategories(category);
            for(Book book : books){ Log.e(category,book.toString()); }
    }

}