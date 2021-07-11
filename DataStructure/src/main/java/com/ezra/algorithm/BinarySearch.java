package com.ezra.algorithm;


import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 二分查询的实现,基于已经排序好的数据
 */
public class BinarySearch {

    private final static int[] nums = {1,2,3,4,4,5,7,8,9,10,12,54,55,56,67,70};


    public static void main(String[] args) {
//        System.out.println(binarySearchGetSingleValue(10));
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("GMT+:08:00"));
        instance.set(Calendar.YEAR,2021);
        instance.set(Calendar.MONTH,5);
        instance.set(Calendar.DAY_OF_MONTH,19);
        instance.set(Calendar.HOUR_OF_DAY,22);
        instance.set(Calendar.MINUTE,0);
        instance.set(Calendar.SECOND,0);

        Date startTime = instance.getTime();


//        instance.add(Calendar.HOUR_OF_DAY,2);
        instance.add(Calendar.MINUTE,2);
        Date now = instance.getTime();


        long l = datetimeCheck(startTime, now);
        if (l == 0) {
            System.out.println("计算1次即可");
            return;
        }

        Date temp = null;
        instance.setTime(startTime);
        for (long i = 0; i < l; i++) {
            instance.add(Calendar.MINUTE,5);
            temp = instance.getTime();
            System.out.println("计算区间" + startTime +"-" + temp);
            startTime = temp;
        }
        if (temp.before(now)) {
            System.out.println("计算最后的区间" + temp + "-" + now);
        }



    }
    /**
     * 二分查询获取单个值对应的下标值
     */
    private static int binarySearchGetSingleValue(int target){
        int left = 0;
        int right = nums.length -1;
        //搜素区间[left,right]

        while (left<=right){
            int mid = (left + right) /2;
            if(nums[mid] == target)
                return mid;
            else if(nums[mid] > target)
                right = mid -1;
            else if(nums[mid] < target)
                left = mid +1;
        }
        return -1;
    }


    /**
     * 计算两个时间之间相差多少分钟
     * @return
     */
    private static long datetimeCheck(Date startTime ,Date endTime){
        long time = endTime.getTime() - startTime.getTime();
        long m = time / 1000 / 60;
        long count = m / 5;


        return count;


    }
}
