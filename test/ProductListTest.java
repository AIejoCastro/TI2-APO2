
import exceptions.PriceOutOfRangeException;
import exceptions.AmountToAddInvalidException;
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


    @Test
    public void addAmountCorrectlyTest(){

        boolean pass=false;
        //Arrange
        productList.getProductList().add(new Product("computador","Es de carne",100,5,5,0));
        productList.addStock("computador",10);

        int amountToVerify=15;
        Product product= productList.searchProduct("computador");

        if(amountToVerify==product.getStock()){
            pass=true;

        }
        assertTrue(pass);

    }

    @Test
    public void addAmountInvalidTest() throws AmountToAddInvalidException{

        boolean pass= true;
        try {
            productList.getProductList().add(new Product("computador","Es de carne",100,5,5,0));
            productList.addStock("computador",0);

        }catch (AmountToAddInvalidException ex){

            ex.printStackTrace();
            pass = false;

        }
        assertFalse(pass);
    }





}
