package com.example.brookeandcoapp.model;

public class Game extends Product{

        public String console;
        public String company;
        public String reldate;

        public Game() {

        }

        public Game(int id, String pcode, String picture, String type, String title, String category, int inventory, String genre, double price, String console, String company, String reldate){
            super(id, pcode, picture, type, title, category, inventory, genre, price);
            this.console = console;
            this.company = company;
            this.reldate = reldate;
        }

        public String getConsole() {
            return console;
        }

        public void setConsole(String console) {
            this.console = console;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getReldate() {
            return reldate;
        }

        public void setReldate(String reldate) {
            this.reldate = reldate;
        }
}
