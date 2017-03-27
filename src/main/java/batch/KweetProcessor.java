package batch;

import model.Kweet;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Nino Vrijman on 22-3-2017.
 */
@Dependent
@Named("KweetProcessor")
public class KweetProcessor implements ItemProcessor {
    @Override
    public Object processItem(Object o) throws Exception {
        InputKweet processedKweet = (InputKweet) o;

        Kweet kweet = new Kweet();
        kweet.setId(processedKweet.id);
        kweet.setContent(processedKweet.content);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        kweet.setDate(dateFormat.parse(processedKweet.date));

        return processedKweet;
    }
}
