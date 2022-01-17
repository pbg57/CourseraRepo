package com.griffin.generics.samples;
import java.util.*;
import java.util.stream.Collectors;

public class SortedSearch {
    /*
    Write a method that accepts non-sorted array of unique integers and returns
    a count of how many values are less than a given input value. Use sorting
    to improve search performance.
     */
    public static int countNumbers(int[] unSortedArray, int lessThan) {
        List<Integer> intList = Arrays.stream(unSortedArray).boxed().collect(Collectors.toList());
        int foundLessThan = 0;

        // Use a TreeSet, which is an ordered Collection.
        Set<Integer> numberSet = new TreeSet<>(intList);

        // Iterate across ordered collection, while current value is less than lessThan.

        for (Integer intVal : numberSet) {
            if (intVal < lessThan) {
                foundLessThan++;
                System.out.println("SortedSearch found: " + intVal);
            } else
                // End the sorted search, as no more elements are < lessThan
            break;
        }
        return foundLessThan;
    }

    public static void main(String[] args) {
        System.out.println(SortedSearch.countNumbers(new int[] { 7, 3, 5, 1 }, 6));
    }
}