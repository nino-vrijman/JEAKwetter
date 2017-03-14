package dao;

import model.Kweet;
import model.User;

import java.util.List;

public interface KweetDao {

    Kweet getKweet(int id);

    List<Kweet> findByContent(String query);

    Kweet create(String content, User user);

    boolean remove(Kweet kweet);



}
