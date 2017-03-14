package bean;

import model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Nino Vrijman on 6-3-2017.
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