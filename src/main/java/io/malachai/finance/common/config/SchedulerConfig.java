package io.malachai.finance.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.Clock;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class SchedulerConfig {

  @Bean
  Clock globalClock() {
    return Clock.systemUTC();
  }

  @Bean
  ThreadPoolTaskScheduler threadPoolScheduler() {
    ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setPoolSize(1);
    taskScheduler.setThreadNamePrefix("api-call-task");
    taskScheduler.setClock(globalClock());
    taskScheduler.initialize();
    return taskScheduler;
  }

}
