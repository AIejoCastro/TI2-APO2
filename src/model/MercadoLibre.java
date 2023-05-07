package model;

import com.google.gson.Gson;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import com.google.gson.GsonBuilder;
import exceptions.AmountToAddInvalidException;
import exceptions.DeleteANonExistentProduct;

public class MercadoLibre {

    static String folder = "data";

    static String path = "data/data.txt";
    static String pathOrders = "data/dataOrdersList.txt";

    ArrayList<Product> products;
    ArrayList<Order> orders;
    static BinarySearch binarySearch = new BinarySearch();

    public MercadoLibre() {
        products = new ArrayList<Product>();
        orders = new ArrayList<Order>();
    }

    public ArrayList<Product> getProductList() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void saveProducts() throws IOException {
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file);

        Gson gson = new Gson();
        String data = gson.toJson(products);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        writer.write(data);
        writer.flush();

        fos.close();
    }

    public void loadProducts() throws IOException{
        File file = new File(path);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String content = "";
            String line = "";
            while ((line = reader.readLine()) != null) {
                content += line + "\n";
            }
            System.out.println(content);
            Gson gson = new Gson();
            Product[] array = gson.fromJson(content, Product[].class);
            products.addAll(Arrays.asList(array));
            fis.close();
        } else {
            File f = new File(folder);
            if (!f.exists()) {
                f.mkdirs();
            }
            file.createNewFile();
        }
    }

    public void saveOrders() throws IOException {
        File file = new File(pathOrders);
        FileOutputStream fos = new FileOutputStream(file);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String data = gson.toJson(orders);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        writer.write(data);
        writer.flush();

        fos.close();
    }

    public void loadOrders() throws IOException{
        File file = new File(pathOrders);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String content = "";
            String line = "";
            while ((line = reader.readLine()) != null) {
                content += line + "\n";
            }
            System.out.println(content);
            Gson gson = new Gson();
            Order[] array = gson.fromJson(content, Order[].class);
            orders.addAll(Arrays.asList(array));
            fis.close();
        } else {
            File f = new File(folder);
            if (!f.exists()) {
                f.mkdirs();
            }
            file.createNewFile();
        }
    }


    public boolean delete(int idProduct)throws DeleteANonExistentProduct {

        boolean confirm=false;

        for (int i = 0; i < getProductList().size(); i++) {

            if(getProductList().get(i).getIdProduct()==idProduct){

                getProductList().remove(i);
                return confirm=true;

            }

        }
            throw new DeleteANonExistentProduct();

    }


    public Product searchProductManager(String productId){


        Product product=null;

        for (int i = 0; i < getProductList().size(); i++) {

            if(getProductList().get(i).getName().equalsIgnoreCase(productId)){
                product=getProductList().get(i);
            }

        }
        return  product;

    }


    public Product searchProduct(String nameProduct){


        Product product=null;

        for (int i = 0; i < getProductList().size(); i++) {

            if(getProductList().get(i).getName().equals(nameProduct)){
                product=getProductList().get(i);
            }

        }
        return  product;

    }


    public String addStock(String idProduct, int amount) throws AmountToAddInvalidException{
        Product product=searchProductManager(idProduct);
        String message="";

        if (product!=null && amount>0){
            product.setStock(product.getStock()+amount);
            message += "The amount has been added successfully.";
        } else if (amount<=0) {
            throw new AmountToAddInvalidException();
        } else {
            message+="The product doesn't exist";
        }
        return  message;

    }

    public String quitStock(Product product) {
        String msg = "";
        if (product.getStock() >= 1) {
            product.setStock(product.getStock() - 1);
            msg = "The product " + product.getName() + " have " + product.getStock() + " products in stock now";

        } else {
            msg = "The product don't have stock";

        }
        return msg;
    }

    public String saleOfACart(ArrayList<Product> cart, String user) {
        String msg="";
        LocalDate localDate;

        if (cart.get(0) == null) {
            msg = "The cart is empty, you can't buy anything";
        } else {
            double totalPrice = 0;
            for (int i = 0; i < cart.size(); i++) {
                totalPrice += cart.get(i).getPrice();
                cart.get(i).setQuantitiesSold(cart.get(i).getQuantitiesSold() + 1);
            }
            Order order = new Order(cart, user, totalPrice, LocalDate.now());
            orders.add(order);
            msg = "The cart have been bought";
        }
        return msg;
    }


    public void showInformationToManager(){
        for (Product s : products) {
            System.out.println("Product name: "+s.getName()+", price: "+s.getPrice()+"$"+" stock: "+s.getStock()+" id: "+s.getIdProduct());
        }
    }

    public void show() {

        for (Product s : products) {
            System.out.println("Product name: "+s.getName()+", price: "+s.getPrice()+"$"+" stock: "+s.getStock() + ", quantities sold: " + s.getQuantitiesSold());
        }
    }

    public void showAllInformation(Product product){

        System.out.println("\nAll about product:\nThe name of the product is: "+product.getName()+"\nDescription: "+product.getDescription()+"\nCategory: "+product.getCategoryProduct()+"\nprice:"+product.getPrice()+"$"+"\nsold units: "+product.getQuantitiesSold()+"\nStock: "+product.getStock());

    }

    public ArrayList<Product> rangeSearch(Comparator comparator, double minValue, double maxValue) {
        Collections.sort(products, (a, b) -> {
            return (int) (a.getPrice() - b.getPrice());
        });
        return binarySearch.searchRangeOrInterval(products, comparator, minValue, maxValue, 0, products.size() - 1);
    }

    public ArrayList<Product> categorySearch(Comparator comparator, CategoryProduct category) {
        Collections.sort(products, (a, b) -> {
            return a.getCategoryProduct().compareTo(b.getCategoryProduct());
        });
        return binarySearch.searchCategory(products, comparator, category, 0, products.size() - 1);
    }
}