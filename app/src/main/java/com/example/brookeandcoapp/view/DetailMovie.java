package com.example.brookeandcoapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.brookeandcoapp.R;
import com.example.brookeandcoapp.controller.AdapterListGames;
import com.example.brookeandcoapp.controller.AdapterListMovies;
import com.example.brookeandcoapp.model.Game;
import com.example.brookeandcoapp.model.Movie;

public class DetailMovie extends Fragment {
    Movie movieDetail = AdapterListMovies.movieDetail;
    ImageView imgDetailMovie;
    TextView txtDetailMovieTitle, txtDetailDirector, txtDetailActors, txtDetailRelYear, txtDetailGenre, txtDetailCategory, txtDetailPrice;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public DetailMovie() {

    }

    public static DetailMovie newInstance(String param1, String param2) {
        DetailMovie fragment = new DetailMovie();
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
        View view = inflater.inflate(R.layout.fragment_detail_movie, container, false);
        imgDetailMovie = view.findViewById(R.id.imgDetailMovie);
        txtDetailMovieTitle = view.findViewById(R.id.txtDetailMovieTitle); txtDetailDirector = view.findViewById(R.id.txtDetailDirector); txtDetailActors = view.findViewById(R.id.txtDetailActors);
        txtDetailRelYear = view.findViewById(R.id.txtDetailRelYear); txtDetailGenre = view.findViewById(R.id.txtDetailGenre); txtDetailCategory = view.findViewById(R.id.txtDetailCategory);
        txtDetailPrice = view.findViewById(R.id.txtDetailPrice);
        showMovieDetail(movieDetail);
        return view;
    }

    public void showMovieDetail(Movie movie){
        Glide.with(this).load(movie.getPicture()).into(imgDetailMovie);
        txtDetailMovieTitle.setText(movie.getTitle());
        txtDetailDirector.setText(movie.getDirector());
        txtDetailActors.setText(movie.getActors());
        txtDetailRelYear.setText(movie.getRelyear());
        txtDetailGenre.setText(movie.getGenre());
        txtDetailCategory.setText(movie.getCategory());
        txtDetailPrice.setText(String.format("%.2f", movie.getPrice()) + " $");
    }

}