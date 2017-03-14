package dao;

import model.Tweet;
import model.User;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nino Vrijman
 */
@Stateless @Default
public class UserDaoColl implements UserDao {
    private List<User> users;

    public UserDaoColl() {
        users = new ArrayList<User>();
    }

    public User addUser(User user) {
        if (!this.users.contains(user)) {
            this.users.add(user);
            return user;
        }
        return null;
    }

    public User getUserByUsername(String username) {
        for (User user : this.users) {
            if (user.getUsername() != null && user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public List<Tweet> getRecentTweets(User user, int offset, int limit) {
        return user.getRecentTweets(offset, limit);
    }

    public List<User> getFollowers(User user) {
        return user.getFollowers();
    }

    public List<User> getFollowing(User user) {
        return user.getFollowing();
    }

    public List<Tweet> getTimelineTweets(User user, int offset, int limit) {
        return user.getTimelineTweets(offset, limit);
    }
}
