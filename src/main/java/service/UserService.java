package service;

import dao.UserDao;
import dao.UserDaoColl;
import model.Tweet;
import model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Nino Vrijman
 */
@Stateless
public class UserService {
    @Inject
    private UserDao userDao;

    public UserService() {
        userDao = new UserDaoColl();
    }

    public User addUser(User user) {
        return userDao.addUser(user);
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public List<Tweet> getRecentTweets(User user, int offset, int limit) {
        return userDao.getRecentTweets(user, offset, limit);
    }

    public List<User> getFollowers(User user) {
        return userDao.getFollowers(user);
    }

    public List<User> getFollowing(User user) {
        return userDao.getFollowing(user);
    }

    public List<Tweet> getTimelineTweets(User user, int offset, int limit) {
        return userDao.getTimelineTweets(user, offset, limit);
    }
}
