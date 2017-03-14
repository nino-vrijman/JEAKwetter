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

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public User addUser(User user) {
        return userDao.addUser(user);
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public List<Kweet> getRecentKweets(User user, int offset, int limit) {
        return userDao.getRecentKweets(user, offset, limit);
    }

    public List<User> getFollowers(User user) {
        return userDao.getFollowers(user);
    }

    public List<User> getFollowing(User user) {
        return userDao.getFollowing(user);
    }

    public List<Kweet> getTimelineKweets(User user, int offset, int limit) {
        return userDao.getTimelineKweets(user, offset, limit);
    }
}
