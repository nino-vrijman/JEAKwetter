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
public class KweetDaoColl implements KweetDao {
    private List<Kweet> kweets;

    public KweetDaoColl() {
        this.kweets = new ArrayList<Kweet>();
    }

    public Kweet getKweet(int id) {
        for (Kweet kweet : this.kweets) {
            if (kweet.getId() == id) {
                return kweet;
            }
        }
        return null;
    }

    public List<Kweet> findByContent(String query) {
        List<Kweet> foundKweets = new ArrayList<Kweet>();
        for (Kweet kweet : this.kweets) {
            if (kweet.getContent().toLowerCase().contains(query.toLowerCase())) {
                foundKweets.add(kweet);
            }
        }
        return foundKweets;
    }

    public Kweet create(String content, User user) {
        Kweet newKweet = new Kweet(content, user);
        if (!this.kweets.contains(newKweet)) {
            this.kweets.add(newKweet);
            return newKweet;
        }
        return null;
    }

    public boolean remove(Kweet kweet) {
        if (this.kweets.contains(kweet)) {
            this.kweets.remove(kweet);
            kweet.getCreatedBy().removeKweet(kweet);
            return true;
        }
        return false;
    }
}
