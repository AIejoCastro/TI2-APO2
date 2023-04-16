package exceptions;

public class QuantityToSellInvalidException extends RuntimeException{

    public QuantityToSellInvalidException(){
        super("The amount you want to buy is invalid");
    }

}
