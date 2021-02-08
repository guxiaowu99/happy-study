package com.happy.study.oom;

import java.util.Random;

/**
 * @description: 堆oom
 * @author: zhangmengchao
 * @date: Created in 2021/2/8 上午11:22
 * @version: 1.0
 * @modified By:
 */
public class JavaHeapSpaceDemo {

    public static void main(String[] args) {
        String str = "atguigu";
        while (true){
            // 参数很多新对象
            str += str + new Random().nextInt(1111111)+new Random().nextInt(22222222);
            str.intern();
        }
        /**
         * 结果：
         * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
         * 	at java.util.Arrays.copyOf(Arrays.java:3332)
         * 	at java.lang.AbstractStringBuilder.ensureCapacityInternal(AbstractStringBuilder.java:124)
         * 	at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:674)
         * 	at java.lang.StringBuilder.append(StringBuilder.java:208)
         * 	at com.happy.study.oom.JavaHeapSpaceDemo.main(JavaHeapSpaceDemo.java:18)
         */
    }
}
