package com.happy.study.oom;

/**
 * @description: 栈大小一般默认 512k-1024k 栈oom
 * @author: zhangmengchao
 * @date: Created in 2021/2/8 上午10:54
 * @version: 1.0
 * @modified By:
 */
public class StackOverflowErrorDemo {

    public static void main(String[] args) {
        stackOverflowError();
    }

    private static void stackOverflowError() {
        /**
         * 循环调用
         */
        stackOverflowError();
    }
    /**
     * 运行结果：
     * 	Exception in thread "main" java.lang.StackOverflowError
     * 	at com.happy.study.oom.StackOverflowErrorDemo.stackOverflowError(StackOverflowErrorDemo.java:17)
     */
}
