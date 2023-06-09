
import exceptions.AmountToAddInvalidException;
import exceptions.QuantityToSellInvalidException;
import model.Product;
import model.MercadoLibre;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MercadoLibreTest {


    MercadoLibre productList= new MercadoLibre();

    //Setup
    public MercadoLibre setupDelete(){

        MercadoLibre productList1=new MercadoLibre();
        productList1.getProductList().add(new Product("gorro","sasd",10,10, 1,10));

        return productList1;

    }






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


    //No está agregado en las tablas de docs
     @Test
     public void deleteAproductIdProductTest(){

        //Arrange
         MercadoLibre productListDelete=setupDelete();
         Product productToDelete=productListDelete.searchProduct("gorro");
         int id=productToDelete.getIdProduct();



         //Act
         boolean confirm=productListDelete.delete(id);

         assertTrue(confirm);



     }


    @Test
    public void addAmountCorrectlyTest(){

        boolean pass=false;
        //Arrange
        productList.getProductList().add(new Product("computador","Es de carne",100,5,5,0));
        Product productTest=productList.getProductList().get(0);
        productList.addStock(productTest.getName(),10);

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
            Product productTest=productList.getProductList().get(0);
            productList.addStock(productTest.getName(),0);

        }catch (AmountToAddInvalidException ex){

            ex.printStackTrace();
            pass = false;

        }
        assertFalse(pass);
    }


    @Test
    public void amountToSellCorrectlyTest() throws IOException {

        boolean pass=false;
        ArrayList<Product> product = new ArrayList<>();
        product.add( new Product("computador","Es de carne",100,5,5,0));

        ArrayList<Product> cart = new ArrayList<>();
        cart.add(product.get(0));

        Product productFounded = product.get(0);
        productList.quitStock(productFounded);

        if(productFounded.getStock()==4){
            pass=true;
        }

        //assert
        assertTrue(pass);
    }



    @Test
    public void amountToSellInvalidTest() throws QuantityToSellInvalidException {


        boolean pass= true;
        try {
            ArrayList<Product> product = new ArrayList<>();
            product.add(new Product("computador","Es de carne",100,5,5,0));
            productList.quitStock(product.get(0));

        }catch (QuantityToSellInvalidException ex){
            ex.printStackTrace();
            pass = false;

        }
        assertFalse(pass);
    }
}