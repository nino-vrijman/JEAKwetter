package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Tweet;
import model.User;

@Generated(value="EclipseLink-2.6.0.v20150330-rNA", date="2017-03-14T23:10:40")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile ListAttribute<User, User> followers;
    public static volatile SingularAttribute<User, String> avatarURL;
    public static volatile SingularAttribute<User, String> websiteURL;
    public static volatile ListAttribute<User, User> following;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, String> bio;
    public static volatile SingularAttribute<User, String> location;
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> type;
    public static volatile ListAttribute<User, Tweet> tweets;
    public static volatile SingularAttribute<User, String> username;

}