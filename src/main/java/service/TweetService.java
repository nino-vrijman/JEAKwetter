package service;

import dao.TweetDaoColl;
import model.Tweet;
import model.User;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nino Vrijman
 */
public class TweetService {
    @Inject
    private TweetDaoColl tweetDao;

    public TweetService(){
        tweetDao = new TweetDaoColl();
    }

    public List<Tweet> findByContent(String query) {
        return tweetDao.findByContent(query);
    }

    public Tweet create(String content, User user) {
        return tweetDao.create(content, user);
    }

    public boolean remove(Tweet tweet) {
        return tweetDao.remove(tweet);
    }
}
