package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Nino Vrijman on 20-3-2017.
 */
@Entity
@Table(name = "UserGroup")
public class UserGroup implements Serializable
{
    @Id
    private String groupName;

    @ManyToMany
    @JoinTable(name="USER_GROUP",
            joinColumns = @JoinColumn(name = "groupName",
                    referencedColumnName = "groupName"),
            inverseJoinColumns = @JoinColumn(name = "userName",
                    referencedColumnName = "userName"))
    private List<User> users;

    public UserGroup() {

    }
}

