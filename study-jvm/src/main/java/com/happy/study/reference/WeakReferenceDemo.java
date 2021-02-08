package com.happy.study.reference;

import java.lang.ref.WeakReference;

/**
 * @description: 弱引用：不管内存够不够，都会被回收
 * @author: zhangmengchao
 * @date: Created in 2021/2/7 下午3:26
 * @version: 1.0
 * @modified By:
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1=null;
        System.gc();
        System.out.println("==============GC之后============");
        System.out.println(o1);
        System.out.println(weakReference.get());
        /**
         * 运行结果：
         * java.lang.Object@7a7b0070
         * java.lang.Object@7a7b0070
         * ==============GC之后============
         * null
         * null
         */
    }
}
