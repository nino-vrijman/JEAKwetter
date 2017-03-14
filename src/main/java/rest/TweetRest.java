package rest;

import model.Kweet;
import service.KweetService;
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
@Path("tweets")
@Produces(MediaType.APPLICATION_JSON)
public class TweetRest {

    @Inject
    KweetService kweetService;

    @Inject
    UserService userService;

    @GET
    @Path("{content}")
    public List<Kweet> findTweetByContent(@PathParam("content") String query) {
        return kweetService.findByContent(query);
    }

    @POST
    @Path("{content}/{username}")
    public Kweet create(@PathParam("content") String content, @PathParam("username") String username) {
        Kweet kweet = kweetService.create(content, userService.getUserByUsername(username));
        System.out.println(kweet.toString());
        return kweet;
    }

    @DELETE
    @Path("{id}")
    public boolean remove(@PathParam("id") int id){
        return kweetService.remove(kweetService.getTweet(id));
    }

}
