package com.griffin.javatutorial;

public class Adder {

    public static void main(String [] args) {
        int argInt = 0;

        for (String s: args) {
            argInt+= Integer.valueOf(s);
        }
        System.out.println("Adder found: " + argInt);
    }
}
