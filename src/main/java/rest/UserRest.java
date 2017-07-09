package rest;

import model.Kweet;
import model.User;
import model.UserDTO;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
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

    @Context
    UriInfo uriInfo;

    @GET
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GET
    @Path("{username}")
    public Response getUserByUsername(@PathParam("username") String username) {
        UserDTO foundUser = new UserDTO(userService.getUserByUsername(username));

        Link self = Link.fromUri(uriInfo.getAbsolutePath()).rel("self").type("GET").build();
        Link following = Link.fromUri(uriInfo.getAbsolutePath() + "/following").rel("following").type("GET").build();
        Link followers = Link.fromUri(uriInfo.getAbsolutePath() + "/followers").rel("followers").type("GET").build();

        return Response.ok(foundUser).links(self, following, followers).build();
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

    @POST
    @Path("{username}/follow/{followingUsername})")
    public Response followUser(@PathParam("username") String username, @PathParam("followingUsername") String followingUsername) {
        if (userService.followUser(userService.getUserByUsername(username), userService.getUserByUsername(followingUsername))) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("{username}/unfollow/{unfollowingUsername})")
    public Response unfollowUser(@PathParam("username") String username, @PathParam("unfollowingUsername") String unfollowingUsername) {
        if (userService.unfollowUser(userService.getUserByUsername(username), userService.getUserByUsername(unfollowingUsername))) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
