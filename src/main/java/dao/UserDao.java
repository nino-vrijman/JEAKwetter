package dao;

import model.Tweet;
import model.User;

import java.util.List;

/**
 * Created by Nino Vrijman
 */
public interface UserDao {

    User addUser(User user);

    User getUserByUsername(String username);

    List<Tweet> getRecentTweets(User user, int offset, int limit);

    List<User> getFollowers(User user);

    List<User> getFollowing(User user);

    List<Tweet> getTimelineTweets(User user, int offset, int limit);
}
