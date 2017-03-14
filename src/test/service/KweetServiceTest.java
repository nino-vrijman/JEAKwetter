package service;

import junit.framework.TestCase;
import model.Kweet;
import model.User;

/**
 * Created by Nino Vrijman on 9-3-2017.
 */
public class KweetServiceTest extends TestCase {
    KweetService kweetService;

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

        kweetService = new KweetService();

        user1 = new User();
        kweet1 = kweetService.create("1 #TEST", user1);


        user2 = new User();
        kweet2 = kweetService.create("2 #TEST", user2);

        user3 = new User();
        kweet3 = kweetService.create("3 #TEST", user3);

        user4 = new User();
        kweet4 = kweetService.create("4 #TEST", user4);

        user5 = new User();
        kweet5 = kweetService.create("5 #TEST", user5);

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
        assertEquals(1, kweetService.findByContent("1 #").size());
        assertEquals(5, kweetService.findByContent("#TEST").size());
        assertEquals(kweet1, kweetService.findByContent("1 #").get(0));
        assertEquals(kweet1, kweetService.findByContent("#test").get(0));
        assertEquals(kweet1, kweetService.findByContent("#TEST").get(0));
    }

    public void testRemove() throws Exception {
        assertTrue(kweetService.remove(kweet5));
        assertEquals(0, user5.getKweets().size());
        assertFalse(kweetService.remove(kweet5));
    }
}