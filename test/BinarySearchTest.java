
import model.BinarySearch;
import model.CategoryProduct;
import model.Order;
import model.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTest {

    BinarySearch binary = new BinarySearch<>();

    @Test
    public void testSearchRangeTotalPrice() {
        // Create a sample list of orders
        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();

        orders.add(new Order(products, "Product A", 100.0));
        orders.add(new Order(products, "Product B", 200.0));
        orders.add(new Order(products, "Product C", 300.0));
        orders.add(new Order(products, "Product D", 400.0));
        orders.add(new Order(products, "Product E", 500.0));

        // Define a comparator for comparing order prices
        Comparator<Double> priceComparator = Comparator.naturalOrder();

        // Test case 1: Search for orders with prices between 200 and 400
        ArrayList<Order> expectedResults1 = new ArrayList<>();
        expectedResults1.add(orders.get(1));
        expectedResults1.add(orders.get(2));
        expectedResults1.add(orders.get(3));
        ArrayList<Order> actualResults1 = binary.searchRangeTotalPrice(orders, priceComparator, 200.0, 400.0, 0, orders.size() - 1);
        assertEquals(expectedResults1, actualResults1);
    }

    @Test
    public void testSearchRangeTotalPriceMax() {
        // Create a sample list of orders
        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();

        orders.add(new Order(products, "Product A", 100.0));
        orders.add(new Order(products, "Product B", 200.0));
        orders.add(new Order(products, "Product C", 300.0));
        orders.add(new Order(products, "Product D", 400.0));
        orders.add(new Order(products, "Product E", 500.0));

        // Define a comparator for comparing order prices
        Comparator<Double> priceComparator = Comparator.naturalOrder();

        ArrayList<Order> expectedResults2 = null;
        ArrayList<Order> actualResults2 = binary.searchRangeTotalPrice(orders, priceComparator, 501.0, Double.MAX_VALUE, 0, orders.size() - 1);
        assertEquals(expectedResults2, actualResults2);
    }

    @Test
    public void searchByName() {
        // Create a sample list of orders
        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();

        orders.add(new Order(products, "Product A", 100.0));
        orders.add(new Order(products, "Product B", 200.0));
        orders.add(new Order(products, "Product C", 300.0));
        orders.add(new Order(products, "Product D", 400.0));
        orders.add(new Order(products, "Product E", 500.0));

        // Define a comparator for comparing order prices
        Comparator<String> priceComparator = (a, b) ->  a.compareTo(b);

        ArrayList<Order> expectedResults1 = new ArrayList<>();
        expectedResults1.add(orders.get(0));
        ArrayList<Order> actualResults1 = binary.searchByName(orders, priceComparator, "Product A", 0, orders.size() - 1);
        assertEquals(expectedResults1, actualResults1);
    }

    @Test
    public void searchByNameThatDosNotExist() {
        // Create a sample list of orders
        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();

        orders.add(new Order(products, "Product A", 100.0));
        orders.add(new Order(products, "Product B", 200.0));
        orders.add(new Order(products, "Product C", 300.0));
        orders.add(new Order(products, "Product D", 400.0));
        orders.add(new Order(products, "Product E", 500.0));

        // Define a comparator for comparing order prices
        Comparator<String> priceComparator = (a, b) ->  a.compareTo(b);

        ArrayList<Order> expectedResults1 = null;
        ArrayList<Order> actualResults1 = binary.searchByName(orders, priceComparator, "Product Z", 0, orders.size() - 1);
        assertEquals(expectedResults1, actualResults1);
    }

    @Test
    public void searchByCategory() {
        // Create a sample list of orders
        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product("Product A", "1", 200, 10, 2, 0));
        products.add(new Product("Product B", "2", 500, 10, 4, 0));
        products.add(new Product("Product C", "3", 300, 10, 1, 0));
        products.add(new Product("Product D", "4", 1000, 10, 7, 0));


        // Define a comparator for comparing order prices
        Comparator<Double> categoryComparator = Comparator.naturalOrder();

        // Test case 1: Search for orders with prices between 200 and 400
        ArrayList<Product> expectedResults1 = new ArrayList<>();
        expectedResults1.add(products.get(0));
        ArrayList<Order> actualResults1 = binary.searchCategory(products, categoryComparator, CategoryProduct.Electronics, 0, products.size() - 1);
        assertEquals(expectedResults1, actualResults1);
    }

    @Test
    public void searchByCategoryThatDoesNotHaveProducts() {
        // Create a sample list of orders
        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product("Product A", "1", 200, 10, 2, 0));
        products.add(new Product("Product B", "2", 500, 10, 4, 0));
        products.add(new Product("Product C", "3", 300, 10, 1, 0));
        products.add(new Product("Product D", "4", 1000, 10, 7, 0));


        // Define a comparator for comparing order prices
        Comparator<Double> categoryComparator = Comparator.naturalOrder();

        // Test case 1: Search for orders with prices between 200 and 400
        ArrayList<Product> expectedResults1 = null;
        ArrayList<Order> actualResults1 = binary.searchCategory(products, categoryComparator, CategoryProduct.Stationery, 0, products.size() - 1);
        assertEquals(expectedResults1, actualResults1);
    }
}
