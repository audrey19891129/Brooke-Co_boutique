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

public class MovieController {

    public static Movie parseMovie(SoapObject product){

        int id = Integer.parseInt(product.getProperty("id").toString());
        String pcode = product.getProperty("pcode").toString();
        String picture = product.getProperty("picture").toString();
        String type = product.getProperty("type").toString();
        String title = product.getProperty("title").toString();
        String category = product.getProperty("category").toString();
        int inventory = Integer.parseInt(product.getProperty("inventory").toString());
        String genre = product.getProperty("genre").toString();
        Double price = Double.parseDouble(product.getProperty("price").toString());
        String director = product.getProperty("director").toString();
        String actors = product.getProperty("actors").toString();
        String relyear = product.getProperty("relyear").toString();

        Movie movie = new Movie(id, pcode, picture, type, title, category, inventory, genre, price,director, actors, relyear);
        return movie;
    }


    public static ArrayList<Movie> getAllMoviesByCategories(String category) throws IOException, XmlPullParserException {
        ArrayList<Movie> list = new ArrayList<>();
        SoapObject resultRequestSoap = SoapRequest.getByCategory("MOVIE_Category", category);
        for(int i = 0; i < resultRequestSoap.getPropertyCount(); i++) {
            SoapObject product = (SoapObject) resultRequestSoap.getProperty(i);
            Movie movie = parseMovie(product);
            list.add(movie);
        }
        return list;
    }

    public static Movie getMovieById(int id) throws IOException, XmlPullParserException {
        SoapObject resultRequestSoap = SoapRequest.getById("MOVIE_ID", id);
        SoapObject product = (SoapObject) resultRequestSoap.getProperty(0);
        Movie movie = parseMovie(product);
        return movie;
    }
}
