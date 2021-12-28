package com.griffin.testdome;

public class TwoSum {
    public static int[] findTwoSum(int[] list, int sum) {
        // Loop through list to find addends

        int currentAddend = 0;

        for (int addendIdx=0; addendIdx < list.length; addendIdx++) {
            if (list[addendIdx] <= sum) {
                currentAddend = list[addendIdx];
                for (int nextAddendIdx=(addendIdx+1); nextAddendIdx < list.length; nextAddendIdx++) {
                    if ((currentAddend + list[nextAddendIdx]) == sum) {
                        int[] retVal = {addendIdx, nextAddendIdx};
                        return retVal;
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] indices = findTwoSum(new int[] { 3, 1, 5, 7, 5, 9 }, 10);
        if(indices != null) {
            System.out.println(indices[0] + " " + indices[1]);
        }
    }
}