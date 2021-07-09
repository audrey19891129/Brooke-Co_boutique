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
import com.example.brookeandcoapp.model.Book;
import com.example.brookeandcoapp.model.Cart;
import com.example.brookeandcoapp.model.CartHolder;
import com.example.brookeandcoapp.model.Entry;
import com.example.brookeandcoapp.model.Product;


import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;


public class AdapterListBooks extends ArrayAdapter<Book> {
    public static Book bookDetail;
    private Context context;
    private int layoutProductList;
    ArrayList<Book> bookList;

    public AdapterListBooks(Context context, int layoutProductList, ArrayList<Book> bookList){
        super(context, layoutProductList, bookList);
        this.context = context;
        this.layoutProductList = layoutProductList;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layoutProductList, parent,false);
        Book book = bookList.get(position);
        if(book != null){
            Button btnAddToCart = convertView.findViewById(R.id.btnAddToCart);
            ImageView imageViewProduct = convertView.findViewById(R.id.imageViewProduct);
            TextView txtProductTitle = convertView.findViewById(R.id.txtProductTitle);
            TextView txtProductPrice = convertView.findViewById(R.id.txtProductPrice);

            txtProductTitle.setText(book.getTitle());
            txtProductPrice.setText(String.format("%.2f", book.getPrice()) + " $");

            Glide
                    .with(convertView)
                    .load(book.getPicture())
                    .placeholder(R.drawable.image_coming_soon)
                    .into(imageViewProduct);

            btnAddToCart.setOnClickListener(v -> {
                Toast.makeText(context, "ITEM ADDED : " + book.getTitle(), Toast.LENGTH_SHORT).show();
                Product product = new Product(book.getId(), book.getPcode(), book.getPicture(),book.getType(),book.getTitle(),book.getCategory(),book.getInventory(),book.getGenre(),book.getPrice());
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
                bookDetail = book;
                Navigation.findNavController(finalConvertView).navigate(R.id.action_prodcut_Layout_List_to_detail_book);
            });

        }

        return convertView;
    }
}
