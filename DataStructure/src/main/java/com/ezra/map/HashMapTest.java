package com.ezra.map;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class HashMapTest {


    public static void main(String[] args) {

        /**
         * 默认的增长因子0.75,默认长度16  最大size：1 << 30
         * Hash冲突，节点树化限制，链表Node >8 ,并且map底层数组的长度 >64,两个都达到，节点存储结构树化
         */
        HashMap<Object, Object> map = new HashMap<>();
        /**
         * putIfAbsent
         * 如果key不存在就put，返回Null
         * 若key存在，不put，返回旧值
         */
        Object o1 = map.put(1, "1");
        System.out.println(o1);
        Object o2 = map.put(1, "2");
        System.out.println(o2);
        map.remove(1);


//        List<Integer> vos = new ArrayList<>();
//        for (int i = 0; i<5;i++){
//            vos.add(i);
//        }
//
//        vos.add(1);
//        List<Integer> collect = vos.stream().filter(key(b -> b.intValue())).collect(Collectors.toList());
//        System.out.println(collect);
        /**
         * 为什么是链表8个节点后才转树？
         *      1.从查询速度来说，红黑树的平均查找长度是log(n)，长度为8，查找长度为log(8)=3，链表的平均查找长度为n/2，当长度为8时，平均查找长度为8/2=4，这才有转换成树的必要；链表长度如果是小于等于6，6/2=3，虽然速度也很快的，但是转化为树结构和生成树的时间并不会太短。
         *        还有选择6和8的原因是：中间有个差值7可以防止链表和树之间频繁的转换。
         *      2.从存储来说，树节点存储大小是链表节点大小的两倍，在节点树较少时，速度没优势并且存储空间也更大
         *      3.从概率上来说，节点桶长度超过8的概率也很小
         * 树节点获取数据的逻辑？
         *      1.找到根节点，比较节点hash大小，和key是否相同，决定向左还是向右偏移
         * 树节点增加数据时，是怎么操作的？  红黑树
         * 已经树化的节点在删除数据时会不会转回链表结构
         * 一致性哈希算法？
         * 一致性哈希算法  节点上下线数据存储问题？
         * 有什么方法减少哈希冲突？
         * hash节点下线产生的数据漂移如何处理？
         * TreeNode的结构   Node的结构
         */


        SortedMap<Integer, String> sortedMap = new TreeMap<>();
        sortedMap.put(1,"1");
        sortedMap.put(2,"1");
        SortedMap<Integer, String> subMap = sortedMap.tailMap(1);
        Integer firstKey = subMap.firstKey();
        String s = subMap.get(firstKey);

    }


    /**
     * PECS
     * producer extends consumer super
     * @param keyE
     * @param <T>
     * @return
     */
    private static <T> Predicate<T> key(Function<? super T,?> keyE){
        ConcurrentHashMap<Object, Object> seen = new ConcurrentHashMap<>();
        return t ->seen.putIfAbsent(keyE.apply(t),true) == null;
    }
}
