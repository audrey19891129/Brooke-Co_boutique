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
import com.example.brookeandcoapp.controller.AdapterListBooks;
import com.example.brookeandcoapp.model.Book;

public class DetailBook extends Fragment {
    Book bookDetail = AdapterListBooks.bookDetail;
    ImageView imgDetailBook;
    TextView txtDetailBookTitle, txtDetailAuthors, txtDetailPubco, txtDetailPubdate, txtDetailGenre, txtDetailCategory, txtDetailPrice;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public DetailBook() {

    }

    public static DetailBook newInstance(String param1, String param2) {
        DetailBook fragment = new DetailBook();
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
        View view = inflater.inflate(R.layout.fragment_detail_book, container, false);
        imgDetailBook = view.findViewById(R.id.imgDetailGame);
        txtDetailBookTitle = view.findViewById(R.id.txtDetailBookTitle);
        txtDetailAuthors = view.findViewById(R.id.txtDetailAuthors);
        txtDetailPubco = view.findViewById(R.id.txtDetailPubco);
        txtDetailPubdate = view.findViewById(R.id.txtDetailPubdate);
        txtDetailGenre = view.findViewById(R.id.txtDetailGenre);
        txtDetailCategory = view.findViewById(R.id.txtDetailCategory);
        txtDetailPrice = view.findViewById(R.id.txtDetailPrice);
        showBookDetail(bookDetail);
        return view;
    }

    public void showBookDetail(Book book){
        Glide.with(this).load(book.getPicture()).into(imgDetailBook);
        txtDetailBookTitle.setText(book.getTitle());
        txtDetailAuthors.setText(book.getAuthors());
        txtDetailPubco.setText(book.getPubCo());
        txtDetailPubdate.setText(book.getPubDate());
        txtDetailGenre.setText(book.getGenre());
        txtDetailCategory.setText(book.getCategory());
        txtDetailPrice.setText(String.format("%.2f", book.getPrice()) + " $");

    }
}