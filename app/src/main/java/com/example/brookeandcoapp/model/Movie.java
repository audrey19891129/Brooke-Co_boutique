package com.example.brookeandcoapp.model;

public class Movie extends Product{
    public String director;
    public String actors;
    public String relyear;

    public Movie() {

    }

    public Movie(int id, String pcode, String picture, String type, String title, String category, int inventory, String genre, double price, String director, String actors, String relyear){
        super(id, pcode, picture, type, title, category, inventory, genre, price);
        this.director = director;
        this.actors = actors;
        this.relyear = relyear;
    }

    public String getDirector() {
        return director;
    }


    public void setDirector(String director) {
        this.director = director;
    }


    public String getActors() {
        return actors;
    }


    public void setActors(String actors) {
        this.actors = actors;
    }


    public String getRelyear() {
        return relyear;
    }


    public void setRelyear(String relyear) {
        this.relyear = relyear;
    }
}
