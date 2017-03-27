package batch;

import model.Kweet;

import javax.batch.api.chunk.ItemReader;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by Nino Vrijman on 22-3-2017.
 */
@Dependent
@Named("KweetReader")
public class KweetReader implements ItemReader {

    @Inject
    private JobContext jobContext;

    private String fileName;

    private JsonParser parser;

    private Checkpoint checkpoint;

    private boolean start;

    @Override
    public void open(Serializable serializable) throws Exception {
        if (checkpoint == null) {
            this.checkpoint = new Checkpoint();
        } else {
            this.checkpoint = (Checkpoint) checkpoint;
        }

        fileName = jobContext.getProperties().getProperty("input_file");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("../../WEB-INF/classes/META-INF/batch-jobs/" + fileName);
        parser = Json.createParser(inputStream);

        start = false;
        for (long i = 0; i < this.checkpoint.getCount(); ++i) {
            JsonParser.Event event = parser.next();
            if (event == JsonParser.Event.START_ARRAY) {
                start = true;
            }
        }
    }

    @Override
    public void close() throws Exception {
        parser.close();
    }

    @Override
    public Object readItem() throws Exception {
        boolean itemFound = false;
        InputKweet item = new InputKweet();

        System.out.println("Reading JSON-item");

        while (itemFound == false
                && parser.hasNext()) {
            JsonParser.Event event = parser.next();
            checkpoint.eventHappened();

            switch (event) {
                case START_ARRAY:
                    start = true;
                    break;
                case VALUE_STRING:
                    if (start == true) {
                        if (Integer.valueOf(item.id) == null) {
                            item.id = parser.getInt();
                        } else if (item.content == null) {
                            item.content = parser.getString();
                        } else if (item.date == null) {
                            item.date = parser.getString();
                            itemFound = true;
                        }
                    }
                    break;
                case END_ARRAY:
                    item = null;
                    break;
            }
        }
        return item;
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return new Checkpoint();
    }
}
