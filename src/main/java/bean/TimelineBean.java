package bean;

import model.Kweet;
import model.User;
import service.KweetService;
import service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Nino Vrijman on 29-3-2017.
 */
@Named(value = "timelineBean")
@SessionScoped
public class TimelineBean implements Serializable {

    @Inject
    private UserService userService;

    @Inject
    private KweetService kweetService;

    private String username;
    private User user;
    private String newKweetContent;
    private String searchUsername;
    private List<User> foundUsers;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNewKweetContent() {
        return newKweetContent;
    }

    public void setNewKweetContent(String newKweetContent) {
        this.newKweetContent = newKweetContent;
    }

    public String getSearchUsername() {
        return searchUsername;
    }

    public void setSearchUsername(String searchUsername) {
        this.searchUsername = searchUsername;
    }

    public List<User> getFoundUsers() {
        return foundUsers;
    }

    public void setFoundUsers(List<User> foundUsers) {
        this.foundUsers = foundUsers;
    }

    public void loadCurrentUser() {
        String currentUsername = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        this.setUsername(currentUsername);
        User currentUser = userService.getUserByUsername(this.getUsername());
        this.setUser(currentUser);
    }

    public List<Kweet> getTimelineKweets() {
        if (this.user == null) {
            return Collections.emptyList();
        }
        return this.user.getTimelineKweets(0, 10);
    }

    public Kweet createKweet() {
        return kweetService.create(this.getNewKweetContent(), this.getUser());
    }

    public void findUsersByUsername() {
        if (this.searchUsername != null && this.searchUsername != "") {
            this.foundUsers = this.userService.getUsersByUsername(this.searchUsername);
        } else {
            this.foundUsers = new ArrayList<User>();
        }
    }

    public void logout(){
        this.searchUsername = "";
        this.foundUsers = new ArrayList<User>();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "login.xhtml");
    }
}
