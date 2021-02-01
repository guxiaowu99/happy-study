package com.happy.study.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description: 由数组结构组成的有界阻塞队列
 * @author: zhangmengchao
 * @date: Created in 2021/2/1 上午10:38
 * @version: 1.0
 * @modified By:
 */
public class ArrayBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
//        groupTest1(blockingQueue);
//        groupTest2(blockingQueue);
//        groupTest3(blockingQueue);
        groupTest4(blockingQueue);
    }

    /**
     * 超时控制组
     * @param blockingQueue
     */
    private static void groupTest4(BlockingQueue<String> blockingQueue) throws InterruptedException {
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        /**
         * 测试结果：当队列已经满的时候，再加入新的元素，等待2s，超时返回false
         * true
         * true
         * true
         * false
         */
    }

    /**
     * 一直阻塞组
     * @param blockingQueue
     * @throws InterruptedException
     */
    private static void groupTest3(BlockingQueue<String> blockingQueue) throws InterruptedException {
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println("=================队列已满==================");
        blockingQueue.put("xxx");
        /**
         * 结果是当队列已经满的话会一直等待，程序不会终止。
         */
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
//        blockingQueue.take();
        /**
         * 当队列里面的元素没有可取的时候，也会一直进行等待。
         */
    }


    /**
     * 返回布尔类型组
     * @param blockingQueue
     */
    private static void groupTest2(BlockingQueue<String> blockingQueue) {
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("x"));
        /**
         * 队列满的时候插入不再直接抛出异常，而是返回boolean值
         * true
         * true
         * true
         * false
         */
        // 检查
        System.out.println(blockingQueue.peek());
        // 获取
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        /**
         * 获取不到时候返回null
         * a
         * b
         * c
         * null
         */
    }

    /**
     * 抛出异常组
     * @param blockingQueue
     */
    private static void groupTest1(BlockingQueue<String> blockingQueue) {
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
//        System.out.println(blockingQueue.add("xx"));
        /** 如果队列已满时添加会抛出异常
         * true
         * true
         * true
         * Exception in thread "main" java.lang.IllegalStateException: Queue full
         */
        // 队列检查 打印队列头部第一个元素a
        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
        /**
         * 移除队列无元素时抛出异常
         * true
         * true
         * true
         * a
         * b
         * c
         * Exception in thread "main" java.util.NoSuchElementException
         */
    }
}
