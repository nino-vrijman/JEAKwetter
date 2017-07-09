package rest;

import jms.JMSSender;
import model.Kweet;
import service.KweetService;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
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
    @Context
    UriInfo uriInfo;

    @GET
    @Path("{content}")
    public List<Kweet> findTweetByContent(@PathParam("content") String query) {
        return kweetService.findByContent(query);
    }

    @POST
    @Path("{content}/{username}")
    public Response create(@PathParam("content") String content, @PathParam("username") String username) {
        Kweet createdKweet = kweetService.create(content, userService.getUserByUsername(username));
        return Response.ok(createdKweet).build();
    }

    @DELETE
    @Path("{id}")
    public boolean remove(@PathParam("id") int id){
        return kweetService.remove(kweetService.getTweet(id));
    }

    @POST
    @Path("jms/{content}/{username}")
    public Kweet createJMS(@PathParam("content") String content, @PathParam("username") String username) {
        JMSSender jmsSender = new JMSSender();
        jmsSender.sendMessage(content + ";" + username);
        return null;
    }

}
