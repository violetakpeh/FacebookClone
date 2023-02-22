import com.example.facebookclone.DOA.PostDatabase;
import com.example.facebookclone.DOA.UserDatabase;
import com.example.facebookclone.model.Post;
import com.example.facebookclone.model.User;
import com.example.facebookclone.utilities.ConnectionManager;
import com.example.facebookclone.utilities.Encryption;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class PostTest {

    private Connection connection;
   // UserDatabase userDatabase = mock(UserDatabase.class);
    public Connection getConnection() {
        connection = ConnectionManager.getConnection();
        return connection;
    }

    private User user1;
    private User user2;
    private Post post;
    PostDatabase postDatabase =mock(PostDatabase.class);

    public void setUp(){
        user1 =new User();
        user1.setId(1);
        user1.setFirstname("Victor");
        user1.setLastname("Daniels");
        user1.setEmail("victordaniels@yahoo.com");
        String encr =  Encryption.encryptPassword("12345");
        user1.setPassword(encr);
        user1.setDob("1992-05-30");
        user1.setGender("Male");


        user2 = new User();
        user2.setFirstname("Joy");
        user2.setLastname("Taiwo");
        user2.setEmail("joy.taiwo@yahoo.com");
         encr = Encryption.encryptPassword("12345");
        user2.setPassword(encr);
        user2.setDob("2000-01-20");
        user2.setGender("Female");

        post = new Post();
        post.setBody("Facebook Clone is working");
        post.setName("victor");
        post.setImageName("image");
        post.setTitle("Facebook");
        post.setId(1);


    }

    @Test
    void createPostTest(){
        postDatabase = new PostDatabase(getConnection());
        boolean b = postDatabase.createPost(1,post);
        assertTrue(b);
    }

    @Test
    void getPostTest(){
        postDatabase = new PostDatabase(getConnection());
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        List<Post> expected = postDatabase.getPosts(user1);
        List<Post> actual = posts;
        assertEquals(expected, actual);

    }
    @Test
    void getPostByIdTest(){
        postDatabase = new PostDatabase(getConnection());
        postDatabase.createPost(1,post);
        Post expected = postDatabase.getPostById(1);
        assertEquals(expected,post);
    }


    @Test
    void makeComment(){
        postDatabase = new PostDatabase(getConnection());

        assertTrue(postDatabase.makeComment(1,1,"I am testing this method"));

    }

    @Test
    void testDeletePost() {
        postDatabase = new PostDatabase(getConnection());
        postDatabase.createPost(1,post);
        assertTrue(PostDatabase.deletePost(1, 1));

    }
}
