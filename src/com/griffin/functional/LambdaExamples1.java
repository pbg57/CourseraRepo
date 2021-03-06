package com.griffin.functional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LambdaExamples1 {


//
// This code was copied from a course example and modified here.
//

    /*
       Any interface with a SAM(Single Abstract Method) is a functional interface, and its implementation may
       be treated as lambda expressions.
       Note that Java 8's default methods are not abstract and do not count; a functional interface may still
       have multiple default methods. We can observe this by looking at the Function's documentation.
    */

    // Note that this annotation is optional, because the interface declaration is a SAM.
    @FunctionalInterface
    public interface ShortToByteFunction {

        byte applyAsByte(short s);
    }

    public byte[] transformArray(short[] array, ShortToByteFunction function) {
        // New byte array sized for input short array
        byte[] transformedArray = new byte[array.length];

        // Iterate, calling functional interface to do the short to byte conversion
        for (int i = 0; i < array.length; i++) {
            transformedArray[i] = function.applyAsByte(array[i]);
        }
        return transformedArray;
    }

    public static void main(String[] args) {
        LambdaExamples1 lam = new LambdaExamples1();
        lam.runTest1();
        lam.runTest2();
    }


    public void runTest1() {
        short[] array = {(short) 1, (short) 2, (short) 3};

        // Note that the functional interface impl (Lambda) is passed as an argument to transformArray.
        // Remember that an object we invoke the method on is, in fact, the implicit first argument of a method.
        // The method is the single abstract method (SAM) of the functional interface, applyAsByte, in this case.
        // The SAM defines the type (short, in this case), being operated on. Note that
        // The passed Lambda does not have to use 's' and can use any name.
        byte[] transformedArray = transformArray(array, s -> (byte) (s * 2));


        byte[] expectedArray = {(byte) 2, (byte) 4, (byte) 6};

        System.out.println(Arrays.toString(array));

        System.out.println(Arrays.toString(transformedArray));

        if (Arrays.equals(expectedArray, transformedArray))
            System.out.println("Arrays match: success");
        else
            System.out.println("Arrays do not match: error");

    }

    public void runTest2() {
        Map<String, Integer> nameMap = new HashMap<>();

        // computeIfAbsent is a Map method which accepts a Key and mapping Function (Lambda or method reference)
        Integer value = nameMap.computeIfAbsent("John", s -> s.length());   // example, only
        System.out.println("Key: John value: " + nameMap.get("John"));

        if (nameMap.get("John") == "John".length())
            System.out.println("Map values match: success");
        else
            System.out.println("Map values do not match: error");

        // Note replacement of Lambda with method reference
        nameMap.computeIfAbsent("Dylan", String::length);
        System.out.println("Key: Dylan value: " + nameMap.get("Dylan"));

        if (nameMap.get("Dylan") == "Dylan".length())
            System.out.println("Map values match: success");
        else
            System.out.println("Map values do not match: error");



    }

}
