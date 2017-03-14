package dao;

import model.Tweet;
import model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Nino Vrijman
 */
@Stateless @JPA
public class UserDaoImp implements UserDao {
    @PersistenceContext(unitName = "KwetterPU", type = PersistenceContextType.EXTENDED)
    EntityManager em;

    public UserDaoImp() {

    }

    public User addUser(User user) {
        try{
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            user = null;
        }
        return user;
    }

    public User getUserByUsername(String username) {
        User user;
        try{
            TypedQuery<User> query = em.createNamedQuery("user.findByUsername", User.class);
            query.setParameter("useruame", username);
            user = query.getSingleResult();
        }catch (Exception ex){
            user = null;
        }
        return user;
    }

    public List<Tweet> getRecentTweets(User user, int offset, int limit) {
        List<Tweet> tweets;
        try{
            tweets = em.find(User.class, user).getRecentTweets(offset, limit);
        }catch (Exception ex){
            tweets = null;
        }
        return tweets;
    }

    public List<User> getFollowers(User user) {
        List<User> users;
        try{
            users = em.find(User.class, user).getFollowers();
        }catch (Exception ex){
            users = null;
        }
        return users;
    }

    public List<User> getFollowing(User user) {
        List<User> users;
        try{
            users = em.find(User.class, user).getFollowing();
        }catch (Exception ex){
            users = null;
        }
        return users;
    }

    public List<Tweet> getTimelineTweets(User user, int offset, int limit) {
        List<Tweet> tweets;
        try{
            tweets = em.find(User.class, user).getTimelineTweets(offset, limit);
        }catch (Exception ex){
            tweets = null;
        }
        return tweets;
    }
}
