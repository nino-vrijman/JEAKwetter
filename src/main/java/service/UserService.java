package service;

import dao.JPA;
import dao.UserDao;
import model.Kweet;
import model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
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
}
