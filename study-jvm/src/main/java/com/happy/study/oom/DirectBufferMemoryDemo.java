package com.happy.study.oom;

import java.nio.ByteBuffer;

/**
 * @description: 直接内存溢出
 * @author: zhangmengchao
 * @date: Created in 2021/2/8 上午11:52
 * @version: 1.0
 * @modified By:
 */
public class DirectBufferMemoryDemo {

    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory:" + (sun.misc.VM.maxDirectMemory() /(double)1024 /1024) + "MB"); //1820.5MB 大概内存的4分之一
        // 分配到直接内存
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 *1024 *1024);

        /**
         * 结果分析：
         * 配置的maxDirectMemory:5.0MB
         * [GC (System.gc()) [PSYoungGen: 1926K->496K(2560K)] 1926K->588K(9728K), 0.0019906 secs] [Times: user=0.00 sys=0.01, real=0.00 secs]
         * [Full GC (System.gc()) [PSYoungGen: 496K->0K(2560K)] [ParOldGen: 92K->486K(7168K)] 588K->486K(9728K), [Metaspace: 2992K->2992K(1056768K)], 0.0052893 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
         * Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
         * 	at java.nio.Bits.reserveMemory(Bits.java:694)
         * 	at java.nio.DirectByteBuffer.<init>(DirectByteBuffer.java:123)
         * 	at java.nio.ByteBuffer.allocateDirect(ByteBuffer.java:311)
         * 	at com.happy.study.oom.DirectBufferMemoryDemo.main(DirectBufferMemoryDemo.java:17)
         */
    }
}
