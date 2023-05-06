package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {

    private ArrayList<Product> products;
    private String buyerName;
    private double totalPrice;
    private LocalDate date;

    public Order(ArrayList<Product> products, String buyerName, double totalPrice, LocalDate date) {
        this.products = products;
        this.buyerName = buyerName;
        this.totalPrice = totalPrice;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
