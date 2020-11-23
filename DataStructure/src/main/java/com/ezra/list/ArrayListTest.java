package com.ezra.list;

import java.util.ArrayList;

public class ArrayListTest {

    /**
     * arraylist创建分配的默认长度    10
     *
     * 增长因子  增长后相当于之前长度的1.5倍
     * int oldCapacity = elementData.length;
     * int newCapacity = oldCapacity + (oldCapacity >> 1);
     */


    /**
     *  长度最大 =  integer最大值 （2的31次方 -1） - 8
     */

    public static void test(){
        ArrayList<Object> list = new ArrayList<>();
        System.out.println("刚创建List:" + list);
        System.out.println("刚创建List.size:" + list.size());
        /**
         * 默认的构造器创建时，底层数组=object[],size为0
         * add时判断容量是否能满足size+1,判断(size+1) - object[].length > 0?
         * add时modCount+1
         */
        list.add("ddd");
        list.add("ddd");
        list.add("ddd");
        list.add("ddd");
        list.add("ddd");
        list.add("ddd");
        list.add("ddd");
        list.add("ddd");
        list.add("ddd");
        list.add("ddd");
        list.add("ddd");
        list.add("ddd");
        list.add("ddd");
        list.add("ddd");
        list.add("ddd");
        list.add("ddd");
        System.out.println("add后List.size:" + list.size());
    }


    public static void main(String[] args) {
        test();
//        int oldCapacity = 10;
//        System.out.println(oldCapacity >> 1);
    }
}
