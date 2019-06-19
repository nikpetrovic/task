package com.nikpetrovic.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class DataScheduler {
    @Scheduled(fixedDelay = 5000)
    public void fetchData() {
        System.out.println("refreshing data");
    }
}
