package dao;

import model.Kweet;
import model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

/**
 * Created by Nino Vrijman.
 */
@Stateless @JPA
public class UserDaoImp implements UserDao {
    @PersistenceContext(unitName = "KwetterPU")
    EntityManager em;

    public UserDaoImp() {

    }

    public User addUser(User user) {
        try{
            em.persist(user);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            user = null;
        }
        return user;
    }

    public List<User> getUsers() {
        List<User> results = em
                .createQuery("Select u from User u", User.class)
                .getResultList();
        return results;
    }

    public User getUserByUsername(String username) {
        User user;
        try{
            TypedQuery<User> query = em.createNamedQuery("user.findByName", User.class);
            query.setParameter("username", username);
            user = query.getSingleResult();
        }catch (Exception ex){
            ex.printStackTrace();
            user = null;
        }
        return user;
    }

    public List<User> getUsersByUsername(String username) {
        List<User> users;

        try {
            TypedQuery<User> query = em.createNamedQuery("user.findUsersByName", User.class);
            query.setParameter("username", "%" + username + "%");
            users = query.getResultList();
        } catch (Exception ex) {
            users = Collections.emptyList();
        }

        return users;
    }

    public List<Kweet> getRecentKweets(User user, int offset, int limit) {
        List<Kweet> kweets;
        try{
            kweets = em.find(User.class, user).getRecentKweets(offset, limit);
            Collections.sort(kweets);
        }catch (Exception ex){
            kweets = null;
        }
        return kweets;
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

    public List<Kweet> getTimelineKweets(User user, int offset, int limit) {
        List<Kweet> kweets;
        try{
            kweets = em.find(User.class, user.getId()).getTimelineKweets(offset, limit);
        }catch (Exception ex){
            kweets = null;
        }
        return kweets;
    }
}
