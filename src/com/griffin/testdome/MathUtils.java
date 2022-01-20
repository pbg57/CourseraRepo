package com.griffin.testdome;

/*
Utility math functions.
 */
public class MathUtils {

    /*
    Returns the double precision sum of two ints.
     */
    public static double average(int a, int b) {
        return ((double) a + (double) b) / 2;
    }

    public static void main(String[] args) {

        System.out.println("double val: " + average(2,1));
    }
}
