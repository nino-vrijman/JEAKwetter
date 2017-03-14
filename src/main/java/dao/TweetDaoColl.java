package dao;

import model.Tweet;
import model.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nino Vrijman
 */
@Stateless @Default
public class TweetDaoColl implements TweetDao {
    private List<Tweet> tweets;

    public TweetDaoColl() {
        this.tweets = new ArrayList<Tweet>();
    }

    public List<Tweet> findByContent(String query) {
        List<Tweet> foundTweets = new ArrayList<Tweet>();
        for (Tweet tweet : this.tweets) {
            if (tweet.getContent().toLowerCase().contains(query.toLowerCase())) {
                foundTweets.add(tweet);
            }
        }
        return foundTweets;
    }

    public Tweet create(String content, User user) {
        Tweet newTweet = new Tweet(content, user);
        if (!this.tweets.contains(newTweet)) {
            this.tweets.add(newTweet);
            return newTweet;
        }
        return null;
    }

    public boolean remove(Tweet tweet) {
        if (this.tweets.contains(tweet)) {
            this.tweets.remove(tweet);
            tweet.getCreatedBy().removeTweet(tweet);
            return true;
        }
        return false;
    }
}
