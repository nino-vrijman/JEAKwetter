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

    public Kweet getTweet(int id){
        return kweetDao.getKweet(id);
    }

    public KweetService(){
        //kweetDao = new KweetDao();
    }

    public List<Kweet> findByContent(String query) {
        return kweetDao.findByContent(query);
    }

    public Kweet create(String content, User user) {
        return kweetDao.create(content, user);
    }

    public boolean remove(Kweet kweet) {
        return kweetDao.remove(kweet);
    }
}
