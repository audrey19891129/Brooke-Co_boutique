package com.example.brookeandcoapp.view;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.brookeandcoapp.R;
import com.example.brookeandcoapp.controller.AdapterListBooks;
import com.example.brookeandcoapp.controller.AdapterListGames;
import com.example.brookeandcoapp.controller.AdapterListMovies;
import com.example.brookeandcoapp.controller.BookController;
import com.example.brookeandcoapp.controller.GameController;
import com.example.brookeandcoapp.controller.MovieController;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Product_Layout_List extends Fragment {
    private static AdapterListBooks adapterBook;
    private static AdapterListGames adapterGame;
    private static AdapterListMovies adapterMovie;
    ListView listView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Product_Layout_List() {
    }

    public static Product_Layout_List newInstance(String param1, String param2) {
        Product_Layout_List fragment = new Product_Layout_List();
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
        View view = inflater.inflate(R.layout.fragment_prodcut_layout_list, container, false);
        listView = view.findViewById(R.id.idListProduct);
        try {
            setAdapter();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return view;
    }

    public void setAdapter() throws IOException, XmlPullParserException {
        if(CategoriesBooks.selection != null){
            adapterBook = new AdapterListBooks(getContext(), R.layout.fragment_product_list, BookController.getAllBooksByCategories(CategoriesBooks.selection));
            listView.setAdapter(adapterBook);
        }else if(CategoriesGames.selection != null){
            adapterGame = new AdapterListGames(getContext(), R.layout.fragment_product_list, GameController.getAllGamesByCategories(CategoriesGames.selection));
            listView.setAdapter(adapterGame);
        }else if(CategoriesMovies.selection != null){
            adapterMovie = new AdapterListMovies(getContext(), R.layout.fragment_product_list, MovieController.getAllMoviesByCategories(CategoriesMovies.selection));
            listView.setAdapter(adapterMovie);
        }
    }
}