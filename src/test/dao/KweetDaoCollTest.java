package dao;

import junit.framework.TestCase;
import model.Kweet;
import model.User;

/**
 * Created by Nino Vrijman.
 */
public class KweetDaoCollTest extends TestCase {

    KweetDao kweetdao;

    User user1;
    User user2;
    User user3;
    User user4;
    User user5;

    Kweet kweet1;
    Kweet kweet2;
    Kweet kweet3;
    Kweet kweet4;
    Kweet kweet5;

    public void setUp() throws Exception {
        super.setUp();

        kweetdao = new KweetDaoColl();

        user1 = new User();
        kweet1 = kweetdao.create("1 #TEST", user1);


        user2 = new User();
        kweet2 = kweetdao.create("2 #TEST", user2);

        user3 = new User();
        kweet3 = kweetdao.create("3 #TEST", user3);

        user4 = new User();
        kweet4 = kweetdao.create("4 #TEST", user4);

        user5 = new User();
        kweet5 = kweetdao.create("5 #TEST", user5);

        user1.follow(user2);
        user1.follow(user3);
        user1.follow(user4);

        user2.likeKweet(kweet3);

        user2.follow(user3);
        user3.follow(user4);
    }

    public void tearDown() throws Exception {

    }

    public void testFindByContent() throws Exception {
        assertEquals(1, kweetdao.findByContent("1 #").size());
        assertEquals(5, kweetdao.findByContent("#TEST").size());
        assertEquals(kweet1, kweetdao.findByContent("1 #").get(0));
        assertEquals(kweet1, kweetdao.findByContent("#test").get(0));
        assertEquals(kweet1, kweetdao.findByContent("#TEST").get(0));
    }

    public void testRemove() throws Exception {
        assertTrue(kweetdao.remove(kweet5));
        assertEquals(0, user5.getKweets().size());
        assertFalse(kweetdao.remove(kweet5));
    }

}