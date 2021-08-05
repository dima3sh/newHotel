package org.azati.cources.configuration;


import org.azati.cources.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ScheduledFuture;


@Configuration
@EnableScheduling
public class ScheduledConfiguration implements SchedulingConfigurer {

    @Autowired
    GuestService guestService;

    TaskScheduler taskScheduler;


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(1);// Set the pool of threads
        threadPoolTaskScheduler.setThreadNamePrefix("scheduler-thread");
        threadPoolTaskScheduler.initialize();
        job1(threadPoolTaskScheduler);// Assign the job1 to the scheduler
        this.taskScheduler = threadPoolTaskScheduler;// this will be used in later part of the article during refreshing the cron expression dynamically
        taskRegistrar.setTaskScheduler(threadPoolTaskScheduler);

    }

    private void job1(TaskScheduler scheduler) {
        ScheduledFuture<?> job1;
        job1 = scheduler.schedule(() -> guestService.updateInvoice(),
                triggerContext -> {
                    String cronExp = "0 0 * * * ?";// Can be pulled from a db .
                    return new CronTrigger(cronExp).nextExecutionTime(triggerContext);
                });
    }
}

