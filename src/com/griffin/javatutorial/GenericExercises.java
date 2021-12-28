package com.griffin.javatutorial;

import javax.swing.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class GenericExercises {

    // Write a generic method to count the number of elements in a collection that have a
    // specific property (for example, odd integers, prime numbers, palindromes).

    public enum Operation {
        ODD, EVEN, PRIME, PALINDROME
    }

    boolean isPrime(Number num) {
        int n = num.intValue();
        for (int i = 2; 2 * i <= n; i++) {
            if (n % i == 0)
                return false;
        }
       // System.out.println("isPrime true for: " + num);
        return true;
    }

    public boolean isPalindrome(String elem) {
        StringBuffer string2 = new StringBuffer((String) elem);
        String elem2 = string2.reverse().toString();

        if (elem.equalsIgnoreCase(elem2))
            return true;
        else
            return false;
    }

    public <T> int CountElements(Collection<T> elemCollection, Operation operation) {

        // Note; This implementation counts and returns a count for a  specific input operation
        int elemCount = 0;

        for (T elem : elemCollection) {

            switch (operation) {
                case ODD:
                    if (elem instanceof Integer) {
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
        results = genericExercises.countElements2(intCollection, genericExercises.new IntegerOdd());
        System.out.println("CountElems2 found " + results + " Odd integers in collection");

        Collection<Integer> primeInts = new ArrayList<>();
        Collections.addAll(primeInts, 1,2,3,4,7,9,17, 128, 64, 19);
        results = genericExercises.countElements2(primeInts, genericExercises.new IntegerPrime());
        System.out.println("CountElems2 found " + results + " Prime integers in collection");

        results = genericExercises.countElements2(stringCollection, genericExercises.new StringPalindrome());
        System.out.println("CountElems2 found " + results + " Palindromes in collection");

    }

    // Different (more correct generic usage) solution implementation below, based on Java tutorial solution:

    public interface UnaryPredicate<T> {
        public boolean test(T obj);
    }

    // A Class that tests for an Odd Integer
    public class IntegerOdd implements UnaryPredicate<Integer> {
        public boolean test(Integer intObject) {
            return ((intObject.intValue() % 2) != 0);
        }
    }

    // A Class that tests for a prime number
    public class IntegerPrime implements UnaryPredicate<Integer> {
        public boolean test(Integer obj) {
            return isPrime(obj);
        }
    }

    // A Class that tests for a Palindrome String
    public class StringPalindrome implements UnaryPredicate<String> {
        @Override
        public boolean test(String obj) {
            if (isPalindrome(obj))
                return true;
            else
                return false;
        }
    }
    public <T> int countElements2(Collection<T> elements, UnaryPredicate unaryPredicate) {
        int count = 0;
        for (T obj: elements) {
            if (unaryPredicate.test(obj))
                count++;
        }
        return count;
    }
}
