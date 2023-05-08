package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OrdersList {

    public ArrayList<Order> orders = new ArrayList<Order>();
    static String folder = "data";
    static String path = "data/dataOrdersList.txt";


    public OrdersList() {
    }

    public void saveOrders() throws IOException {
        File file = new File(path);
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
        File file = new File(path);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String content = "";
            String line = "";
            while ((line = reader.readLine()) != null) {
                content += line + "\n";
            }
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

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public String show() {
        String msg = "";
        for (Order o : orders) {
            msg += "\nBuyer name: " + o.getBuyerName() + "\n--------------------" + "\nProducts: " + o.showProducts() + "\n--------------------" + "\nTotal price: " + o.getTotalPrice() + "$\n";
        }
        return msg;
    }
}