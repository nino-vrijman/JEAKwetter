package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.0.v20150330-rNA", date="2017-03-14T23:14:04")
@StaticMetamodel(Kweet.class)
public class Tweet_ { 

    public static volatile SingularAttribute<Kweet, Date> date;
    public static volatile SingularAttribute<Kweet, User> createdBy;
    public static volatile ListAttribute<Kweet, User> likedBy;
    public static volatile SingularAttribute<Kweet, Long> id;
    public static volatile SingularAttribute<Kweet, String> content;

}