import model.User;
import model.UserList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserListTest {


    @Test
    public void checkUserAlreadyExistTest(){

        boolean pass=false;

        //Arrange
        User userTest= new User("rodrigo","s");
        UserList userListTest=new UserList();
        userListTest.getUsersList().add(userTest);


        //act
        if(userListTest.userExist(userTest.getUserName())){

            pass=true;

        }


        assertTrue(pass);

    }



    @Test
    public void checkUserAlreadydoesNotExistTest(){

        boolean pass=false;

        //Arrange
        UserList userListTest=new UserList();


        //act
        if(userListTest.userExist("lolito")){

            pass=true;

        }


        assertFalse(pass);

    }





}
