package com.griffin.generics.samples;

import java.util.*;

public class JdkTutorial {
    // Write a generic method to count the number of elements in a collection that have a specific property
    // (for example, odd integers, prime numbers, palindromes).

    public static void main(String[] args) {
        ArrayList<Integer> oddList = new ArrayList<>();
        oddList.add(1);


        List<Integer> aList = Arrays.asList(1, 3);
        Integer[] iA = new Integer[2];
        iA[0] = 1;
        iA[1] = 3;

        int result = compareGeneric(iA, 1, "isGreater");
        System.out.println("CompareGeneric input: " + " result:" + result);

//        Write a generic method to exchange the positions of two different elements in an array.

        Integer inpElements[] = new Integer[2];
        inpElements[0] = 10;
        inpElements[1] = 11;
        System.out.println("Generic exchangeElements before: " + inpElements[0].toString());
        exchangeElements(inpElements);
        System.out.println("Generic exchangeElements after: " + inpElements[0].toString());

//        Write a generic method to find the maximal element in the range [begin, end) of a list.

        List<Integer> intList = Arrays.asList(5,4,3,2,6);
        int begin = 1;
        int end = 3;
        Integer maxResult = findMaximumElement(intList, begin, end);
        System.out.println("FindMaxElement input: " + intList.toString());
        System.out.println("FindMaxElement begin:end: " + begin + ":" + end);
        String stringResult = (maxResult == null ? "null" : maxResult.toString());
        System.out.println("FindMaxElement output: " + stringResult);

    } // End main()


    public static <T extends Comparable<T>> int compareGeneric(T[] anArray, T anElem, String property) {
        int count = 0;

        switch (property) {
            case "isGreater":
                for (T elem : anArray) {
                    if (elem.compareTo(anElem) > 0)
                        ++count;
                }
                break;
            case "IsPrimeNumber":
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + property);
        }
        return count;
    }

    public static <T extends Comparable<T>> void exchangeElements(T[] elems) {
        // Assume array length of 2
        if (elems[0].compareTo(elems[1]) != 0) {
            T elem0 = elems[0];
            elems[0] = elems[1];
            elems[1] = elem0;
        }
    }

    public static <T extends Comparable<T>> T findMaximumElement(List<T> elemList, int begin, int end) {

        if ((elemList.size() > end) && (end > begin)) {
            T beginElem = elemList.get(begin);

            T maxElem = beginElem;
            for (int i = begin+1; i < end; i++) {
                if (elemList.get(i).compareTo(elemList.get(i+1)) < 0)
                    maxElem = elemList.get(i+1);
            }
            return maxElem;
        } else
            return null;
    }

    final Comparator<String> IGNORE_CASE_ORDER = String::compareToIgnoreCase;

} // end class