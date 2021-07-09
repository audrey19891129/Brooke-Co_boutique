package com.example.brookeandcoapp.model;

public class Book extends Product{
    public String authors;
    public String pubCo;
    public String pubDate;

    public Book() {}

    public Book(int id, String pcode, String picture, String type, String title, String category, int inventory, String genre, double price, String authors, String pubCo, String pubDate){
        super(id, pcode, picture, type, title, category, inventory, genre, price);
        this.authors = authors;
        this.pubCo = pubCo;
        this.pubDate = pubDate;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPubCo() {
        return pubCo;
    }

    public void setPubCo(String pubCo) {
        this.pubCo = pubCo;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "Book [id=" + this.getId() + ", pcode=" + this.getPcode() + ", picture=" + this.getPicture() + ", type=" + this.getType() + ", title=" + this.getTitle()+ ", category=" + this.getCategory() + ", inventory=" + this.getInventory() + ", genre=" + this.getGenre() + ", price=" + this.getPrice() + "authors=" + authors + ", pubCo=" + pubCo + ", pubDate=" + pubDate + "]";
    }
}
