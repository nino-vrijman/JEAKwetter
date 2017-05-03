package websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Kweet;
import model.KweetDTO;
import service.KweetService;
import service.UserService;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Nino Vrijman on 3-5-2017.
 */
@ServerEndpoint(value = "/kweetEndpoint")
public class SocketEndpoint {

    @Inject
    private KweetService kweetService;
    @Inject
    private UserService userService;

    private static final Logger LOG = Logger.getLogger(SocketEndpoint.class.getName());
    private static final Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session peer) {
        LOG.info("Connection opened ...");
        peers.add(peer);
    }

    @OnClose
    public void onClose(Session peer) {
        LOG.info("Connection closed ...");
        peers.remove(peer);
    }

    @OnError
    public void onError(Throwable t) {
        LOG.log(Level.INFO, "Error ...{0}", t.getMessage());
    }

    @OnMessage
    public void onMessage(final Session client, final String message) {
        if (message != null) {
            Kweet newKweet = kweetService.create(message, userService.getUserByUsername("nino"));
            KweetDTO jsonKweet = new KweetDTO(newKweet);
            sendMessage(client, jsonKweet);
        }
    }

    private void sendMessage(Session peer, Object send) {
        try {
            if (peer.isOpen()) {
                ObjectMapper mapper = new ObjectMapper();
                peer.getBasicRemote().sendObject(mapper.writeValueAsString(send));
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    private void sentToAll(Object answer) {
        for (Iterator<Session> it = peers.iterator(); it.hasNext();) {
            sendMessage(it.next(), answer);
        }
    }
}
