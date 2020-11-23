package com.ezra.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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


        System.out.println(map.get(1));
//        List<Integer> vos = new ArrayList<>();
//        for (int i = 0; i<5;i++){
//            vos.add(i);
//        }
//
//        vos.add(1);
//        List<Integer> collect = vos.stream().filter(key(b -> b.intValue())).collect(Collectors.toList());
//        System.out.println(collect);

    }



    private static <T> Predicate<T> key(Function<? super T,?> keyE){
        ConcurrentHashMap<Object, Object> seen = new ConcurrentHashMap<>();
        return t ->seen.putIfAbsent(keyE.apply(t),true) == null;
    }
}
