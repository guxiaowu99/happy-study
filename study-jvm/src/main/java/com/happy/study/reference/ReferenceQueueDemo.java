package com.happy.study.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @description: ReferenceQueue 引用队列 对象被回收可放到引用队列中保存一下
 * @author: zhangmengchao
 * @date: Created in 2021/2/8 上午9:45
 * @version: 1.0
 * @modified By:
 */
public class ReferenceQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        Object o1= new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o1, referenceQueue);
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("=====================");
        o1 = null;
        System.gc();
        Thread.sleep(500);
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
        /**
         * java.lang.Object@7a7b0070
         * java.lang.Object@7a7b0070
         * null
         * =====================
         * null
         * null
         * java.lang.ref.WeakReference@39a054a5
         */
    }
}
