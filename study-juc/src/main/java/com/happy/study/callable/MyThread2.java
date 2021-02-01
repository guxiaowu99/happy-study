package com.happy.study.callable;

import java.util.concurrent.Callable;

/**
 * @description: 线程实现方式2
 * @author: zhangmengchao
 * @date: Created in 2021/2/1 下午4:50
 * @version: 1.0
 * @modified By:
 */
public class MyThread2 implements Callable<Integer> {

    // 带返回值
    @Override
    public Integer call() throws Exception {
        System.out.println("******* come in callable");
        return 1024;
    }
}
