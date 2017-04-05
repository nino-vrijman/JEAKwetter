package service;

import javax.annotation.PostConstruct;
import javax.batch.operations.JobOperator;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Created by Nino Vrijman on 27-3-2017.
 */
@Singleton
@Startup
public class StartUp {

    private long execID;
    private JobOperator jobOperator;
    private static final long serialVersionUID = 3712686178567411830L;

    public StartUp() {
    }

    @PostConstruct
    private void initData(){
//        jobOperator = BatchRuntime.getJobOperator();
//        execID = jobOperator.start("kweetimport", null);
    }
}

