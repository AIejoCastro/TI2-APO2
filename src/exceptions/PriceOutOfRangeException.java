package exceptions;

import java.io.IOException;

public class PriceOutOfRangeException extends RuntimeException {

    public PriceOutOfRangeException(){
        super("The price entered is not valid");
    }

}
