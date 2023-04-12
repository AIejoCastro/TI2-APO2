package model;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class UserList {

    static String folder = "data";

    static String path = "data/dataUserList.txt";

    ArrayList<User> users;

    public UserList() {
        users = new ArrayList<User>();
    }


    public ArrayList<User> getUsersList() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }


    public void save() throws IOException {
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file);

        Gson gson = new Gson();
        String data = gson.toJson(users);

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
            User[] array = gson.fromJson(content, User[].class);
            users.addAll(Arrays.asList(array));
            fis.close();
        } else {
            File f = new File(folder);
            if (!f.exists()) {
                f.mkdirs();
            }
            file.createNewFile();
        }
    }








    public boolean userExist(String userName){

        boolean exist=false;

        for (int i = 0; i < getUsersList().size(); i++) {

            if(getUsersList().get(i).getUserName().equals(userName)){
                exist=true;

            }

        }

        return exist;

    }



}
