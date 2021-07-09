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
import com.example.brookeandcoapp.model.Game;
import com.example.brookeandcoapp.model.Product;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class AdapterListGames extends ArrayAdapter<Game> {
    public static Game gameDetail;
    private Context context;
    private int layoutProductList;
    ArrayList<Game> gameList;

    public AdapterListGames(Context context, int layoutProductList, ArrayList<Game> gameList){
        super(context, layoutProductList, gameList);
        this.context = context;
        this.layoutProductList = layoutProductList;
        this.gameList = gameList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layoutProductList, parent,false);
        Game game = gameList.get(position);
        if(game != null){
            Button btnAddToCart = convertView.findViewById(R.id.btnAddToCart);
            ImageView imageViewProduct = convertView.findViewById(R.id.imageViewProduct);
            TextView txtProductTitle = convertView.findViewById(R.id.txtProductTitle);
            TextView txtProductPrice = convertView.findViewById(R.id.txtProductPrice);


            txtProductTitle.setText(game.getTitle());
            txtProductPrice.setText(String.format("%.2f", game.getPrice()) + " $");

            Glide
                    .with(convertView)
                    .load(game.getPicture())
                    .placeholder(R.drawable.image_coming_soon)
                    .into(imageViewProduct);

            btnAddToCart.setOnClickListener(v -> {
                Toast.makeText(context, "ITEM ADDED : " + game.getTitle(), Toast.LENGTH_SHORT).show();
                Product product = new Product(game.getId(),game.getPcode(),game.getPicture(),game.getType(),game.getTitle(),game.getCategory(),game.getInventory(),game.getGenre(),game.getPrice());

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
                gameDetail = game;
                Navigation.findNavController(finalConvertView).navigate(R.id.action_prodcut_Layout_List_to_detail_game);
            });
        }

        return convertView;
    }
}
