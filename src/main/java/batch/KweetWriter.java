package batch;

import model.User;
import service.KweetService;
import service.UserService;

import javax.batch.api.chunk.ItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Dependent
@Named("KweetWriter")
public class KweetWriter implements ItemWriter {
    
    @Inject
    private KweetService kweetService;
    @Inject
    private UserService userService;

    @Override
    public void close() throws Exception {
    }

    @Override
    public void writeItems(List<Object> items) throws Exception {
        System.out.println("Writing items!");

        User testUser = userService.getUserByUsername("nino");
        if (testUser == null) {
            testUser = new User();
            testUser.setUsername("nino");
            testUser.setPassword("nino");
            testUser = userService.addUser(testUser);
        }

        for (Object item : items) {
            InputKweet k = (InputKweet) item;
            kweetService.create(k.content, testUser);
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
       return null;
    }

    @Override
    public void open(Serializable checkpoint) throws Exception {
    }
   
}
