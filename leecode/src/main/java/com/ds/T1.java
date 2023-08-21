package com.ds;

/**
 * @author ds
 * @date 2023/8/21
 * @description
 */
public class T1 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{2, 1, 3}, {6, 5, 4}, {7, 8, 9}}; // [[2,1,3],[6,5,4],[7,8,9]]
        // 第一行最小值
        int min = matrix[0][0];
        for (int i = 0; i < matrix.length; i++) {
            min = Math.min(min, matrix[0][i]);
        }
        System.out.println("min = " + min);

        System.out.println(minFallingPathSum(matrix, 0, 1));
    }

    public static int minFallingPathSum(int[][] matrix,int n, int a) {
        int start = matrix[n][a];
        int down = matrix[n + 1][a];
        System.out.println("down = " + down);
        int left = matrix[n + 1][a - 1];
        System.out.println("left = " + left);
        int right = matrix[n + 1][a + 1];
        System.out.println("right = " + right);
        return 0;
    }
}
