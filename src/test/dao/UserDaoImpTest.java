package dao;

import junit.framework.TestCase;
import model.Kweet;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * Created by Nino Vrijman.
 */
public class UserDaoImpTest extends TestCase {
    UserDao userdao;

    User user1;
    User user2;
    User user3;
    User user4;
    User user5;
    User user6;
    User user7;
    User user8;
    User user9;
    User user10;

    Kweet kweet1;
    Kweet kweet2;
    Kweet kweet3;
    Kweet kweet4;
    Kweet kweet5;
    Kweet kweet6;
    Kweet kweet7;
    Kweet kweet8;
    Kweet kweet9;
    Kweet kweet10;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        userdao = new UserDaoImp();
        ((UserDaoImp)userdao).em = Persistence.createEntityManagerFactory("KwetterPU").createEntityManager();

        user1 = userdao.addUser(new User());
        kweet1 = new Kweet("1 #TEST", user1);

        user2 = userdao.addUser(new User());
        kweet2 = new Kweet("2 #TEST", user2);

        user3 = userdao.addUser(new User());
        kweet3 = new Kweet("3 #TEST", user3);

        user4 = userdao.addUser(new User());
        kweet4 = new Kweet("4 #TEST", user4);

        user5 = userdao.addUser(new User());
        kweet5 = new Kweet("5 #TEST", user5);

        user6 = userdao.addUser(new User());
        kweet6 = new Kweet("6 #TEST", user6);

        user7 = userdao.addUser(new User());
        kweet7 = new Kweet("7 #TEST", user7);

        user8 = userdao.addUser(new User());
        kweet8 = new Kweet("8 #TEST", user8);

        user9 = userdao.addUser(new User());
        kweet9 = new Kweet("9 #TEST", user9);

        user10 = userdao.addUser(new User());
        kweet10 = new Kweet("10 #TEST", user10);

        user1.follow(user2);
        user1.follow(user3);
        user1.follow(user4);

        user2.likeKweet(kweet3);

        user2.follow(user3);
        user3.follow(user4);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddUser() throws Exception{
        assertEquals(null, userdao.addUser(user1));
    }

    @Test
    public void testGetUserByUsername() throws Exception {
        user1.setUsername("Peter Monroe");
        user2.setUsername("Theo Hubers");
        user8.setUsername("Hackerman");

        assertEquals(user1, userdao.getUserByUsername("Peter Monroe"));
        assertEquals(user2, userdao.getUserByUsername("Theo Hubers"));
        assertEquals(user8, userdao.getUserByUsername("Hackerman"));
        assertEquals(null, userdao.getUserByUsername("Herman"));
    }

    @Test
    public void testGetRecentKweets() throws Exception {
        List<Kweet> user1Kweets = new ArrayList<Kweet>();
        user1Kweets.add(kweet1);
        assertEquals(user1Kweets, userdao.getRecentKweets(user1, 0, 2));
        user1Kweets.add(new Kweet("#11 Test", user1));
        assertEquals(user1Kweets, userdao.getRecentKweets(user1, 0, 2));
    }

    @Test
    public void testGetFollowers() throws Exception {
        List<User> userList = new ArrayList<User>();
        userList.add(user1);
        assertEquals(userList, userdao.getFollowers(user2));
        user2.follow(user3);
        userList.add(user2);
        assertEquals(userList, userdao.getFollowers(user3));
    }

    @Test
    public void testGetFollowing() throws Exception {
        List<User> userList = new ArrayList<User>();
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        assertEquals(userList, userdao.getFollowing(user1));
    }

    @Test
    public void testGetTimelineKweets() throws Exception {
        assertEquals(4, userdao.getTimelineKweets(user1, 0, 8).size());
        assertEquals(kweet3, userdao.getTimelineKweets(user1, 2, 1).get(0));
    }
}