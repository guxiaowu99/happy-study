package com.happy.study.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: zhangmengchao
 * @date: Created in 2021/1/16 上午10:50
 * @version: 1.0
 * @modified By:
 */
public class Phone implements Runnable{

    // ------------------------synchronized--------------------------

    public synchronized void sendSms() throws Exception{
        System.out.println(Thread.currentThread().getName() + "\t invoked sendSMS()");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName() + "\t ------- invoked sendEmail()");
    }

    // ------------------------ReentrantLock--------------------------

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get(){
        // 只要lock和unlock匹配可以上多层锁
        // 当lock比unlock多的时候，运行会卡死
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoked get()");
            set();
        }catch (Exception e){

        }finally {
            lock.unlock();
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoked set()");
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }
}
