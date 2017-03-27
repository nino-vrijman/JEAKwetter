package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Kweet;
import model.User;
import model.UserGroup;

@Generated(value="EclipseLink-2.6.0.v20150330-rNA", date="2017-03-27T19:54:14")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> avatarURL;
    public static volatile SingularAttribute<User, String> bio;
    public static volatile ListAttribute<User, UserGroup> groups;
    public static volatile SingularAttribute<User, String> type;
    public static volatile SingularAttribute<User, String> password;
    public static volatile ListAttribute<User, User> followers;
    public static volatile SingularAttribute<User, String> websiteURL;
    public static volatile ListAttribute<User, User> following;
    public static volatile SingularAttribute<User, String> name;
    public static volatile ListAttribute<User, Kweet> kweets;
    public static volatile SingularAttribute<User, String> location;
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> username;

}