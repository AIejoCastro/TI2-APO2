package exceptions;

public class DeleteANonExistentProduct extends RuntimeException{

    public DeleteANonExistentProduct(){
        super("The product does not exist");
    }

}
