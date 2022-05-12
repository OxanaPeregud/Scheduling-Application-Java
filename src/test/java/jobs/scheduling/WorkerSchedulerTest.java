package jobs.scheduling;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringJUnitConfig(ThreadPoolConfiguration.class)
class WorkerSchedulerTest {

    @SpyBean
    WorkerScheduler workerScheduler;

    @Test
    public void givenSleepBy100ms_whenGetInvocationCount_thenIsGreaterThanZero() throws InterruptedException {
        Thread.sleep(100L);
        assertThat(workerScheduler.getInvocationCount()).isGreaterThan(0);
    }

    @Test
    public void whenWaitOneSecond_thenScheduledIsCalledAtLeastTenTimes() {
        await()
                .atMost(Duration.ofSeconds(1))
                .untilAsserted(() -> verify(workerScheduler, atLeast(10)).getInvocationCount());
    }
}
