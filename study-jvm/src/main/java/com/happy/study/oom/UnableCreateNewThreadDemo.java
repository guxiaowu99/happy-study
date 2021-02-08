package com.happy.study.oom;

/**
 * @description: 不能创建新线程
 * @author: zhangmengchao
 * @date: Created in 2021/2/8 下午3:20
 * @version: 1.0
 * @modified By:
 */
public class UnableCreateNewThreadDemo {

    public static void main(String[] args) {
        for (int i=1; ; i++){
            System.out.println("************** i= " +i);
            new Thread(() ->{
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },""+i).start();
        }
    }
}
