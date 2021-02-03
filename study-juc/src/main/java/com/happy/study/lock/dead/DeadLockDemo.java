package com.happy.study.lock.dead;

/**
 * @description: 死锁demo
 * @author: zhangmengchao
 * @date: Created in 2021/2/3 上午10:16
 * @version: 1.0
 * @modified By:
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB), "ThreadA").start();
        new Thread(new HoldLockThread(lockB, lockA), "ThreadB").start();
        /**
         * 运行结果 产生死锁
         * ThreadA	 自己持有lockA	 尝试获得: lockB
         * ThreadB	 自己持有lockB	 尝试获得: lockA
         */
    }
}
