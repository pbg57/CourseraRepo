package com.griffin.javatutorial;

public class FPAdder {

    public static void main(String[] args) {
        float argFloat = 0;

        for (String s : args) {
            System.out.println("Arg: " + s);
            argFloat += Float.parseFloat(s);
        }
        System.out.println("Adder found: " + argFloat);
       // System.out.format("Adder found: %f" + argFloat);
    }
}
