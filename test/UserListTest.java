import model.User;
import model.UserList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserListTest {
    
=======
    public void testUserExistWithValidUserName() {
        UserList userList = new UserList();
        userList.getUsersList().add(new User("JohnDoe", "John"));
        userList.getUsersList().add(new User("JaneDoe", "Jane"));
        boolean result = userList.userExist("John");
        assertTrue(result);
    }

    @Test
    public void testUserExistWithNoUsers() {
        UserList userList = new UserList();
        boolean result = userList.userExist("John");
        assertFalse(result);
    }
>>>>>>> alejo
}
