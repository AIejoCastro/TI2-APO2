import model.CategoryProduct;
import model.Product;
import model.ProductList;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static ProductList productList = new ProductList();

    public static void main(String[] args) throws IOException {

        //Cargar la informacion
        productList.load();


        Scanner lector = new Scanner(System.in);

        while(true){
            System.out.println("Welcome To Mercado Libre\n1. Añadir Producto\n2. Mostrar\n3. Salir\n");
            int option = Integer.parseInt(lector.nextLine());
            switch (option){
                case 1:
                    //Nombre++Code++Edad
                    System.out.println("Type the name of the product");
                    String name= lector.nextLine();

                    System.out.println("Type the product description");
                    String description= lector.nextLine();

                    System.out.println("Type the price $");

                    double price= lector.nextDouble();
                    lector.nextLine();

                    System.out.println("Type the stock");
                    int stock=lector.nextInt();
                    lector.nextLine();

                    System.out.println("Type the category, options:\n\n 1.Books\n 2.Electronic\n 3.Clothes and accessories\n 4.Food and drinks\n 5.Stationery\n 6.Sports\n 7.Beauty and personal care products\n 8.Toys and games");
                    int optionCategory= lector.nextInt();
                    lector.nextLine();


                    productList.getProductList().add(new Product(name,description,price,stock,optionCategory,0));
                    productList.save();
                    System.out.println("The product has been added successfully. ✓\n");
                    break;

                case 2:
                    productList.show();
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }

    }
}