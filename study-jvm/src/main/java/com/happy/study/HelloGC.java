package com.happy.study;

import static java.lang.Integer.MAX_VALUE;

/**
 * @description:
 * @author: zhangmengchao
 * @date: Created in 2021/2/7 上午9:21
 * @version: 1.0
 * @modified By:
 */
public class HelloGC {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("************HelloGC");
        Thread.sleep(MAX_VALUE);
    }
}
