import model.*;


import java.io.IOException;
import java.util.*;

public class Main {

    static MercadoLibre mercadoLibre = new MercadoLibre();
    static UserList userList = new UserList();

    static Scanner lector = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        //Cargar la informacion
        mercadoLibre.loadProducts();
        userList.load();


        while (true) {

            System.out.println(
                    """
                                                        
                            Welcome To Mercado Libre! (version manager)
                            1. Add Product.
                            2. Delete Product.
                            3. Add more quantity of a product.
                            4. Orders search.
                            5. Version User.
                            6. Exit
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


                    mercadoLibre.getProductList().add(new Product(name, description, price, stock, optionCategory, 0));
                    mercadoLibre.saveProducts();
                    System.out.println("The product has been added successfully. ✓\n");
                    break;

                //case Version manager
                case 2:

                    mercadoLibre.showInformationToManager();
                    System.out.print("\nType the id of the product to delete: ");

                    int productIdToDelete = lector.nextInt();
                    lector.nextLine();
                   boolean confirmDelete= mercadoLibre.delete(productIdToDelete);

                   if(confirmDelete) {
                       mercadoLibre.saveProducts();
                       System.out.println("Product successfully removed");
                   }
                    break;

                //case Version manager
                case 3:
                    mercadoLibre.showInformationToManager();
                    System.out.print("\nType the name of the product: ");
                    String product1 = lector.nextLine();
                    lector.nextLine();
                    System.out.println("Type the amount you want to add more ");
                    int amount = lector.nextInt();
                    lector.nextLine();
                    System.out.println(mercadoLibre.addStock(product1, amount));
                    mercadoLibre.saveProducts();
                    break;

                //case Version manager
                case 4:


                    break;
                case 5:

                    versionUser();
                    break;

                //case Version manager
                case 6:
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
                                    ArrayList<Product> cart = new ArrayList<>();
                                    int cartOption = 0;
                                    while (cartOption != 3) {
                                        System.out.println("\nPlease select an option" +
                                                "\n1. Add to the cart a product" +
                                                "\n2. Buy all the cart" +
                                                "\n3. Go back");
                                        cartOption = lector.nextInt();
                                        lector.nextLine();

                                        switch (cartOption) {
                                            case 1:
                                                System.out.println("\nChoose how do you want to sort the products" +
                                                        "\n1. Name (A - Z)" +
                                                        "\n2. Name (Z - A)" +
                                                        "\n3. Price (Minor to mayor)" +
                                                        "\n4. Price (Mayor to minor)" +
                                                        "\n5. Price (Range)" +
                                                        "\n6. Category" +
                                                        "\n7. Purchased (Minor to mayor)" +
                                                        "\n8. Purchased (Mayor to minor)");
                                                int option = lector.nextInt();
                                                lector.nextLine();
                                                searchSort(option);
                                                System.out.print("\nType the product you want to add it? ");
                                                String productToBuy = lector.nextLine();
                                                Product productVerification = mercadoLibre.searchProduct(productToBuy);

                                                if (productVerification != null) {

                                                    mercadoLibre.showAllInformation(productVerification);
                                                    System.out.println("\nYou want to buy it?\n1. Yes\n2. Not");
                                                    int confirm = lector.nextInt();
                                                    lector.nextLine();
                                                    if (confirm == 1 && productVerification.getStock() >= 1) {
                                                        //Agrego al carrito
                                                        cart.add(productVerification);
                                                        System.out.println(mercadoLibre.quitStock(productVerification));
                                                        mercadoLibre.saveProducts();
                                                    }

                                                    System.out.println("\nThis is your cart: ");

                                                    for (Product s : cart) {
                                                        System.out.println("Product name: "+s.getName()+", price: "+s.getPrice()+"$");
                                                    }
                                                } else {
                                                    System.out.println("The product doesn't exist");
                                                }
                                                break;
                                            case 2:
                                                System.out.println(mercadoLibre.saleOfACart(cart, user));
                                                mercadoLibre.saveProducts();
                                                //Falta el save orders
                                                break;
                                            case 3:
                                                break;
                                        }
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

    public static void searchSort(int option) {
        switch (option) {
            case 1:
                System.out.println("\nProducts from A to Z");

                Collections.sort(mercadoLibre.getProducts(), (a, b) -> {
                    return a.getName().compareTo(b.getName());
                });

                mercadoLibre.show();
                break;
            case 2:
                System.out.println("\nProducts from Z to A");

                Collections.sort(mercadoLibre.getProducts(), (a, b) -> {
                    return b.getName().compareTo(a.getName());
                });

                mercadoLibre.show();
                break;
            case 3:
                System.out.println("\nProducts from minor to mayor in price");

                //Check
                Collections.sort(mercadoLibre.getProducts(), (a, b) -> {
                    return (int) (a.getPrice() - b.getPrice());
                });

                mercadoLibre.show();
                break;
            case 4:
                System.out.println("\nProducts from mayor to minor in price");
                //Check
                Collections.sort(mercadoLibre.getProducts(), (a, b) -> {
                    return (int) (b.getPrice() - a.getPrice());
                });

                mercadoLibre.show();
                break;
            case 5:
                //Check
                System.out.println("\nMinor range to search");
                int min = lector.nextInt();
                System.out.println("Mayor range to search");
                int max = lector.nextInt();
                lector.nextLine();

                Comparator<Double> compare = (a, b) -> { return (int) (a - b);};
                ArrayList<Product> arr = mercadoLibre.rangeSearch(compare, min, max);

                arr.forEach(product -> {
                    System.out.println("Product name: "+product.getName()+", price: "+product.getPrice()+"$"+" stock: "+product.getStock() + ", quantities sold: " + product.getQuantitiesSold());
                });
                break;
            case 6:
                //Check
                System.out.println("\nType the category, options:\n\n 1.Books\n 2.Electronics\n 3.Clothes and accessories\n 4.Food and drinks\n 5.Stationery\n 6.Sports\n 7.Beauty and personal care products\n 8.Toys and games");
                int category = lector.nextInt();
                lector.nextLine();

                searchForCategories(category);


                break;
            case 7:
                System.out.println("\nProducts from minor to mayor sorted by purchased products");

                Collections.sort(mercadoLibre.getProducts(), (a, b) -> {
                    return (int) (a.getQuantitiesSold() - b.getQuantitiesSold());
                });

                mercadoLibre.show();
                break;
            case 8:
                System.out.println("\nProducts from mayor to minor sorted by purchased products");

                Collections.sort(mercadoLibre.getProducts(), (a, b) -> {
                    return (int) (b.getQuantitiesSold() - a.getQuantitiesSold());
                });

                mercadoLibre.show();
                break;
        }
    }

    public static void searchForCategories(int option) {
        Comparator<CategoryProduct> compare = (a, b) ->  a.compareTo(b);
        ArrayList<Product> arr = new ArrayList<>();
        switch (option) {
            case 1:
                arr = mercadoLibre.categorySearch(compare, CategoryProduct.Books);

                arr.forEach(product -> {
                    System.out.println("Product name: "+product.getName()+", price: "+product.getPrice()+"$"+" stock: "+product.getStock() + ", quantities sold: " + product.getQuantitiesSold());
                });
                break;
            case 2:
                arr = mercadoLibre.categorySearch(compare, CategoryProduct.Electronics);

                arr.forEach(product -> {
                    System.out.println("Product name: "+product.getName()+", price: "+product.getPrice()+"$"+" stock: "+product.getStock() + ", quantities sold: " + product.getQuantitiesSold());
                });
                break;
            case 3:
                arr = mercadoLibre.categorySearch(compare, CategoryProduct.ClothesAndAccessories);

                arr.forEach(product -> {
                    System.out.println("Product name: "+product.getName()+", price: "+product.getPrice()+"$"+" stock: "+product.getStock() + ", quantities sold: " + product.getQuantitiesSold());
                });
                break;
            case 4:
                arr = mercadoLibre.categorySearch(compare, CategoryProduct.FoodsAndDrinks);

                arr.forEach(product -> {
                    System.out.println("Product name: "+product.getName()+", price: "+product.getPrice()+"$"+" stock: "+product.getStock() + ", quantities sold: " + product.getQuantitiesSold());
                });
                break;
            case 5:
                arr = mercadoLibre.categorySearch(compare, CategoryProduct.Stationery);

                arr.forEach(product -> {
                    System.out.println("Product name: "+product.getName()+", price: "+product.getPrice()+"$"+" stock: "+product.getStock() + ", quantities sold: " + product.getQuantitiesSold());
                });
                break;
            case 6:
                arr = mercadoLibre.categorySearch(compare, CategoryProduct.Sports);

                arr.forEach(product -> {
                    System.out.println("Product name: "+product.getName()+", price: "+product.getPrice()+"$"+" stock: "+product.getStock() + ", quantities sold: " + product.getQuantitiesSold());
                });
                break;
            case 7:
                arr = mercadoLibre.categorySearch(compare, CategoryProduct.BeautyAndPersonalCareProducts);

                arr.forEach(product -> {
                    System.out.println("Product name: "+product.getName()+", price: "+product.getPrice()+"$"+" stock: "+product.getStock() + ", quantities sold: " + product.getQuantitiesSold());
                });
                break;
            case 8:
                arr = mercadoLibre.categorySearch(compare, CategoryProduct.ToysAndGames);

                arr.forEach(product -> {
                    System.out.println("Product name: "+product.getName()+", price: "+product.getPrice()+"$"+" stock: "+product.getStock() + ", quantities sold: " + product.getQuantitiesSold());
                });
                break;
        }
    }
}