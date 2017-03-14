package model;

import javax.enterprise.inject.Model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nino Vrijman
 */
@Entity @Model
public class Tweet implements Comparable<Tweet> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    private Date date;

    @OneToOne
    private User createdBy;
    @OneToMany
    private List<User> likedBy;

    public Tweet() {
    }

    public Tweet(String content, User creater) {
        this.content = content;
        this.createdBy = creater;
        this.date = new Date(System.currentTimeMillis());
        this.likedBy = new ArrayList();

        creater.addTweet(this);
    }

    public boolean addLike(User user){
        if(!likedBy.contains(user)){
            likedBy.add(user);
            return true;
        }
        return false;
    }

    public int compareTo(Tweet o) {
        return getDate().compareTo(o.getDate());
    }

    public Date getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public List<User> getLikedBy() {
        return likedBy;
    }

    public User getCreatedBy() {
        return createdBy;
    }
}
