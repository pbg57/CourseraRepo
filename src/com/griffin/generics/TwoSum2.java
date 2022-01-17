package com.griffin.generics;


public class TwoSum2 {
    public static int[] findTwoSum(int[] list, int sum) {

        for (int i = 0; i < list.length; i++) {
            if (list[i] < sum) {    // skip if out of range
                for (int j = i; j < list.length; j++)
                    if (list[j] < sum) {     // skip if out of range
                        if ((list[i] + list[j]) == sum) {
                            return new int[]{ i, j};
                        }
                    }
                }
            }
        return null;
        }

        public static void main (String[]args){
            int[] indices = findTwoSum(new int[]{3, 1, 5, 7, 5, 9}, 10);
            if (indices != null) {
                System.out.println(indices[0] + " " + indices[1]);
            }
        }
    }

