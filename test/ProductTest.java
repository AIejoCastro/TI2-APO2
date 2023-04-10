
import exceptions.StockOutOfRangeException;
import exceptions.PriceOutOfRangeException;
import model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ProductTest {


public Product setupCorrectlyProduct(){

    Product product= new Product("empanada","Es de carne",100,5,5,0);

    return product;
}
public Product setup1IncorrectPriceProduct(){

    Product product= new Product("empanada","Es de carne",-5,5,5,0);
    return product;
}

public Product setup2IncorrectStockProduct(){

    Product product= new Product("empanada","Es de carne",10,-100,5,0);
    return product;

}



@Test
    public void createAProductWithCorrectlyPriceTest(){

    boolean pass=false;

    //Arrange
    Product product=setupCorrectlyProduct();

    //act
    double price=product.getPrice();

    if(price>0){
        pass=true;
    }

    //assert
    assertTrue(pass);


}


@Test
    public void createAProductWithIncorrectPriceTest() throws PriceOutOfRangeException {

    boolean pass=true;
    //Arrange

    try {
        Product product=setup1IncorrectPriceProduct();
    } catch (PriceOutOfRangeException ex) {
        ex.printStackTrace();
        pass = false;
    }

    //assert
    assertFalse(pass);



}

@Test
    public void createAProductWithCorrectlyStockTest(){

    boolean pass=false;

    //Arrange
    Product product=setupCorrectlyProduct();

    //act
    double stock=product.getStock();

    if(stock>0){
        pass=true;
    }

    //assert
    assertTrue(pass);

}

@Test
    public void createAproductWithIncorrectStockTest() throws StockOutOfRangeException{

    boolean pass=true;
    //Arrange

    try {
        Product product=setup2IncorrectStockProduct();
    } catch (StockOutOfRangeException ex) {
        ex.printStackTrace();
        pass = false;
    }

    //assert
    assertFalse(pass);

}



}
