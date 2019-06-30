package com.nikpetrovic.task.beans;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class StockPullExecutor {
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public StockPullExecutor() {
        this.threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        this.threadPoolTaskExecutor.setCorePoolSize(4);
        this.threadPoolTaskExecutor.setMaxPoolSize(8);
        this.threadPoolTaskExecutor.setThreadNamePrefix("stockPullExecutor-");
        this.threadPoolTaskExecutor.initialize();
    }

    private ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
        return threadPoolTaskExecutor;
    }

    public void execute(Runnable task) {
        this.getThreadPoolTaskExecutor().execute(task);
    }
}
