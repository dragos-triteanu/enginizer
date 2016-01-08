package com.startup.enginizer.repository.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Dragos on 11/10/2015.
 */
@Component
@PropertySource("classpath:conf/application.properties")
public class ExampleCronJob {
    private static final Logger LOG = LoggerFactory.getLogger(ExampleCronJob.class);

    @Scheduled(cron = "${client.removal.task.cron}")
    public void exampleExecution(){
        LOG.info("Started job");
    }
}
