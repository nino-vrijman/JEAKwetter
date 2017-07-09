package dao;

import model.Kweet;
import model.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nino Vrijman.
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

    public List<User> getUsers() {
        return this.users;
    }

    public User getUserByUsername(String username) {
        for (User user : this.users) {
            if (user.getUsername() != null && user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getUsersByUsername(String username) {
        List<User> users = new ArrayList<User>();

        for (User user : this.users) {
            if (user.getUsername() != null && user.getUsername().contains(username)) {
                if (!users.contains(user)) {
                    users.add(user);
                }
            }
        }

        return users;
    }

    public List<Kweet> getRecentKweets(User user, int offset, int limit) {
        return user.getRecentKweets(offset, limit);
    }

    public List<User> getFollowers(User user) {
        return user.getFollowers();
    }

    public List<User> getFollowing(User user) {
        return user.getFollowing();
    }

    public List<Kweet> getTimelineKweets(User user, int offset, int limit) {
        return user.getTimelineKweets(offset, limit);
    }

    @Override
    public boolean followUser(User user, User userToFollow) {
        return false;
    }

    @Override
    public boolean unfollowUser(User user, User userToUnfollow) {
        return false;
    }
}
