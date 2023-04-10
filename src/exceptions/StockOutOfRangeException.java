package exceptions;

public class StockOutOfRangeException extends RuntimeException{

    public StockOutOfRangeException(){
        super("The amount available entered is invalid");
    }
}
