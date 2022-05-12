package jobs.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class WorkerScheduler {

    @Qualifier("threadPoolTaskExecutor")
    @Autowired
    private ThreadPoolTaskExecutor executor;

    private final Queue<Runnable> jobsScheduledForPeriodicExecution = new ArrayDeque<>();
    private final Queue<Runnable> jobsExecutedImmediately = new ArrayDeque<>();
    private final AtomicInteger count = new AtomicInteger(0);

    public WorkerScheduler() {
        this.jobsScheduledForPeriodicExecution.add(new JobTypeOne());
        this.jobsScheduledForPeriodicExecution.add(new JobTypeTwo());
        this.jobsExecutedImmediately.add(new JobTypeThree());
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.HOURS)
    public void startWorkerThreadOneHour() {
        startJobsScheduledForPeriodicExecution();
    }

    @Scheduled(fixedDelay = 2, timeUnit = TimeUnit.HOURS)
    public void startWorkerThreadTwoHours() {
        startJobsScheduledForPeriodicExecution();
    }

    @Scheduled(fixedDelay = 6, timeUnit = TimeUnit.HOURS)
    public void startWorkerThreadSixHours() {
        startJobsScheduledForPeriodicExecution();
    }

    @Scheduled(fixedDelay = 12, timeUnit = TimeUnit.HOURS)
    public void startWorkerThreadTwelveHours() {
        startJobsScheduledForPeriodicExecution();
    }

    @Scheduled(fixedDelay = 5)
    public void startWorkerThreadImmediately() {
        this.getInvocationCount();
        this.count.incrementAndGet();
        startJobsExecutedImmediately();
    }

    private void startJobsScheduledForPeriodicExecution() {
        Runnable runnable = jobsScheduledForPeriodicExecution.poll();
        if (runnable != null) {
            executor.execute(runnable);
        }
        jobsScheduledForPeriodicExecution.add(runnable);
    }

    private void startJobsExecutedImmediately() {
        Runnable runnable = jobsExecutedImmediately.poll();
        if (runnable != null) {
            executor.execute(runnable);
        }
    }

    public int getInvocationCount() {
        return this.count.get();
    }
}
