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
        System.out.println("WRITE-ITEMS-HAS-BEEN-INVOKED");

        User testUser = userService.getUserByUsername("Batch Testuser");
        if (testUser == null) {
            testUser = new User();
            testUser.setUsername("Batch Testuser");
            testUser = userService.addUser(testUser);
        }

        for (Object item : items) {
            InputKweet k = (InputKweet) item;
            kweetService.create(k.content, testUser);
            System.out.println("KWEETSERVICE-CREATE-CALLED");
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
