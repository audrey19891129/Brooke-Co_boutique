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
import com.example.brookeandcoapp.model.Game;

public class DetailGame extends Fragment {
    Game gameDetail = AdapterListGames.gameDetail;
    ImageView imgDetailGame;
    TextView txtDetailGameTitle, txtDetailConsole, txtDetailCompany, txtDetailRelDate, txtDetailGenre, txtDetailCategory, txtDetailPrice;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public DetailGame() {

    }

    public static DetailGame newInstance(String param1, String param2) {
        DetailGame fragment = new DetailGame();
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
        View view = inflater.inflate(R.layout.fragment_detail_game, container, false);
        imgDetailGame = view.findViewById(R.id.imgDetailGame);
        txtDetailGameTitle = view.findViewById(R.id.txtDetailGameTitle); txtDetailConsole = view.findViewById(R.id.txtDetailConsole); txtDetailCompany = view.findViewById(R.id.txtDetailCompany);
        txtDetailRelDate = view.findViewById(R.id.txtDetailRelDate); txtDetailGenre = view.findViewById(R.id.txtDetailGenre); txtDetailCategory = view.findViewById(R.id.txtDetailCategory);
        txtDetailPrice = view.findViewById(R.id.txtDetailPrice);
        showBookDetail(gameDetail);
        return view;
    }

    public void showBookDetail(Game game){
        Glide.with(this).load(game.getPicture()).into(imgDetailGame);
        txtDetailGameTitle.setText(game.getTitle());
        txtDetailConsole.setText(game.getConsole());
        txtDetailCompany.setText(game.getCompany());
        txtDetailRelDate.setText(game.getReldate());
        txtDetailGenre.setText(game.getGenre());
        txtDetailCategory.setText(game.getCategory());
        txtDetailPrice.setText(String.format("%.2f", game.getPrice()) + " $");
    }
}