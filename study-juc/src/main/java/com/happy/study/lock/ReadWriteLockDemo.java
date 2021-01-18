package com.happy.study.lock;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {

    private volatile Map<String, Object> map = new HashMap<>();

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入： " + key);
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成： " + key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取");
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成： " + value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }

}

/**
 * @description: 读写锁  写操作：原子 + 独占
 * @author: zhangmengchao
 * @date: Created in 2021/1/16 上午11:47
 * @version: 1.0
 * @modified By:
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt + "", tempInt + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt + "");
            }, String.valueOf(i)).start();
        }
    }
    /**
     * 没有加入ReentrantReadWriteLock之前结果
     * 1	 正在写入： 1
     * 2	 正在写入： 2
     * 3	 正在写入： 3
     * 4	 正在写入： 4
     * 2	 写入完成： 2
     * 1	 写入完成： 1
     * 3	 写入完成： 3
     * 4	 写入完成： 4
     * 5	 正在写入： 5
     * 5	 写入完成： 5
     * 1	 正在读取
     * 2	 正在读取
     * 3	 正在读取
     * 1	 读取完成： 1
     * 2	 读取完成： 2
     * 3	 读取完成： 3
     * 4	 正在读取
     * 4	 读取完成： 4
     * 5	 正在读取
     * 5	 读取完成： 5
     *
     * 可以看出目前的写操作不是原子的
     */

    /**
     * 加入ReentrantReadWriteLock之后 可以看出写操作变为了原子性 并且读操作可以一起读
     * 1	 正在写入： 1
     * 1	 写入完成： 1
     * 2	 正在写入： 2
     * 2	 写入完成： 2
     * 3	 正在写入： 3
     * 3	 写入完成： 3
     * 4	 正在写入： 4
     * 4	 写入完成： 4
     * 5	 正在写入： 5
     * 5	 写入完成： 5
     * 1	 正在读取
     * 1	 读取完成： 1
     * 2	 正在读取
     * 3	 正在读取
     * 2	 读取完成： 2
     * 3	 读取完成： 3
     * 4	 正在读取
     * 5	 正在读取
     * 4	 读取完成： 4
     * 5	 读取完成： 5
     */
}
