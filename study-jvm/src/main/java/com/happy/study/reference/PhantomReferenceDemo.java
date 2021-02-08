package com.happy.study.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @description: 虚引用
 * @author: zhangmengchao
 * @date: Created in 2021/2/8 上午10:40
 * @version: 1.0
 * @modified By:
 */
public class PhantomReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        Object o1= new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        /**
         * referenceQueue 其实就是用于监控然后通知，在gc回收之前还有没有遗言
         */
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1, referenceQueue);
        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("=====================");
        o1 = null;
        System.gc();
        Thread.sleep(500);
        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
        /**
         * 运行结果：
         * java.lang.Object@7a7b0070
         * null
         * null
         * =====================
         * null
         * null
         * java.lang.ref.PhantomReference@39a054a5
         */
    }
}
