package dao;

import model.Kweet;
import model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Nino Vrijman.
 */
@Stateless @JPA
public class KweetDaoImp implements KweetDao {
    @PersistenceContext(unitName = "KwetterPU")
    EntityManager em;

    public Kweet getKweet(int id) {
        Kweet kweet;
        try{
            kweet = em.find(Kweet.class, id);
        }catch (Exception ex){
            System.out.println("ERROR " + ex.getMessage());
            kweet = null;
        }
        return kweet;
    }

    public List<Kweet> findByContent(String query) {
        List kweets;
        try{
            kweets = em.createQuery("SELECT k FROM Kweet k where k.content = :content")
                        .setParameter("content", query).getResultList();
            return kweets;
        }catch (Exception ex){
            System.out.println("ERROR " + ex.getMessage());
            kweets = null;
        }
        return kweets;
    }

    public Kweet create(String content, User user) {
        Kweet kweet;
        try{
            kweet = new Kweet(content, user);
            em.getTransaction().begin();
            em.persist(kweet);
            em.getTransaction().commit();
        }catch (Exception ex){
            System.out.println("ERROR " + ex.getMessage());
            kweet = null;
        }
        return kweet;
    }

    public boolean remove(Kweet kweet) {
        try{
            em.remove(kweet);
        }catch (Exception ex){
            System.out.println("ERROR " + ex.getMessage());
            return false;
        }
        return true;
    }
}
