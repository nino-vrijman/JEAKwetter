package controller;

import dao.JPA;
import dao.TweetDao;
import model.Tweet;
import model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

/**
 * Created by Nino Vrijman
 */
@Stateless @JPA
public class TweetDaoImp implements TweetDao {
    @PersistenceContext(unitName = "KwetterPU", type = PersistenceContextType.EXTENDED)
    EntityManager em;

    public List<Tweet> findByContent(String query) {
        return null;
    }

    public Tweet create(String content, User user) {
        Tweet tweet = null;
        try{
            em.getTransaction().begin();
            tweet = new Tweet(content, user);
            em.persist(tweet);
            em.getTransaction().commit();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            tweet = null;
        }
        return tweet;
    }

    public boolean remove(Tweet tweet) {
        try{
            em.getTransaction().begin();
            em.remove(tweet);
            em.getTransaction().commit();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
}
