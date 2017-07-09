package service;

import dao.JPA;
import dao.UserDao;
import model.Kweet;
import model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * Created by Nino Vrijman.
 */
@Stateless
public class UserService {
    @Inject @JPA
    private UserDao userDao;

    public UserService() {

    }

    /**
     * Gets all existing users.
     * @return A list of all existing users.
     */
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    /**
     * Adds / creates a new user.
     * @param user The user object that should be created / saved in the database.
     * @return The user that has been created, or null if it couldn't be created.
     */
    public User addUser(User user) {
        return userDao.addUser(user);
    }

    /**
     * Gets a user based on a given username.
     * @param username The username to search for.
     * @return The user with the given username.
     */
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public List<User> getUsersByUsername(String username) {
        if (username.length() >= 3) {
            return userDao.getUsersByUsername(username);
        }
        return Collections.emptyList();
    }

    /**
     * Gets a range of recent kweets of a single user.
     * @param user The user of which the recent kweets should be retrieved.
     * @param offset The starting number at which the range of recent kweets should start.
     * @param limit The amount of kweets that should be returned.
     * @return A range of recent kweets in a list.
     */
    public List<Kweet> getRecentKweets(User user, int offset, int limit) {
        return userDao.getRecentKweets(user, offset, limit);
    }

    /**
     * Gets all the followers of a user.
     * @param user The user whose followers should be retrieved.
     * @return A list of users which follow the given user.
     */
    public List<User> getFollowers(User user) {
        return userDao.getFollowers(user);
    }

    /**
     * Gets all the users a user follows.
     * @param user The user of which all the users he / she follows should be retrieved.
     * @return A list of users which the given user follows.
     */
    public List<User> getFollowing(User user) {
        return userDao.getFollowing(user);
    }

    /**
     * Gets a range of timeline kweets of a single user.
     * @param user The user of which all timeline tweets should be retrieved.
     * @param offset The starting number at which the range of timeline kweets should start.
     * @param limit The amount of timeline kweets that should be returned.
     * @return A range of recent kweets in a list.
     */
    public List<Kweet> getTimelineKweets(User user, int offset, int limit) {
        return userDao.getTimelineKweets(user, offset, limit);
    }

    /**
     * Lets a user follow another user.
     * @param user The user who will follow another user.
     * @param userToFollow The user who will be followed by another user.
     * @return Whether or not the userToFollow has been successfully followed by the other user.
     */
    public boolean followUser(User user, User userToFollow) {
        return userDao.followUser(user, userToFollow);
    }

    /**
     * Lets a user unfollow another user.
     * @param user The user who will unfollow another user.
     * @param userToUnfollow The user who will be unfollowed by another user.
     * @return Whether or not the user has successfully unfollowed the userToUnfollow.
     */
    public boolean unfollowUser(User user, User userToUnfollow) {
        return userDao.unfollowUser(user, userToUnfollow);
    }
}
