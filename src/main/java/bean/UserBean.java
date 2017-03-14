package bean;

import model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Nino Vrijman.
 */
@Named(value = "UserBean")
@RequestScoped
public class UserBean implements Serializable {
    @Inject
    private User user;

    public User getUser() {
        return user;
    }
}