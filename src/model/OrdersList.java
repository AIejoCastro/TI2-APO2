package model;

import java.io.*;
import java.util.Arrays;

import com.google.gson.Gson;

public class OrdersList {

    static MercadoLibre mercadoLibre = new MercadoLibre();
    static String folder = "data";
    static String path = "data/dataOrderList.txt";


    public OrdersList() {
    }

    public void save() throws IOException {
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file);

        Gson gson = new Gson();
        String data = gson.toJson(mercadoLibre.getOrders());

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
            Gson gson = new Gson();
            Order[] array = gson.fromJson(content, Order[].class);
            mercadoLibre.getOrders().addAll(Arrays.asList(array));
            fis.close();
        } else {
            File f = new File(folder);
            if (!f.exists()) {
                f.mkdirs();
            }
            file.createNewFile();
        }
    }

    public void addOrder(Order order) {
        mercadoLibre.getOrders().add(order);
    }
}