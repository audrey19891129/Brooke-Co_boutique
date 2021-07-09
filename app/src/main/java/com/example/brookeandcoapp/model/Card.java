package com.example.brookeandcoapp.model;

public class Card {

    public int card_id;
    public int clientId;
    public String type;
    public String card_number;
    public String security_code;
    public String holdername;
    public String expiration;

    public Card(){ }

    public Card(int card_id, int clientId, String type, String card_number, String security_code, String holdername, String expiration)
    {
        this.card_id = card_id;
        this.clientId = clientId;
        this.type = type;
        this.card_number = card_number;
        this.security_code = security_code;
        this.holdername = holdername;
        this.expiration = expiration;
    }

    public int getCard_id() { return card_id; }
    public void setCard_id(int card_id) {this.card_id = card_id; }
    public int getClient_id() {return clientId;}
    public void setClient_id(int client_id) {this.clientId = client_id; }
    public String getType() {return type; }
    public void setType(String type) {this.type = type; }
    public String getCard_number() {return card_number; }
    public void setCard_number(String card_number) {this.card_number = card_number; }
    public String getSecurity_code() {return security_code; }
    public void setSecurity_code(String security_code) {this.security_code = security_code; }
    public String getHoldername() { return holdername; }
    public void setHoldername(String holdername) {this.holdername = holdername; }
    public String getExpiration() {return expiration; }
    public void setExpiration(String expiration) {this.expiration = expiration; }
}
