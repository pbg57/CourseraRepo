package com.griffin.generics.samples;

import java.util.*;

public class JdkTutorial {
    /*
       Write generic methods to compare elements in a collection that have a specific property
     (for example, odd integers, prime numbers, palindromes).
     */

    public static void main(String[] args) {

        Integer[] intArray = new Integer[]{7, 3, 5, 8, 2};
        String[] strArray = new String[]{"1", "2", "3", "4", "5"};

/*
Determine the number of generic List elements that are greater than an input value.
Show for two different types: Integer and String.
 */
        int result = compareGeneric(intArray, 3, "isGreater");
        System.out.println("CompareGeneric input: " + Arrays.toString(intArray) + " result: " + result);

        result = compareGeneric(strArray, "2", "isGreater");
        System.out.println("CompareGeneric input: " + Arrays.toString(strArray) + " result: " + result);


/*
Swap the positions of two elements in  a generic List.
Show for two different types: Integer and Float.
 */

        Integer[] intElements = new Integer[]{10, 11};

        System.out.println("Generic exchangeElements before: " + Arrays.toString(intElements));
        exchangeElements(intElements);
        System.out.println("Generic exchangeElements after: " + Arrays.toString(intElements));

        Float[] floatElements = new Float[]{10.01f, 11.011f};

        System.out.println("Generic exchangeElements before: " + Arrays.toString(floatElements));
        exchangeElements(floatElements);
        System.out.println("Generic exchangeElements after: " + Arrays.toString(floatElements));

        // Write a generic method to find the maximal element in the range [begin, end) of a list.
        // Show for two different types: Integer and Float.

        List<Integer> intList = Arrays.asList(5, 4, 3, 2, 6);
        List<Float> fElements  = Arrays.asList(10.01f, 11.011f, 4.2f, 0.02f, 33.3f);

        int begin = 1;
        int end = 3;
        Integer maxResult = findMaximumElement(intList, begin, end);
        System.out.println("FindMaxElement input: " + intList);
        System.out.println("FindMaxElement begin:end: " + begin + ":" + end);
        String stringResult = (maxResult == null ? "null" : maxResult.toString());
        System.out.println("FindMaxElement output: " + stringResult);

        begin = 0;
        end = 3;
        Float maxfResult = findMaximumElement(fElements, begin, end);
        System.out.println("FindMaxElement input: " + fElements);
        System.out.println("FindMaxElement begin:end: " + begin + ":" + end);
        stringResult = (maxfResult == null ? "null" : maxfResult.toString());
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

            T maxElem = elemList.get(begin);
            for (int i = begin; i < end; i++) {
                int eval = maxElem.compareTo(elemList.get(i + 1));
                if (eval < 0)
                    maxElem = elemList.get(i + 1);
            }
            return maxElem;
        } else
            return null;
    }

} // end class