package com.example.demo.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 版      权 :  jariec.com
 * 包      名 :  com.example.demo.test.ThreadPoolExecutor
 * 描      述 :
 * 创 建 时 间 : 2021/7/16 16:38
 *
 * @author :  张伟
 */
public class ThreadPoolExecutor {

    private static volatile java.util.concurrent.ThreadPoolExecutor threadPoolExecutor = null;
    public static java.util.concurrent.ThreadPoolExecutor getInstance(){
        if(null == threadPoolExecutor){
            synchronized (ThreadPoolExecutor.class){
                if(null == threadPoolExecutor){
                    int core = Runtime.getRuntime().availableProcessors() * 2;
                    int max = 9;
                    long timeout = 3 * 1000;
                    int defaultSize = 50;
                    BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(defaultSize);
                    ThreadFactory threadFactory = (runnable)->{
                        Thread thread = new Thread(runnable);
                        thread.setName("FROM_THREAD_POOL");
                        return thread;
                    };
                    threadPoolExecutor = new java.util.concurrent.ThreadPoolExecutor(core, max, timeout, TimeUnit.MILLISECONDS, blockingQueue,threadFactory);
                    threadPoolExecutor.allowCoreThreadTimeOut(true);//空闲时是否销毁核心线程
                }
            }
        }
        return threadPoolExecutor;
    }

}