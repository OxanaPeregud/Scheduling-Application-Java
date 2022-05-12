package jobs.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class JobTypeThree implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobTypeThree.class);

    @Override
    public void run() {
        LOGGER.debug("JobTypeThree started. Current thread name {}", Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.debug("JobTypeThree finished. Current thread name {}", Thread.currentThread().getName());
    }
}
