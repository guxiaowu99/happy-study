package com.happy.study.reference;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

/**
 * @description: 软引用案例：内存够用就保留，内存不够就回收
 * @author: zhangmengchao
 * @date: Created in 2021/2/7 下午3:12
 * @version: 1.0
 * @modified By:
 */
public class SoftReferenceDemo {

    public static void softRef_Memory_Enough(){

        Object o1= new Object();
        Reference<Object>  softReference= new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;
        System.gc();

        System.out.println(o1);
        System.out.println(softReference.get());
        /**
         * 结果:
         * java.lang.Object@7a7b0070
         * java.lang.Object@7a7b0070
         * null
         * java.lang.Object@7a7b0070
         */
    }

    /**
     * JVM配置，故意产生大对象并配置小的内存。让它内存不够导致OOM，然后看软引用的回收情况
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRef_Memory_NotEnough(){

        Object o1= new Object();
        Reference<Object>  softReference= new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;
        try {
            byte[] bytes = new byte[30 * 1024 *1024];
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }

        /**
         * 运行结果分析： 后面2次打印结果均为null，说明软引用被回收。
         * [GC (Allocation Failure) [PSYoungGen: 1024K->448K(1536K)] 1024K->448K(5632K), 0.0014684 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
         * java.lang.Object@4f47d241
         * java.lang.Object@4f47d241
         * [GC (Allocation Failure) [PSYoungGen: 1222K->503K(1536K)] 1222K->527K(5632K), 0.0009205 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * [GC (Allocation Failure) [PSYoungGen: 503K->503K(1536K)] 527K->535K(5632K), 0.0006369 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * [Full GC (Allocation Failure) [PSYoungGen: 503K->0K(1536K)] [ParOldGen: 32K->435K(4096K)] 535K->435K(5632K), [Metaspace: 2942K->2942K(1056768K)], 0.0046150 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
         * [GC (Allocation Failure) [PSYoungGen: 0K->0K(1536K)] 435K->435K(5632K), 0.0003966 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * [Full GC (Allocation Failure) [PSYoungGen: 0K->0K(1536K)] [ParOldGen: 435K->419K(4096K)] 435K->419K(5632K), [Metaspace: 2942K->2942K(1056768K)], 0.0073409 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
         * null
         * null
         * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
         * 	at com.happy.study.reference.SoftReferenceDemo.softRef_Memory_NotEnough(SoftReferenceDemo.java:47)
         * 	at com.happy.study.reference.SoftReferenceDemo.main(SoftReferenceDemo.java:61)
         * Heap
         *  PSYoungGen      total 1536K, used 91K [0x00000007bfe00000, 0x00000007c0000000, 0x00000007c0000000)
         *   eden space 1024K, 8% used [0x00000007bfe00000,0x00000007bfe16e88,0x00000007bff00000)
         *   from space 512K, 0% used [0x00000007bff80000,0x00000007bff80000,0x00000007c0000000)
         *   to   space 512K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007bff80000)
         *  ParOldGen       total 4096K, used 419K [0x00000007bfa00000, 0x00000007bfe00000, 0x00000007bfe00000)
         *   object space 4096K, 10% used [0x00000007bfa00000,0x00000007bfa68e68,0x00000007bfe00000)
         *  Metaspace       used 2974K, capacity 4556K, committed 4864K, reserved 1056768K
         *   class space    used 316K, capacity 392K, committed 512K, reserved 1048576K
         */
    }

    public static void main(String[] args) {
        //内存够用的情况
//        softRef_Memory_Enough();
        // 内存不够
        softRef_Memory_NotEnough();
    }
}
