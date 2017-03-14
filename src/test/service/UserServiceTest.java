package service;

import junit.framework.TestCase;
import model.Kweet;
import model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nino Vrijman.
 */
public class UserServiceTest extends TestCase {

    UserService userService;

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

    public void setUp() throws Exception {
        super.setUp();
        userService = new UserService();

        user1 = userService.addUser(new User());
        kweet1 = new Kweet("1 #TEST", user1);

        user2 = userService.addUser(new User());
        kweet2 = new Kweet("2 #TEST", user2);

        user3 = userService.addUser(new User());
        kweet3 = new Kweet("3 #TEST", user3);

        user4 = userService.addUser(new User());
        kweet4 = new Kweet("4 #TEST", user4);

        user5 = userService.addUser(new User());
        kweet5 = new Kweet("5 #TEST", user5);

        user6 = userService.addUser(new User());
        kweet6 = new Kweet("6 #TEST", user6);

        user7 = userService.addUser(new User());
        kweet7 = new Kweet("7 #TEST", user7);

        user8 = userService.addUser(new User());
        kweet8 = new Kweet("8 #TEST", user8);

        user9 = userService.addUser(new User());
        kweet9 = new Kweet("9 #TEST", user9);

        user10 = userService.addUser(new User());
        kweet10 = new Kweet("10 #TEST", user10);

        user1.follow(user2);
        user1.follow(user3);
        user1.follow(user4);

        user2.likeKweet(kweet3);

        user2.follow(user3);
        user3.follow(user4);
    }

    public void tearDown() throws Exception {

    }

    public void testAddUser() throws Exception{
        assertEquals(null, userService.addUser(user1));
    }

    public void testGetUserByUsername() throws Exception {
        user1.setUsername("Peter Monroe");
        user2.setUsername("Theo Hubers");
        user8.setUsername("Hackerman");

        assertEquals(user1, userService.getUserByUsername("Peter Monroe"));
        assertEquals(user2, userService.getUserByUsername("Theo Hubers"));
        assertEquals(user8, userService.getUserByUsername("Hackerman"));
        assertEquals(null, userService.getUserByUsername("Herman"));
    }

    public void testGetRecentKweets() throws Exception {
        List<Kweet> user1Kweets = new ArrayList<Kweet>();
        user1Kweets.add(kweet1);
        assertEquals(user1Kweets, userService.getRecentKweets(user1, 0, 2));
        user1Kweets.add(new Kweet("#11 Test", user1));
        assertEquals(user1Kweets, userService.getRecentKweets(user1, 0, 2));
    }

    public void testGetFollowers() throws Exception {
        List<User> userList = new ArrayList<User>();
        userList.add(user1);
        assertEquals(userList, userService.getFollowers(user2));
        user2.follow(user3);
        userList.add(user2);
        assertEquals(userList, userService.getFollowers(user3));
    }

    public void testGetFollowing() throws Exception {
        List<User> userList = new ArrayList<User>();
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        assertEquals(userList, userService.getFollowing(user1));
    }

    public void testGetTimelineKweets() throws Exception {
        assertEquals(4, userService.getTimelineKweets(user1, 0, 8).size());
        assertEquals(kweet3, userService.getTimelineKweets(user1, 2, 1).get(0));
    }

}