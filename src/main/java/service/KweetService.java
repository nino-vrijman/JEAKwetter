package service;

import dao.JPA;
import dao.KweetDao;
import model.Kweet;
import model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Nino Vrijman.
 */
@Stateless
public class KweetService {
    @Inject @JPA
    private KweetDao kweetDao;

    public KweetService(){
        //kweetDao = new KweetDao();
    }

    /**
     * Gets a kweet with a certain ID.
     * @param id The ID of the kweet.
     * @return The kweet with the given id.
     */
    public Kweet getTweet(int id){
        return kweetDao.getKweet(id);
    }

    /**
     * Finds kweets containing a certain string.
     * @param query The query / text to search for.
     * @return A list of Kweets containing the given string.
     */
    public List<Kweet> findByContent(String query) {
        return kweetDao.findByContent(query);
    }

    /**
     * Creates a new kweet.
     * @param content The content of the kweet.
     * @param user The user who created the kweet.
     * @return The newly created kweet.
     */
    public Kweet create(String content, User user) {
        return kweetDao.create(content, user);
    }

    /**
     * Removes / deletes the given Kweet.
     * @param kweet The kweet that should be removed.
     * @return Whether the kweet has been removed successfully.
     */
    public boolean remove(Kweet kweet) {
        return kweetDao.remove(kweet);
    }
}
