import model.*;


import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static ProductList productList = new ProductList();
    static UserList userList = new UserList();

    static Scanner lector = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        //Cargar la informacion
        productList.load();
        userList.load();


        while (true) {

            System.out.println(
                    """
                                                        
                            Welcome To Mercado Libre! (version manager)
                            1. Add Product.
                            2. Delete Product.
                            3. Add more quantity of a product.
                            4. Version User.
                            5. Exit
                            """
            );


            int option = Integer.parseInt(lector.nextLine());

            switch (option) {

                //case Version manager
                case 1:

                    System.out.println("Type the name of the product");
                    String name = lector.nextLine();

                    System.out.println("Type the product description");
                    String description = lector.nextLine();

                    System.out.println("Type the price $");

                    double price = lector.nextDouble();
                    lector.nextLine();

                    System.out.println("Type the stock");
                    int stock = lector.nextInt();
                    lector.nextLine();

                    System.out.println("Type the category, options:\n\n 1.Books\n 2.Electronic\n 3.Clothes and accessories\n 4.Food and drinks\n 5.Stationery\n 6.Sports\n 7.Beauty and personal care products\n 8.Toys and games");
                    int optionCategory = lector.nextInt();
                    lector.nextLine();


                    productList.getProductList().add(new Product(name, description, price, stock, optionCategory, 0));
                    productList.save();
                    System.out.println("The product has been added successfully. ✓\n");
                    break;

                //case Version manager
                case 2:

                    productList.showInformationToManager();
                    System.out.print("\nType the id of the product to delete: ");

                    int productIdToDelete = lector.nextInt();
                    lector.nextLine();
                   boolean confirmDelete= productList.delete(productIdToDelete);

                   if(confirmDelete) {
                       productList.save();
                       System.out.println("Product successfully removed");
                   }
                    break;

                //case Version manager
                case 3:
                    productList.showInformationToManager();
                    System.out.print("\nType the id of the product: ");
                    int product1 = lector.nextInt();
                    lector.nextLine();
                    System.out.println("Type the amount you want to add more ");
                    int amount = lector.nextInt();
                    lector.nextLine();
                    System.out.println(productList.addStock(product1, amount));
                    productList.save();


                    break;

                //case Version manager
                case 4:

                    versionUser();
                    break;

                //case Version manager
                case 5:
                    System.exit(0);
                    break;


            }

        }


    }




    public static void versionUser() throws IOException {

        int continueUserMenu = 1;

        while (continueUserMenu == 1) {


            System.out.println(
                    """
                                                                    
                            Welcome To Mercado Libre! (version User)
                            1. Login.
                            2. Sign up.                             
                            3. Go back.
                            """
            );

            int optionUser = Integer.parseInt(lector.nextLine());

            switch (optionUser) {
                //Case version user
                case 1:
                    System.out.println("Type your user name");
                    String user = lector.nextLine();
                    boolean exist = userList.userExist(user);


                    if (exist) {

                        int optionContinueExistUser = 1;
                        while (optionContinueExistUser == 1) {
                            //Si el usuario existe


                            System.out.println(
                                    """
                                                                                    
                                            Choose one option
                                            1. Buy A product.                                           
                                            2. Go back.                           
                                            """
                            );
                            int optionUserExist = Integer.parseInt(lector.nextLine());

                            switch (optionUserExist) {

                                //Case if user exist
                                case 1:
                                    System.out.println("Available Products: \n");
                                    productList.show();
                                    System.out.print("\nType the product you want to buy: ");
                                    String productToBuy = lector.nextLine();
                                    Product productVerification = productList.searchProduct(productToBuy);

                                    if (productVerification != null) {

                                        productList.showAllInformation(productVerification);
                                        System.out.println("\nYou want to buy it?\n1. yes\n2. Not");
                                        int confirm = lector.nextInt();
                                        lector.nextLine();
                                        if (confirm == 1) {
                                            //Agrego al carrito
                                            System.out.println("\nType the quantity of the product you want to buy: ");
                                            int quantityToSell = lector.nextInt();
                                            lector.nextLine();

                                            System.out.println(productList.saleOfAProduct(productVerification.getName(), quantityToSell));
                                            productList.save();

                                        }


                                    } else {
                                        System.out.println("The product doesn't exist");
                                    }

                                    break;

                                //Case if user exist
                                case 2:
                                    optionContinueExistUser = 0;
                                    break;


                            }

                        }
                    }
                    break;

                //Case version user
                case 2:

                    System.out.println("Type your name");
                    String realName = lector.nextLine();
                    System.out.println("Type your User name");
                    String userName = lector.nextLine();
                    boolean existUser = userList.userExist(userName);

                    while (existUser) {
                        System.out.println("The username is already taken");
                        System.out.println("Type your User name:");
                        String userNameToVerify = lector.nextLine();
                        existUser = userList.userExist(userNameToVerify);
                    }

                    userList.getUsersList().add(new User(realName, userName));
                    userList.save();
                    System.out.println("User created successfully.");
                    break;

                //Case version user
                case 3:
                    continueUserMenu = 0;
                    break;


            }
        }




    }









}
