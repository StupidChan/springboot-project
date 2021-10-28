package com.chen.schedule;


import java.util.concurrent.ScheduledFuture;

//ScheduledFuture是ScheduledExecutorService定时任务线程池的执行结果

public final class ScheduledTask {


    public volatile ScheduledFuture<?> future;

    /**
     * 取消定时任务
     */
    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }
}
