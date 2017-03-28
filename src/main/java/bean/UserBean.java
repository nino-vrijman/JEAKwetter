package bean;

import model.Kweet;
import model.User;
import service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Nino Vrijman.
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean implements Serializable {

    @Inject
    private UserService userService;

    private User user;
    private String username;

    public void getCurrentUser() {
        username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        user = userService.getUserByUsername(username);
    }

    public User getUser() {
        return user;
    }

    public String getUsername() {
        String username = user == null ? "NO USERNAME AVAILABLE" : user.getUsername();
        return user.getUsername();
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

    public Kweet getKweet() {
        return this.user.getKweets().get(0);
    }
}