package com.example.brookeandcoapp.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.brookeandcoapp.R;
import com.example.brookeandcoapp.model.Cart;
import com.example.brookeandcoapp.model.CartHolder;
import com.example.brookeandcoapp.model.Movie;
import com.example.brookeandcoapp.model.Product;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class AdapterListMovies extends ArrayAdapter<Movie> {
    public static Movie movieDetail;
    private Context context;
    private int layoutProductList;
    ArrayList<Movie> movieList;

    public AdapterListMovies(Context context, int layoutProductList, ArrayList<Movie> movieList){
        super(context, layoutProductList, movieList);
        this.context = context;
        this.layoutProductList = layoutProductList;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layoutProductList, parent,false);
        Movie movie = movieList.get(position);
        if(movie != null){
            Button btnAddToCart = convertView.findViewById(R.id.btnAddToCart);
            ImageView imageViewProduct = convertView.findViewById(R.id.imageViewProduct);
            TextView txtProductTitle = convertView.findViewById(R.id.txtProductTitle);
            TextView txtProductPrice = convertView.findViewById(R.id.txtProductPrice);

            txtProductTitle.setText(movie.getTitle());
            txtProductPrice.setText(String.format("%.2f", movie.getPrice()) + " $");

            Glide
                    .with(convertView)
                    .load(movie.getPicture())
                    .placeholder(R.drawable.image_coming_soon)
                    .into(imageViewProduct);

            btnAddToCart.setOnClickListener(v -> {
                Toast.makeText(context, "ITEM ADDED : " + movie.getTitle(), Toast.LENGTH_SHORT).show();
                Product product = new Product(movie.getId(),movie.getPcode(),movie.getPicture(),movie.getType(),movie.getTitle(),movie.getCategory(),movie.getInventory(),movie.getGenre(),movie.getPrice());

                try {
                    Cart.addToCart(product);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
            });
            View finalConvertView = convertView;
            ConstraintLayout cons = convertView.findViewById(R.id.idConsProd);
            cons.setClickable(true);
            cons.setOnClickListener(v -> {
                movieDetail = movie;
                Navigation.findNavController(finalConvertView).navigate(R.id.action_prodcut_Layout_List_to_detail_movie);
            });
        }

        return convertView;
    }
}
