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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.brookeandcoapp.R;
import com.example.brookeandcoapp.model.Book;
import com.example.brookeandcoapp.model.Entry;
import com.example.brookeandcoapp.model.Game;
import com.example.brookeandcoapp.model.Movie;
import com.example.brookeandcoapp.model.Order;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class AdapterListEntries extends ArrayAdapter<Entry> {

    private int layoutCartList;
    private Context context;
    public ArrayList<Entry> entries;
    public ListView listView;


    public AdapterListEntries(Context context, int layoutCartList,  ArrayList<Entry> entries){
        super(context, layoutCartList, entries);
        this.context = context;
        this.entries = entries;
        this.layoutCartList = layoutCartList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layoutCartList, parent,false);
        Entry entry = entries.get(position);
        Log.e("ENTRY ADAPT", entry.toString());


        TextView txtTitle = convertView.findViewById(R.id.txtEntryTitle);
        TextView txtQuantity = convertView.findViewById(R.id.txtQuantEntry);
        TextView txtPrice = convertView.findViewById(R.id.txtPriceEntry);
        ImageView img= convertView.findViewById(R.id.imgEntry);
        img.setClickable(true);


        txtTitle.setText(entry.getProduct().getTitle());
        txtQuantity.setText(String.valueOf(entry.getQuantity()));
        txtPrice.setText(String.format("%.2f", entry.getPrice()) + " $");

        Glide.with(convertView)
                .load(entry.getProduct().getPicture())
                .placeholder(R.drawable.image_coming_soon)
                .into(img);

        View finalConvertView = convertView;
        img.setOnClickListener(v->{
            String type = entry.getProduct().getType();

            switch(type){
                case "book":
                    try {
                        AdapterListBooks.bookDetail= BookController.getBookById(entry.getProduct().getId());
                        Navigation.findNavController(finalConvertView).navigate(R.id.action_order_entries_to_detail_book);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    }
                    break;
                case "game":
                    try {
                        AdapterListGames.gameDetail= GameController.getGameById(entry.getProduct().getId());
                        Navigation.findNavController(finalConvertView).navigate(R.id.action_order_entries_to_detail_game);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    }
                    break;
                case "movie":
                    try {
                        AdapterListMovies.movieDetail= MovieController.getMovieById(entry.getProduct().getId());
                        Navigation.findNavController(finalConvertView).navigate(R.id.action_order_entries_to_detail_movie);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        });


        return convertView;
    }
}
