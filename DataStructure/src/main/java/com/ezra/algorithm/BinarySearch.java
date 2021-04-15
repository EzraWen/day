package com.ezra.algorithm;


/**
 * 二分查询的实现,基于已经排序好的数据
 */
public class BinarySearch {

    private final static int[] nums = {1,2,3,4,4,5,7,8,9,10,12,54,55,56,67,70};


    public static void main(String[] args) {
        System.out.println(binarySearchGetSingleValue(10));
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
}
