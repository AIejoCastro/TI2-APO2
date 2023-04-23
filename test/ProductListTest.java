
import exceptions.PriceOutOfRangeException;
import exceptions.AmountToAddInvalidException;
import exceptions.QuantityToSellInvalidException;
import model.CategoryProduct;
import model.Product;
import model.ProductList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductListTest {


    ProductList productList= new ProductList();

    //Setup
    public ProductList setupDelete(){

        ProductList productList1=new ProductList();
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
         ProductList productListDelete=setupDelete();
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


    @Test
    public void amountToSellCorrectlyTest(){

        boolean pass=false;

        //arrange
         productList.getProductList().add( new Product("computador","Es de carne",100,5,5,0));
         Product product= productList.searchProduct("computador");


         productList.saleOfAProduct(product.getName(),1);

         //act
        if(product.getStock()==4){

            pass=true;

        }

        //assert

        assertTrue(pass);




    }



    @Test
    public void amountToSellInvalidTest() throws QuantityToSellInvalidException {


        boolean pass= true;
        try {
            productList.getProductList().add(new Product("computador","Es de carne",100,5,5,0));
            productList.saleOfAProduct("computador",10);

        }catch (QuantityToSellInvalidException ex){

            ex.printStackTrace();
            pass = false;

        }
        assertFalse(pass);


    }


}
