package com.griffin.generics.samples;
import java.util.*;
import java.util.stream.Collectors;

public class SortedSearch {
    public static int countNumbers(int[] sortedArray, int lessThan) {
  //      Set <Integer> numberSet = new HashSet<>();
        List<Integer> intList = Arrays.stream(sortedArray).boxed().collect(Collectors.toList());
        int foundLessThan = 0;

        // Use a TreeSet, which is an ordered Collection.
        Set<Integer> numberSet = new TreeSet<>(intList);

        // Iterate across ordered collection, while current value is less thatn lessThan.

        for (Integer intVal : numberSet) {
            if (intVal < lessThan) {
                foundLessThan++;
            } else
            break;
        }
//        Iterator<Integer> setIterator = numberSet.iterator();
//
//
//        while (setIterator.hasNext()) {
//           Integer i = setIterator.next();
//            if (i < lessThan) {
//                foundLessThan++;
//        }
        return foundLessThan;
    }

    public static void main(String[] args) {
        System.out.println(SortedSearch.countNumbers(new int[] { 7, 3, 5, 1 }, 6));
    }
}