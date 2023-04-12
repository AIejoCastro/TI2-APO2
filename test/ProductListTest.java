
import model.Product;
import model.ProductList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductListTest {


    ProductList productList= new ProductList();


    @Test
    public void addCorrectlyProductToListTest(){

        boolean pass=false;
        //Arrange

       productList.getProductList().add(new Product("computador","Es de carne",100,5,5,0));
        productList.getProductList().add(new Product("bellaca","Es de carne",100,5,5,0));

        //act
        if(productList.getProductList().get(1).getName().equals("bellaca")){

            pass=true;

        }


        assertTrue(pass);

    }


    @Test
    public void removeAproductOfTheListTest(){
        //Arrange
        productList.getProductList().add(new Product("computador","Es de carne",100,5,5,0));

        //Act
        boolean confirm=productList.getProductList().remove("computador");

        assertFalse(confirm);

    }





}
