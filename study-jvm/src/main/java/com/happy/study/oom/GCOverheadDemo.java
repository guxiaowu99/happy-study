package com.happy.study.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: GC回收占用98%时间且只回收了 不到2%的内存 效果很差
 * @author: zhangmengchao
 * @date: Created in 2021/2/8 上午11:34
 * @version: 1.0
 * @modified By:
 */
public class GCOverheadDemo {

    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Throwable e) {
            System.out.println("********i:" + i);
            e.printStackTrace();
            throw e;
        }

        /**
         * 运行结果：
         * ********i:149932
         * java.lang.OutOfMemoryError: GC overhead limit exceeded
         * 	at java.lang.Integer.toString(Integer.java:403)
         * 	at java.lang.String.valueOf(String.java:3099)
         * 	at com.happy.study.oom.GCOverheadDemo.main(GCOverheadDemo.java:20)
         * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
         * 	at java.lang.Integer.toString(Integer.java:403)
         * 	at java.lang.String.valueOf(String.java:3099)
         * 	at com.happy.study.oom.GCOverheadDemo.main(GCOverheadDemo.java:20)
         */
    }
}
