package model;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import exceptions.AmountToAddInvalidException;
import exceptions.DeleteANonExistentProduct;
import exceptions.QuantityToSellInvalidException;

public class ProductList {

    static String folder = "data";

    static String path = "data/data.txt";

    ArrayList<Product> products;

    public ProductList() {
        products = new ArrayList<Product>();
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

    public void save() throws IOException {
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file);

        Gson gson = new Gson();
        String data = gson.toJson(products);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        writer.write(data);
        writer.flush();

        fos.close();
    }

    public void load() throws IOException{
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


    public Product searchProductManager(int productId){


        Product product=null;

        for (int i = 0; i < getProductList().size(); i++) {

            if(getProductList().get(i).getIdProduct()==productId){
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


    public String addStock(int idProduct, int amount) throws AmountToAddInvalidException{


        Product product=searchProductManager(idProduct);
        String message="";

        if (product!=null && amount>0){


            product.setStock(product.getStock()+amount);
            message+="The amount has been added successfully.";


        } else if (amount<=0) {
            throw new AmountToAddInvalidException();

        } else {
            message+="The product doesn't exist";

        }

        return  message;

    }




    public String saleOfAProduct(String nameProduct, int quantityToSell) throws QuantityToSellInvalidException{


        String message="";
        //Aqui entran todos los tipos de search este solo es para pruebas sin los filtros de busqueda
        Product product=searchProduct(nameProduct);

        if(product!=null && quantityToSell>0 && product.getStock()>=quantityToSell){


            product.setStock(product.getStock()-quantityToSell);
            message="You successfully add to the cart the product";
            product.setQuantitiesSold(quantityToSell);



        }else if(product.getStock()==0){

            message="Out of stock, will be available soon.";

        }else {
            throw  new QuantityToSellInvalidException();
        }

            return message;

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
}