package model;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity @Model
@NamedQueries({
        @NamedQuery(name="user.findByName",
                query="SELECT u FROM User u WHERE u.username LIKE :username"),
        @NamedQuery(name="user.getRecentTweets",
                query="SELECT u FROM User u WHERE u.username LIKE :username")
})
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String type;
    private String bio;
    private String location;
    private String avatarURL;
    private String websiteURL;
    private String username;

    @ManyToMany
    private List<User> following;
    @ManyToMany
    private List<User> followers;
    @ManyToMany
    private List<Tweet> tweets;

    public User() {
        this.avatarURL = "/default/placeholder.png";
        this.tweets = new ArrayList<Tweet>();
        this.following = new ArrayList<User>();
        this.followers = new ArrayList<User>();
    }

    public void addTweet(Tweet tweet){
        tweets.add(tweet);
    }

    public void follow(User user){
        if(!this.following.contains(user)){
            user.addFollower(this);
            this.following.add(user);
        }
    }

    private void addFollower(User user){
        if(!this.followers.contains(user))
            this.followers.add(user);
    }

    public List<Tweet> getRecentTweets(int offset, int limit){
        ArrayList<Tweet> result = new ArrayList<Tweet>(getTweets());

        Collections.sort(result);
        if (result.size() >= offset + limit){
            return result.subList(offset, offset + limit);
        } else {
            return result.subList(offset, result.size());
        }
    }

    public void likeTweet(Tweet tweet){
        tweet.addLike(this);
    }

    public List<Tweet> getTimelineTweets(int offset, int limit){
        ArrayList<Tweet> result = new ArrayList<Tweet>(getTweets());
        for (User user: following) {
            result.addAll(user.getTweets());
        }

        Collections.sort(result);
        if (result.size() >= offset + limit){
            return result.subList(offset, offset + limit);
        } else {
            return result.subList(offset, result.size());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public boolean setBio(String bio) {
        this.bio = bio;
        return true;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public boolean setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
        return true;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public boolean setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
        return true;
    }

    public String getUsername() {
        return username;
    }

    public boolean setUsername(String username) {
        this.username = username;
        return true;
    }

    public boolean setLocation(String location) {
        this.location = location;
        return true;
    }

    public List<User> getFollowing() {
        return following;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public String getLocation() {
        return location;
    }

    public boolean removeTweet(Tweet tweet){
        if (this.tweets.contains(tweet))
            return tweets.remove(tweet);
        return false;
    }
}
