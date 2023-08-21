package com.ds.basic.base;

/**
 * @author ds
 * @date 2023/7/26
 * @description
 */
public class Test3 {
    public static void main(String[] args) {
        int[] nums = {10, 21, 3, 2, 19, 0, 22, 6};
        System.out.println(increasingTriplet(nums));
    }

    public static boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int first = nums[0], second = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num > second) {
                return true;
            } else if (num > first) {
                second = num;
            } else {
                first = num;
            }
        }
        return false;
    }

}
