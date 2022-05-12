package jobs.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Stats {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerScheduler.class);

    @Qualifier("threadPoolTaskExecutor")
    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.HOURS)
    public void introspect() {
        LOGGER.debug("ThreadPoolTaskExecutor completed tasks : " + executor.getThreadPoolExecutor().getCompletedTaskCount());
        LOGGER.debug("ThreadPoolTaskExecutor task count : " + executor.getThreadPoolExecutor().getTaskCount());
        LOGGER.debug("ThreadPoolTaskExecutor active count : " + executor.getThreadPoolExecutor().getActiveCount());
    }
}
