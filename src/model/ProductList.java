package model;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import exceptions.AmountToAddInvalidException;
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


    public boolean delete(String name){

        boolean confirm=true;

        for (int i = 0; i < getProductList().size(); i++) {

            if(getProductList().get(i).getName().equals(name)){

                getProductList().remove(i);
                return confirm=false;

            }

        }
            return  confirm;

    }


    public Product searchProduct(String name){

        Product product=null;

        for (int i = 0; i < getProductList().size(); i++) {

            if(getProductList().get(i).getName().equals(name)){
                product=getProductList().get(i);
            }

        }
        return  product;

    }

    public String addStock(String name, int amount) throws AmountToAddInvalidException{


        Product product=searchProduct(name);
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
        Product product=searchProduct(nameProduct);

        if(product!=null && quantityToSell>0 && product.getStock()>quantityToSell){


            product.setStock(product.getStock()-quantityToSell);
            message="The purchase has been successful";
            product.setQuantitieSold(quantityToSell);


            //Verificar si es necesario un sout con una excepción
        }else{
            throw  new QuantityToSellInvalidException();
        }

            return message;

    }


    public void show() {

        for (Product s : products) {
            System.out.println("Product name: "+s.getName()+", price: "+s.getPrice()+"$"+" stock: "+s.getStock());
        }
    }

    public void showAllInformation(Product product){

        System.out.println("\nAll about product:\nThe name of the product is: "+product.getName()+"\nDescription: "+product.getDescription()+"\nCategory: "+product.getCategoryProduct()+"\nprice:"+product.getPrice()+"$"+"\nsold units: "+product.getQuantitieSold() );

    }
}