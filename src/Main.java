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

            System.out.println(
                    """
                            
                            Welcome To Mercado Libre!
                            1. Add Product.
                            2. Delete Product.
                            3. Add more quantity of a product.
                            4. Exit
                            """
            );


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

                    System.out.println("Type the name of the product to delete");
                    String product= lector.nextLine();
                    productList.delete(product);
                    productList.save();
                    System.out.println("Product successfully removed");

                    break;

                case 3:
                    System.out.println("Type the name of the product");
                    String product1= lector.nextLine();
                    System.out.println("Type the amount you want to add more ");
                    int amount= lector.nextInt();
                    lector.nextLine();
                    System.out.println(productList.addStock(product1,amount));
                    productList.save();


                    break;


                case 4:
                    System.exit(0);
                    break;
            }
        }

    }
}