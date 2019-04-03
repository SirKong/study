package com.ccnu.test.datastruct;

public class Offer01FindInArray {

    public static boolean find(int[][] array, int k) {
        if (null == array || 0 == array.length) {
            return false;
        }

        int i = 0;
        int j = array[0].length - 1;
        while (i < array.length && j >= 0) {
            int value = array[i][j];
            if (k == value) {
                System.out.println("array[" + i + "][" + j + "]");
                return true;
            } else if (k < value) {
                j--;
            } else {
                i++;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] array = new int[][]{{10, 30, 50, 70, 90}, {20, 40, 60, 80, 100}, {25, 45, 65, 85, 105}, {28, 48, 68,
                88, 108}};

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println(find(array, 51));
        System.out.println(find(array, 71));
        System.out.println(find(array, 20));
        System.out.println(find(array, 88));
    }
}
