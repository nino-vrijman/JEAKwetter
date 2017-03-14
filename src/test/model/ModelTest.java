package model;

import junit.framework.TestCase;

/**
 * Created by Nino Vrijman.
 */
public class ModelTest extends TestCase {

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

        user1 = new User();
        kweet1 = new Kweet("1 #TEST", user1);

        user2 = new User();
        kweet2 = new Kweet("2 #TEST", user2);

        user3 = new User();
        kweet3 = new Kweet("3 #TEST", user3);

        user4 = new User();
        kweet4 = new Kweet("4 #TEST", user4);

        user5 = new User();
        kweet5 = new Kweet("5 #TEST", user5);

        user6 = new User();
        kweet6 = new Kweet("6 #TEST", user6);

        user7 = new User();
        kweet7 = new Kweet("7 #TEST", user7);

        user8 = new User();
        kweet8 = new Kweet("8 #TEST", user8);

        user9 = new User();
        kweet9 = new Kweet("9 #TEST", user9);

        user10 = new User();
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

    public void testAddTweet() throws Exception {
        assertEquals(kweet1, user1.getKweets().get(0));
    }

    public void testGetRecentKweets() throws Exception {
        User user = new User();
        Kweet kweet1 = new Kweet("1 #TEST", user);
        Kweet kweet2 = new Kweet("2 #TEST", user);
        Kweet kweet3 = new Kweet("3 #TEST", user);
        Kweet kweet4 = new Kweet("4 #TEST", user);
        Kweet kweet5 = new Kweet("5 #TEST", user);
        Kweet kweet6 = new Kweet("6 #TEST", user);
        Kweet kweet7 = new Kweet("7 #TEST", user);
        Kweet kweet8 = new Kweet("8 #TEST", user);
        Kweet kweet9 = new Kweet("9 #TEST", user);
        Kweet kweet10 = new Kweet("10 #TEST", user);

        assertEquals(user.getKweets(), user.getRecentKweets(0,10));
        assertEquals(user.getKweets().subList(0,5), user.getRecentKweets(0,5));
    }

    public void testLikeTweet() throws Exception {
        assertEquals(user2, kweet3.getLikedBy().get(0));

        user2.likeKweet(kweet3);//Test dubbele like
        assertEquals(1, kweet3.getLikedBy().size());
    }

    public void testGetTimelineKweets() throws Exception {
        assertEquals(4, user1.getTimelineKweets(0,8).size());
        assertEquals(kweet3, user1.getTimelineKweets(2,1).get(0));
    }

    public void testSetName() throws Exception {
        String testString =  "test";
        User u = new User();
        u.setName(testString);
        assertEquals(testString, u.getName());
    }

    public void testSetBio() throws Exception {
        String testString =  "test";
        User u = new User();
        u.setBio(testString);
        assertEquals(testString, u.getBio());
    }

    public void testSetAvatarURL() throws Exception {
        String testString =  "test";
        User u = new User();
        u.setAvatarURL(testString);
        assertEquals(testString, u.getAvatarURL());
    }

    public void testSetWebsiteURL() throws Exception {
        String testString =  "test";
        User u = new User();
        u.setWebsiteURL(testString);
        assertEquals(testString, u.getWebsiteURL());
    }

    public void testSetUsername() throws Exception {
        String testString =  "test";
        User u = new User();
        u.setUsername(testString);
        assertEquals(testString, u.getUsername());
    }

    public void testSetLocation() throws Exception {
        String testString = "Woensel";
        User u = new User();
        u.setLocation(testString);
        assertEquals(testString, u.getLocation());
    }

    public void testGetFollowing() throws Exception {
        assertEquals(3, user1.getFollowing().size());
    }

    public void testGetFollowers() throws Exception {
        assertEquals(2, user3.getFollowers().size());
        assertEquals(0, user1.getFollowers().size());
    }

    public void testRemoveTweet() throws Exception{
        user1.removeKweet(kweet1);
        assertEquals(0, user1.getKweets().size());
    }
}