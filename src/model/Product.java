package model;

import exceptions.PriceOutOfRangeException;
import exceptions.StockOutOfRangeException;

public class Product {

    private String name;
    private String description;
    private double price;

    private int stock;
    private int quantitieSold;

    private int idProduct=0;

    private CategoryProduct categoryProduct;








    public Product(String name, String description, double price, int stock, int category, int quantitieSold) throws PriceOutOfRangeException, StockOutOfRangeException {
        this.name = name;
        this.description = description;
        if(price<=0){
            throw new PriceOutOfRangeException();
        }
        else {
            this.price = price;
        }


        if(stock<=0){
            throw new StockOutOfRangeException();
        }
        else {
            this.stock = stock;
        }

        this.quantitieSold = quantitieSold;


        if(category==1){
            categoryProduct=CategoryProduct.Books;
        } else if (category==2) {
            categoryProduct=CategoryProduct.Electronics;

        } else if (category==3) {
            categoryProduct=CategoryProduct.ClothesAndAccessories;
        } else if (category==4) {
            categoryProduct=CategoryProduct.FoodsAndDrinks;
        } else if (category==5) {
            categoryProduct=CategoryProduct.Stationery;
        } else if (category==6) {
            categoryProduct=CategoryProduct.Sports;
        } else if (category==7) {
            categoryProduct=CategoryProduct.BeautyAndPersonalCareProducts;

        } else if (category==8) {
            categoryProduct = CategoryProduct.ToysAndGames;
        }
        else {
            System.out.println("Tira excepción");
        }

        this.idProduct=generateRandomId();

    }



    private int generateRandomId() {
        return (int) (Math.random() * 999999) + 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getQuantitieSold() {
        return quantitieSold;
    }

    public void setQuantitieSold(int quantitieSold) {
        this.quantitieSold = quantitieSold;
    }

    public CategoryProduct getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(CategoryProduct categoryProduct) {
        this.categoryProduct = categoryProduct;
    }


    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
}


