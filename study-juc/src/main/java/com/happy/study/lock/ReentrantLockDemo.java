package com.happy.study.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 可重入锁
 * @author: zhangmengchao
 * @date: Created in 2021/1/16 上午10:26
 * @version: 1.0
 * @modified By:
 */
public class ReentrantLockDemo {

    /**
     * 构造方法：
     * 默认是非公平的
     * ReentrantLock lock = new ReentrantLock();
     * <p>
     * 参数可设置，true为公平
     * ReentrantLock fairLoc = new ReentrantLock(true);
     */

    public static void main(String[] args) {

        // ------------------------synchronized--------------------------
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "ThreadA").start();

        new Thread(() -> {
            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "ThreadB").start();


        // ------------------------ReentrantLock--------------------------
        Thread t3 = new Thread(phone,"ThreadC");
        Thread t4 = new Thread(phone,"ThreadD");
        t3.start();
        t4.start();
    }
    /**
     * 结果：
     * ThreadA	 invoked sendSMS()    线程A在外层方法获取锁后
     * ThreadA	 ------- invoked sendEmail()  进入内层方法时会自动获取锁
     * ThreadB	 invoked sendSMS()
     * ThreadB	 ------- invoked sendEmail()
     * ThreadC	 invoked get()
     * ThreadC	 invoked set()
     * ThreadD	 invoked get()
     * ThreadD	 invoked set()
     */
}
