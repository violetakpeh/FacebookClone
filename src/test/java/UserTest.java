import com.example.facebookclone.DOA.UserDatabase;
import com.example.facebookclone.model.Post;
import com.example.facebookclone.model.User;
import com.example.facebookclone.utilities.ConnectionManager;
import com.example.facebookclone.utilities.Encryption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;


public class UserTest {
   @Mock
    private Connection connection;
    UserDatabase userDatabase = mock(UserDatabase.class);
    public Connection getConnection() {
        connection = ConnectionManager.getConnection();
        return connection;
    }


    private User user1;
    private User user2;
    @BeforeEach
    void setUp() {
        user1 =new User();
        user1.setFirstname("Victor");
        user1.setLastname("Daniels");
        user1.setEmail("victordaniels@yahoo.com");

        String encr =  Encryption.encryptPassword("1234567890");
        String decr = Encryption.decryptPassword("1234567890");
        user1.setPassword("12345");
        user1.setDob("1992-05-30");
        user1.setGender("Male");


        user2 = new User();
        user2.setFirstname("Joy");
        user2.setLastname("Taiwo");
        user2.setEmail("joy.taiwo@yahoo.com");
      //  encr = Encryption.encryptPassword("12345");
        //user2.setPassword(encr);
        user2.setDob("2000-01-20");
        user2.setGender("Female");


    }


    @Test
    void registerUserTest() {
        userDatabase = new UserDatabase(getConnection());
         boolean b = userDatabase.registerUser(user1);
         assertTrue(b);



    }
    @Test
    void loginTest() {
        userDatabase = new UserDatabase(getConnection());
        userDatabase.registerUser(user1);
        User expected = userDatabase.login("victordaniels@yahoo.com","12345");
        assertEquals(expected,user1);
    }
}
