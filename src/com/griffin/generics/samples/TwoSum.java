package com.griffin.generics.samples;

public class TwoSum {
    public static int[] findTwoSum(int[] list, int sum) {
        /*
        Iterates through an int array to locate a pair of addends that total 'sum'.
        Return indices of addends.
         */
        int[] returnArray = new int[2];

        for (int addendOne = 0; addendOne < list.length; addendOne++) {
            if (list[addendOne] <= sum) {   // skip if out of range
                for (int addendTwo = addendOne + 1; addendTwo < list.length; addendTwo++) {
                    if (list[addendTwo] + list[addendOne] == sum) {
                        returnArray[0] = addendOne;
                        returnArray[1] = addendTwo;
                        return returnArray;
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] indices = findTwoSum(new int[]{3, 1, 5, 7, 5, 9}, 10);
        if (indices != null) {
            System.out.println(indices[0] + " " + indices[1]);
        }
    }
}