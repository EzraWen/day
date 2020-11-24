package com.ezra.set;

import com.ezra.vo.TestVO;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetTest {


    /**
     * 迭代器迭代的过程
     *         HashIterator() {
     *             expectedModCount = modCount;
     *             Node<K,V>[] t = table;
     *             current = next = null;
     *             index = 0;
     *             if (t != null && size > 0) { // advance to first entry
     *                 do {} while (index < t.length && (next = t[index++]) == null);
     *             }
     *         }
     * 首先是获取迭代器的方法，获取到底层table中第一个不为null的数据，并赋值给迭代器中的next属性（为Node类型），记录index为当前数据的index+1，记录expectedModCount为实例的modCount,用于判断是否并发修改
     *
     * hasNext方法
     *        public final boolean hasNext() {
     *             return next != null;
     *        }
     *
     * 判断next属性是否为Null
     *
     * next方法
     * 实际调用的事nextNode方法
     * 首先判断迭代器的next是否为null,expectedModCount是否等于实例的modCount
     * 将迭代器current = next,并判断next.next是否为空，不为空使next = next.next，若为空则执行do {} while (index < t.length && (next = t[index++]) == null); 从上一个index开始找到下一个不为null的数据，再次记录next和index
     * 返回current
     * 所以迭代过程中如果一个数据的next数据有值，优先迭代这部分数据，如
     * [ 1 , 2, 3, 4]
     *   |
     *   7
     *   |
     *   6
     *   迭代顺序应为  1,7,6,2,3,4
     *
     *   final class KeyIterator extends HashIterator
     *         implements Iterator<K> {
     *         public final K next() { return nextNode().key; }
     *     }
     *
     *   final Node<K,V> nextNode() {
     *             Node<K,V>[] t;
     *             Node<K,V> e = next;
     *             if (modCount != expectedModCount)
     *                 throw new ConcurrentModificationException();
     *             if (e == null)
     *                 throw new NoSuchElementException();
     *             if ((next = (current = e).next) == null && (t = table) != null) {
     *                 do {} while (index < t.length && (next = t[index++]) == null);
     *             }
     *             return e;
     *         }
     *
     *
     *   HashSet的实现依赖HashMap
     *   底层持有一个map,set.add(value)实际上是map.put(value,new Object());
     *   调用的还是map中的方法
     * @param args
     */


    public static void main(String[] args) {


        HashSet<TestVO> hashSet = new HashSet<>();

        hashSet.add(new TestVO(1,"数据1"));
        hashSet.add(new TestVO(1,"数据2"));
        hashSet.add(new TestVO(1,"数据3"));
        Iterator<TestVO> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            TestVO next = iterator.next();
        }

        HashSet<TestVO> m = new HashSet<>(),p;
        p=new HashSet<>();
        System.out.println(p);


    }
}
