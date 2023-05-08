package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {

    private ArrayList<Product> products;
    private String buyerName;
    private double totalPrice;

    public Order(ArrayList<Product> products, String buyerName, double totalPrice) {
        this.products = products;
        this.buyerName = buyerName;
        this.totalPrice = totalPrice;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String showProducts() {
        String msg = "";
        for (Product p : products) {
            msg += p.toString();
        }
        return msg;
    }
}