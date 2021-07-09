package com.example.brookeandcoapp.model;

import java.util.ArrayList;

public class CartHolder {
    public static ArrayList<Product> cart = new ArrayList<>();
    public Product product;

    public CartHolder(){ }

    public static void addToCart(Product product){
        cart.add(product);
    }

    public static void remove(int id){
        for(Product product : getCart()){
            if(product.getId() == id){
                getCart().remove(product);
                return;
            }
        }
    }

    public static int getCount(){
        int number = 0;
        for(int i = 0; i < getCart().size(); i++){
            number++;
        }
        return number;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public static ArrayList<Product> getCart() {
        return cart;
    }

    public static void setCart(ArrayList<Product> cart) {
        CartHolder.cart = cart;
    }

    @Override
    public String toString() {
        return "CartHolder{" +
                "product=" + product +
                '}';
    }
}
