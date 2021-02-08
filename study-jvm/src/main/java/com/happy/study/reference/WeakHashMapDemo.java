package com.happy.study.reference;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @description: WeakHashMap
 * @author: zhangmengchao
 * @date: Created in 2021/2/7 下午4:48
 * @version: 1.0
 * @modified By:
 */
public class WeakHashMapDemo {

    public static void main(String[] args) {
        myHashMap();
        System.out.println("=========myWeakHashMap===========");
        myWeakHashMap();

        /**
         * 运行结果：
         * {1=HashMap}
         * {1=HashMap}
         * {1=HashMap}	1
         * =========myWeakHashMap===========
         * {2=weakHashMap}
         * {2=weakHashMap}
         * {}	0
         */
    }

    private static void myWeakHashMap() {
        WeakHashMap<Integer,String> weakHashMap= new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "weakHashMap";
        weakHashMap.put(key, value);
        System.out.println(weakHashMap);
        key = null;
        System.out.println(weakHashMap);
        System.gc();
        System.out.println(weakHashMap + "\t" +weakHashMap.size());
    }


    private static void myHashMap() {
        HashMap<Integer,String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";
        map.put(key, value);
        System.out.println(map);
        key = null;
        System.out.println(map);
        System.gc();
        System.out.println(map + "\t" +map.size());
    }
}
