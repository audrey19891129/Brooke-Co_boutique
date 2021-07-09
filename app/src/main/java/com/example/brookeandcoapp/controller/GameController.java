package com.example.brookeandcoapp.controller;

import com.example.brookeandcoapp.Interface.SoapRequest;
import com.example.brookeandcoapp.model.Book;
import com.example.brookeandcoapp.model.Game;
import com.example.brookeandcoapp.model.Movie;

import org.ksoap2.serialization.SoapObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class GameController {

    public static Game parseGame(SoapObject product){

        int id = Integer.parseInt(product.getProperty("id").toString());
        String pcode = product.getProperty("pcode").toString();
        String picture = product.getProperty("picture").toString();
        String type = product.getProperty("type").toString();
        String title = product.getProperty("title").toString();
        String category = product.getProperty("category").toString();
        int inventory = Integer.parseInt(product.getProperty("inventory").toString());
        String genre = product.getProperty("genre").toString();
        Double price = Double.parseDouble(product.getProperty("price").toString());
        String console = product.getProperty("console").toString();
        String company = product.getProperty("company").toString();
        String reldate = product.getProperty("reldate").toString();

        Game game = new Game(id, pcode, picture, type, title, category, inventory, genre, price,console, company, reldate);
        return game;
    }

    public static ArrayList<Game> getAllGamesByCategories(String category) throws IOException, XmlPullParserException {
        ArrayList<Game> list = new ArrayList<>();
        SoapObject resultRequestSoap = SoapRequest.getByCategory("GAME_Category", category);
        for(int i = 0; i < resultRequestSoap.getPropertyCount(); i++) {
            SoapObject product = (SoapObject) resultRequestSoap.getProperty(i);
            Game game = parseGame(product);
            list.add(game);
        }
        return list;
    }

    public static Game getGameById(int id) throws IOException, XmlPullParserException {
        SoapObject resultRequestSoap = SoapRequest.getById("GAME_ID", id);
        SoapObject product = (SoapObject) resultRequestSoap.getProperty(0);
        Game game = parseGame(product);
        return game;
    }
}
