package dao;

import junit.framework.TestCase;
import model.Kweet;
import model.User;
import org.jglue.cdiunit.CdiRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import util.DatabaseCleaner;

import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * Created by Nino Vrijman.
 */
public class UserDaoImpTest extends TestCase {
    UserDao userdao;
    KweetDao kweetdao;

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

        DatabaseCleaner cleaner = new DatabaseCleaner(Persistence.createEntityManagerFactory("KwetterPU").createEntityManager());
        cleaner.clean();

        userdao = new UserDaoImp();
        kweetdao = new KweetDaoImp();
        ((UserDaoImp)userdao).em = Persistence.createEntityManagerFactory("KwetterPU").createEntityManager();
        ((KweetDaoImp)kweetdao).em = Persistence.createEntityManagerFactory("KwetterPU").createEntityManager();

        user1 = new User();
        user1.setUsername("Test1");
        userdao.addUser(user1);
        kweet1 = kweetdao.create("1 #TEST", user1);
        userdao.update(user1);

        user2 = new User();
        user2.setUsername("Test2");
        userdao.addUser(user2);
        kweet2 = kweetdao.create("2 #TEST", user2);
        userdao.update(user2);

        user3 = new User();
        user3.setUsername("Test3");
        userdao.addUser(user3);
        kweet3 = kweetdao.create("3 #TEST", user3);
        userdao.update(user3);

        user4 = new User();
        user4.setUsername("Test4");
        userdao.addUser(user4);
        kweet4 = kweetdao.create("4 #TEST", user4);
        userdao.update(user4);

        user5 = new User();
        user5.setUsername("Test5");
        userdao.addUser(user5);
        kweet5 = kweetdao.create("5 #TEST", user5);
        userdao.update(user5);

        user6 = new User();
        user6.setUsername("Test6");
        userdao.addUser(user6);
        kweet6 = kweetdao.create("6 #TEST", user6);
        userdao.update(user6);

        user7 = new User();
        user7.setUsername("Test7");
        userdao.addUser(user7);
        kweet7 = kweetdao.create("7 #TEST", user7);
        userdao.update(user7);

        user8 = new User();
        user8.setUsername("Test8");
        userdao.addUser(user8);
        kweet8 = kweetdao.create("8 #TEST", user8);
        userdao.update(user8);

        user9 = new User();
        user9.setUsername("Test9");
        userdao.addUser(user9);
        kweet9 = kweetdao.create("9 #TEST", user9);
        userdao.update(user9);

        user10 = new User();
        user10.setUsername("Test10");
        userdao.addUser(user10);
        kweet10 = kweetdao.create("10 #TEST", user10);
        userdao.update(user10);

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
        assertEquals(user1, userdao.getUserByUsername("Test1"));
        assertEquals(user2, userdao.getUserByUsername("Test2"));
        assertEquals(user8, userdao.getUserByUsername("Test8"));
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