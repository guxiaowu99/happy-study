package com.happy.study.callable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description: 第四种获取java多线程的方式，线程池
 * @author: zhangmengchao
 * @date: Created in 2021/2/2 上午10:14
 * @version: 1.0
 * @modified By:
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {

//        threadPoolInit();
        /**
         * 手写创建线程池-改进版
         */
        ExecutorService threadPool = new ThreadPoolExecutor(2, 5, 1L,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(3), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        try {
            for (int i = 1; i <= 55; i++) {
                threadPool.execute(() ->{
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
        /**
         * 处理不过来时请求时:
         *    默认拒绝策略AbortPolicy会抛出异常：java.util.concurrent.RejectedExecutionException
         *    CallerRunsPolicy拒绝策略： 处理不过来回退给调用者  main	 办理业务
         *    DiscardPolicy拒绝策略： 处理不过来的时候直接丢弃任务
         *    DiscardOldestPolicy拒绝策略：抛弃等待最久的任务。把当前任务加入队列
         */
    }

    /**
     * jdk中线程池的创建方式，但是生产环境不建议使用
     *
     * @throws InterruptedException
     */
    private static void threadPoolInit() throws InterruptedException {

        /**
         * 固定线程数的线程池
         */
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
        // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool1.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool1.shutdown();
        }
        TimeUnit.SECONDS.sleep(2);
        /**
         * 结果：
         * pool-1-thread-1	 办理业务
         * pool-1-thread-3	 办理业务
         * pool-1-thread-2	 办理业务
         * pool-1-thread-4	 办理业务
         * pool-1-thread-5	 办理业务
         * pool-1-thread-5	 办理业务
         * pool-1-thread-5	 办理业务
         * pool-1-thread-5	 办理业务
         * pool-1-thread-1	 办理业务
         * pool-1-thread-3	 办理业务
         */
        System.out.println("======================单个线程的线程池=======================");
        /**
         * 单个线程的线程池
         */
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();
        // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool2.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool2.shutdown();
        }
        TimeUnit.SECONDS.sleep(2);
        /**
         * 结果
         * pool-2-thread-1	 办理业务
         * pool-2-thread-1	 办理业务
         * pool-2-thread-1	 办理业务
         * pool-2-thread-1	 办理业务
         * pool-2-thread-1	 办理业务
         * pool-2-thread-1	 办理业务
         * pool-2-thread-1	 办理业务
         * pool-2-thread-1	 办理业务
         * pool-2-thread-1	 办理业务
         * pool-2-thread-1	 办理业务
         */
        System.out.println("======================不固定线程数的线程池=======================");
        /**
         * 不固定线程数的线程池
         *
         */
        ExecutorService threadPool3 = Executors.newCachedThreadPool();
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool3.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool3.shutdown();
        }
        /**
         * 结果
         * pool-3-thread-1	 办理业务
         * pool-3-thread-2	 办理业务
         * pool-3-thread-3	 办理业务
         * pool-3-thread-4	 办理业务
         * pool-3-thread-1	 办理业务
         * pool-3-thread-5	 办理业务
         * pool-3-thread-6	 办理业务
         * pool-3-thread-5	 办理业务
         * pool-3-thread-2	 办理业务
         * pool-3-thread-3	 办理业务
         */
    }
}
