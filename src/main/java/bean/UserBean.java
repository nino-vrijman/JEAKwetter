package bean;

import model.Kweet;
import model.User;
import service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Nino Vrijman.
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    @Inject
    private UserService userService;

    private User user;
    private String username;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        String username = user == null ? "NO USERNAME AVAILABLE" : user.getUsername();
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void loadCurrentUser() {
        this.setUsername(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        this.setUser(user = userService.getUserByUsername(username));
    }

    public String getAvatarURL() {
        String avatarURL = user == null ? "NO AVATAR URL AVAILABLE" : user.getAvatarURL();
        return avatarURL;
    }

    public String getBio() {
        String userBio = user == null ? "Error getting biography." : user.getBio();
        if (userBio == null || userBio == "") {
            userBio = "-";
        }
        return userBio;
    }

    public List<Kweet> getUserKweets() {
        return this.user.getKweets();
    }

    public int getFollowersAmount() {
        if (this.user == null) {
            return -1;
        }
        return this.user.getFollowers().size();
    }

    public int getFollowingAmount() {
        if (this.user == null) {
            return -1;
        }
        return this.user.getFollowing().size();
    }
}