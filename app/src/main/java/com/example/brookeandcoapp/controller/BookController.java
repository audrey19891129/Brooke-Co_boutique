package com.example.brookeandcoapp.controller;

import com.example.brookeandcoapp.Interface.SoapRequest;
import com.example.brookeandcoapp.model.Book;
import com.example.brookeandcoapp.model.Game;

import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class BookController {

    public static Book parseBook(SoapObject product){
        int id = Integer.parseInt(product.getProperty("id").toString());
        String pcode = product.getProperty("pcode").toString();
        String picture = product.getProperty("picture").toString();
        String type = product.getProperty("type").toString();
        String title = product.getProperty("title").toString();
        String category = product.getProperty("category").toString();
        int inventory = Integer.parseInt(product.getProperty("inventory").toString());
        String genre = product.getProperty("genre").toString();
        Double price = Double.parseDouble(product.getProperty("price").toString());
        String authors = product.getProperty("authors").toString();
        String pubCo = product.getProperty("pubCo").toString();
        String pubDate = product.getProperty("pubDate").toString();
        Book book = new Book(id, pcode, picture, type, title, category, inventory, genre, price, authors, pubCo, pubDate);
        return book;
    }

    public static ArrayList<Book> getAllBooksByCategories(String category) throws IOException, XmlPullParserException {
        ArrayList<Book> list = new ArrayList<>();
        SoapObject resultRequestSoap = SoapRequest.getByCategory("BOOK_Category", category);
        for(int i = 0; i < resultRequestSoap.getPropertyCount(); i++) {
            SoapObject product = (SoapObject) resultRequestSoap.getProperty(i);
            Book book = parseBook(product);
            list.add(book);
        }
        return list;
    }

    public static Book getBookById(int id) throws IOException, XmlPullParserException {
        SoapObject resultRequestSoap = SoapRequest.getById("BOOK_ID", id);
        SoapObject product = (SoapObject) resultRequestSoap.getProperty(0);
        Book book = parseBook(product);
        return book;
    }
}
