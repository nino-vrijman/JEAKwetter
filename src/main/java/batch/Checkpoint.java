package batch;

import java.io.Serializable;

public final class Checkpoint implements Serializable {

    private int eventCount;

    public Checkpoint() {
        eventCount = 0;
    }

    public final void eventHappened() {
        ++eventCount;
    }

    public final int getCount() {
        return eventCount;
    }
}
