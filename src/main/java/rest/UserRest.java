package rest;

import model.Kweet;
import model.User;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Nino Vrijman.
 */
@Stateless
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserRest {

    @Inject
    UserService userService;

    @GET
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GET
    @Path("{username}")
    public User getUserByUsername(@PathParam("username") String username) {
        return userService.getUserByUsername(username);
    }

    @POST
    @Path("{username}/{name}")
    public User newUser(@PathParam("username") String username, @PathParam("name") String name) {
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        return userService.addUser(user);
    }

    @GET
    @Path("{username}/tweets/{offset}/{limit}")
    public List<Kweet> getRecentKweets(@PathParam("username") String username, @PathParam("offset") int offset, @PathParam("limit") int limit) {
        return userService.getRecentKweets(userService.getUserByUsername(username), offset, limit);
    }

    @GET
    @Path("{username}/followers")
    public List<User> getFollowers(@PathParam("username") String username) {
        return userService.getFollowers(userService.getUserByUsername(username));
    }

    @GET
    @Path("{username}/following")
    public List<User> getFollowing(@PathParam("username") String username) {
        return userService.getFollowing(userService.getUserByUsername(username));
    }

    @GET
    @Path("{username}/timeline/{offset}/{limit}")
    public List<Kweet> getTimelineKweets(@PathParam("username") String username, @PathParam("offset") int offset, @PathParam("limit") int limit) {
        return userService.getTimelineKweets(userService.getUserByUsername(username), offset, limit);
    }
}
