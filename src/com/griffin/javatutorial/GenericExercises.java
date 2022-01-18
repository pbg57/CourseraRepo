package com.griffin.javatutorial;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GenericExercises {

    // Write a generic method to count the number of elements in a collection that have a
    // specific property (for example, odd integers, prime numbers, palindromes).

    public enum Operation {
        ODD, EVEN, PRIME, PALINDROME
    }

    public static boolean isPrime(Number num) {
        int n = num.intValue();
        for (int i = 2; 2 * i <= n; i++) {
            if (n % i == 0)
                return false;
        }
       // System.out.println("isPrime true for: " + num);
        return true;
    }

    /*
    A palindrome is text that reads the same forward or backwards.
     */
    public static boolean isPalindrome(String elem) {
        StringBuilder string2 = new StringBuilder(elem);
        String elemReversed = string2.reverse().toString();

        return elem.equalsIgnoreCase(elemReversed);
    }

    public <T> int CountElements(Collection<T> elemCollection, Operation operation) {
        /*
        This implementation returns a count for a specific input operation type.
         */
        int elemCount = 0;

        for (T elem : elemCollection) {

            switch (operation) {
                case ODD:
                    if (elem instanceof Integer) {      // Requiring use instanceof not good practice. See soln below.
                        if ((Integer) elem % 2 != 0)
                            elemCount++;
                    }
                    break;

                case PRIME:
                    if (elem instanceof Number) {
                        if (isPrime((Number) elem)) {
                            elemCount++;
                        }
                    }
                    break;

                case PALINDROME:
                    if (elem instanceof String) {
                        if (isPalindrome((String) elem))
                            elemCount++;
                    }
                    break;

                case EVEN:
                    if (elem instanceof Integer) {
                        if ((Integer) elem % 2 == 0)
                            elemCount++;
                    }
                    break;

            }
        }
        return elemCount;
    }

    public static void main(String[] args) {
        GenericExercises genericExercises = new GenericExercises();

        Collection<Integer> intCollection = new ArrayList<>();
        Collections.addAll(intCollection, 1, 2, 3, 4, 5);

        int results = genericExercises.CountElements(intCollection, Operation.ODD);
        System.out.println("CountElems found " + results + " ODD elements in collection");

        results = genericExercises.CountElements(intCollection, Operation.EVEN);
        System.out.println("CountElems found " + results + " EVEN elements in collection");

        Collection<String> stringCollection = new ArrayList<>();
        Collections.addAll(stringCollection, "One", "Two", "Able was I saw Elba");
        results = genericExercises.CountElements(stringCollection, Operation.PALINDROME);
        System.out.println("CountElems found " + results + " Palindrome elements in collection");

        // The second solution requires an input int Collection
        // Note: inner class instantiation syntax
        results = genericExercises.countElements2(intCollection, new IntegerOdd());
        System.out.println("CountElems2 found " + results + " Odd integers in collection");

        Collection<Integer> primeInts = new ArrayList<>();
        Collections.addAll(primeInts, 1,2,3,4,7,9,17, 128, 64, 19);
        results = genericExercises.countElements2(primeInts, new IntegerPrime());
        System.out.println("CountElems2 found " + results + " Prime integers in collection");

        results = genericExercises.countElements2(stringCollection, new StringPalindrome());
        System.out.println("CountElems2 found " + results + " Palindromes in collection");

    }

    /*
    Below is a different (more correct generic usage) solution,
    based on the Java tutorial solution:
     */

    // A UnaryPredicate asserts a function whose domain is a given set.
    public interface UnaryPredicate<T> {
         boolean test(T obj);
    }

    // A Class that tests for an Odd Integer
    public static class IntegerOdd implements UnaryPredicate<Integer> {
        public boolean test(Integer intObject) {
            return ((intObject % 2) != 0);
        }
    }

    // A Class that tests for a prime number
    public static class IntegerPrime implements UnaryPredicate<Integer> {
        public boolean test(Integer obj) {
            return isPrime(obj);
        }
    }

    // A Class that tests for a Palindrome String
    public static class StringPalindrome implements UnaryPredicate<String> {
        public boolean test(String obj) {
            return isPalindrome(obj);
        }
    }
    public <T> int countElements2(Collection<T> elements, UnaryPredicate<T> unaryPredicate) {
        int count = 0;
        for (T obj: elements) {
            if (unaryPredicate.test(obj))
                count++;
        }
        return count;
    }
}
