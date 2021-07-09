package com.example.brookeandcoapp.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.brookeandcoapp.R;
import com.example.brookeandcoapp.model.Cart;
import com.example.brookeandcoapp.model.CartHolder;
import com.example.brookeandcoapp.model.Entry;
import com.example.brookeandcoapp.model.Game;
import com.example.brookeandcoapp.model.Product;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

public class AdapterListCart extends ArrayAdapter<Product> {
    private Context context;
    private int layoutCartList;
    ArrayList<Product> cartProductList;

    public AdapterListCart(Context context, int layoutCartList, ArrayList<Product> cartProductList){
        super(context, layoutCartList, cartProductList);
        this.context = context;
        this.layoutCartList = layoutCartList;
        this.cartProductList = cartProductList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layoutCartList, parent,false);

        Product product = cartProductList.get(position);

            if(product != null){

                Spinner quantitySpinner = convertView.findViewById(R.id.quantitySpinner);
                ArrayAdapter<CharSequence> adapterQuantity = ArrayAdapter.createFromResource(getContext(), R.array.quantity, android.R.layout.simple_spinner_dropdown_item);
                quantitySpinner.setAdapter(adapterQuantity);

                Button btnDelete = convertView.findViewById(R.id.btnSupprimer);
                ImageView imgCartProduct = convertView.findViewById(R.id.imgCartProduct);
                TextView txtTitle = convertView.findViewById(R.id.txtTitle);
                TextView txtPrice = convertView.findViewById(R.id.txtPrice);
                TextView txtQuant = convertView.findViewById(R.id.txtQuantity);

                for(Entry entry: Cart.getCart().getEntries()){
                    Product pr = entry.getProduct();
                    if(pr.getId() == product.getId()) {
                        txtQuant.setText(String.valueOf(entry.getQuantity()));
                        break;
                    }
                }


                txtTitle.setText(product.getTitle());
                txtPrice.setText(String.format("%.2f", product.getPrice()) + " $");

                Glide.with(convertView)
                        .load(product.getPicture())
                        .placeholder(R.drawable.image_coming_soon)
                        .into(imgCartProduct);

                View finalConvertView = convertView;

                quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        //***** IS TRIGGERED BY setSelection(entry.getQuantity()) ==> CREATES INFINITE LOOP*******
                        if (!quantitySpinner.getSelectedItem().toString().equalsIgnoreCase("selection")) {

                            int quantity = Integer.parseInt(quantitySpinner.getSelectedItem().toString());
                            int prev = 0;
                            int entryId = 0;
                            for (Entry entry : Cart.getCart().getEntries()) {
                                if (entry.getProduct().getId() == product.getId()) {
                                    entryId = entry.getId();
                                    prev = entry.getQuantity();
                                    break;
                                }
                            }
                            try {
                                if (prev != quantity) {
                                    Cart.update(entryId, quantity);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (XmlPullParserException e) {
                                e.printStackTrace();
                            }
                            Navigation.findNavController(finalConvertView).navigate(R.id.action_cart_self);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }

                });

                btnDelete.setOnClickListener(v -> {
                    int entryId = 0;
                    for(Entry entry: Cart.getCart().getEntries()){
                        if(entry.getProduct().getId() == product.getId()){
                            entryId = entry.getId();
                            break;
                        }
                    }
                    try {
                        Cart.remove(entryId);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    }
                    Navigation.findNavController(finalConvertView).navigate(R.id.action_cart_self);
                });

                View finalConvertView1 = convertView;

                ConstraintLayout cons = convertView.findViewById(R.id.idConsCart);
                cons.setClickable(true);
                cons.setOnClickListener(v -> {
                    String type = product.getType();
                    switch(type){
                        case "book":
                            try {
                                AdapterListBooks.bookDetail= BookController.getBookById(product.getId());
                                Navigation.findNavController(finalConvertView).navigate(R.id.action_cart_to_detail_book);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (XmlPullParserException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "game":
                            try {
                                AdapterListGames.gameDetail= GameController.getGameById(product.getId());
                                Navigation.findNavController(finalConvertView).navigate(R.id.action_cart_to_detail_game);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (XmlPullParserException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "movie":
                            try {
                                AdapterListMovies.movieDetail= MovieController.getMovieById(product.getId());
                                Navigation.findNavController(finalConvertView).navigate(R.id.action_cart_to_detail_movie);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (XmlPullParserException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                });
            }

        return convertView;
    }
}
