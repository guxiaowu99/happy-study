package com.happy.study.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @description: ABA问题解决  AtomicStampedReference
 * @author: zhangmengchao
 * @date: Created in 2021/1/12 下午5:52
 * @version: 1.0
 * @modified By:
 */
public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);


    public static void main(String[] args) {

        //ABA问题
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);  //AB
            atomicReference.compareAndSet(101, 100);  //A
        }, "ThreadA").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 2021) + "\t  最终结果" + atomicReference.get().toString());
        }, "ThreadB").start();
        /**
         * 结果：
         * true	  最终结果2021
         */


        System.out.println("=======================ABA问题解决方案===================================");
        //ABA问题解决
        new Thread(() -> {
            // 获取版本号
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 第一次版本号" + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t 第二次版本号" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t 第三次版本号" + atomicStampedReference.getStamp());
        }, "ThreadC").start();

        new Thread(() -> {
            // 获取版本号
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 第一次版本号" + stamp);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicStampedReference.compareAndSet(100, 2019,
                    stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "\t 修改是否成功" + result +  "当前最新版本号" + atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName() + "\t 当前实际最新值: " + atomicStampedReference.getReference());
        }, "ThreadD").start();
    }
    /**
     * 结果：
     * =======================ABA问题解决方案===================================
     * ThreadC	 第一次版本号1
     * ThreadD	 第一次版本号1
     * ThreadC	 第二次版本号2
     * ThreadC	 第三次版本号3
     * ThreadD	 修改是否成功false当前最新版本号3
     * ThreadD	 当前实际最新值: 100
     */
}
