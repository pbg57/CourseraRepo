package com.griffin.generics.samples;

import java.util.*;
import java.util.stream.Collectors;

public class LeetCode {

    public static void main(String[] args) {
        LeetCode lc = new LeetCode();
        lc.testRunner();
    }


    void testRunner() {
        // Run tests
        int[] intArray = {2, 4, 5, 6, 3, 55, 4, 66};
        /*
        Return the two indices of the addends for the input target sum.
         */
        int[] retValue = new Solution().twoSum(intArray, 61);
        System.out.println("twoSum input: " + Arrays.toString(intArray));
        System.out.println("twoSum output: " + Arrays.toString(retValue));

        /*
        Find the three array entries whose sum equals zero, and whose values form a unique set of addends.
         */
//        int[] intArray2 = {-1, 0, 1, 2, -1, -4};
//        int[] intArray2 = {1, 2, -2, -1};
        int[] intArray2 = {-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0};

        System.out.println("3Sum input: " + Arrays.toString(intArray2));
        List<List<Integer>> retValue2 = new Solution3Sum().threeSum(intArray2);

        for (List<Integer> list : retValue2) {
            System.out.println("3Sum output: " + list.toString());
        }

//        String a = "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101";
//        String b = "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011";
        String a = "11";
        String b = "101";
        /*
        Add two binary numbers and return them as a string.
         */
        new SolutionAddBinary().addBinary(a, b);

        /*
        Given an integer array with duplicate entries, find the single unique entry
         */
        int[] inArray = {5, 3, 2, 2, 1, 1, 3};
        int resultInt = new SolutionSingleNumber().singleNumber(inArray);
        System.out.println("SingleNUmber found " + resultInt);

        // Examples to use stream
        // How to use streams to convert a primitive int []  into a List
        int[] nums = {1, 2, 3};
        List<Integer> inpList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        System.out.println("int array to Integer List:");
        inpList.forEach(System.out::println);

        // How to use streams to convert a object String []  into a List
        String[] strings = {"one", "two", "three"};
        List<String> strList = Arrays.stream(strings).toList();
        System.out.println("string array to List");
        strList.forEach(System.out::println);

        int[] inArra2y = {5, 3, 2, 2, 3, 2, 3};
        int resultInt2 = new SolutionSingleNumber2().singleNumber(inArra2y);
        System.out.println("SingleNUmber2 found " + resultInt2);

        String inputString = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> resultList = new SolutionFindRepeatedSequence().findRepeatedDnaSequences(inputString);
        System.out.println("Find Repeated Sequence: ");
        resultList.forEach(System.out::println);
    }


    static class Solution {
        /*
        Find the two indices whose sum equals the target value.
         */
        public int[] twoSum(int[] nums, int target) {
            int nextIndex = 0;

            // Nested loops through the array to find the indices
            for (int j = nextIndex; j < nums.length - 1; j++) {
                for (int i = j + 1; i < nums.length; i++) {
                    if ((nums[nextIndex] + nums[i]) == target) {
                        int[] retArray = new int[2];
                        retArray[0] = nextIndex;
                        retArray[1] = i;
                        return retArray;
                    }
                }
                nextIndex++;
            }
            return null;
        }
    } // End class Solution

    static class Solution3Sum {
        /*
        Find the three array entries whose sum equals zero, and whose values form a unique set of addends.
         */
        public List<List<Integer>> threeSum(int[] nums) {

            // Return value is List of integer lists for each unique set of integers totaling zero.
            List<List<Integer>> intList = new ArrayList<>();

            // Use intSet to represent unique HashSet triplets that have been found.
            List<HashSet<Integer>> intSet = new ArrayList<>();

            /*
            Start with i=0; j=i+1; k=i+2 and loop until k>nums.length
             */
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    for (int k = i + 2; k < nums.length; k++) {
                        if (((nums[i] + nums[j] + nums[k]) == 0) &&
                                (i != j) && (i != k) && (j != k)) {
                            System.out.println("Processing " + nums[i] + " " + nums[j] + " " + nums[k]);
                            Integer[] solnArray = new Integer[3];
                            solnArray[0] = nums[i];
                            solnArray[1] = nums[j];
                            solnArray[2] = nums[k];
                            List<Integer> itListInt = Arrays.asList(solnArray);
                            if (intList.size() == 0) {
                                // First entry into return List
                                intList.add(itListInt);
                                HashSet<Integer> hashInt = new HashSet<>();
                                hashInt.add(nums[i]);
                                hashInt.add(nums[j]);
                                hashInt.add(nums[k]);
                                intSet.add(hashInt);
                            } else {
                                // Need to not allow duplicate triplets in intList.
                                // Create a List of HashSets of Integers to represent unique triplets
                                // Determine if we have added this triplet already?;
                                boolean found = false;
                                for (HashSet<Integer> set : intSet) {
                                    int count = 0;
                                    for (Integer itInt : itListInt) {
                                        if (set.contains(itInt))
                                            ++count;
                                    }
                                    if (count == 3) {
                                        // Found this triplet already.
                                        System.out.println("Triplet already found");
                                        found = true;
                                    }
                                }
                                if (!found) {
                                    // Have not seen this unique set of addends. Add to return
                                    System.out.println("New Triplet found");
                                    intList.add(itListInt);
                                    HashSet<Integer> hashInt = new HashSet<>();
                                    hashInt.add(nums[i]);
                                    hashInt.add(nums[j]);
                                    hashInt.add(nums[k]);
                                    intSet.add(hashInt);
                                }
                            }
                        }
                    }
                }
            }
            return intList;
        }
    }

    static class SolutionAddBinary {
        public String addBinary(String a, String b) {
            // take two binary numbers and add them. Return as binary string

            // Binary to base 10
            Integer aInt = Integer.parseInt(a, 2);

            System.out.println("addBinary input " + a + " " + b);
            Integer bInt = Integer.parseInt(b, 2);
            int intSum = aInt + bInt;

            String binResult = Integer.toBinaryString(intSum);
            System.out.println("addBinary result " + binResult);
            return binResult;

        }
    }

    static class SolutionSingleNumber {
        /*
        Given an integer array with duplicate entries, find the single unique entry
         */
        public int singleNumber(int[] nums) {
            Set<Integer> inpSet = new HashSet<>();
            List<Integer> inpList = Arrays.stream(nums).boxed().collect(Collectors.toList());

            for (Integer inta : inpList) {
                if (!inpSet.add(inta)) {
                    // Already exists in Set, so remove because this is a duplicate in the input array
                    inpSet.remove(inta);
                }
            }
            // Should only be single entry
            Integer resultInt = null;
            for (Integer inta : inpSet) {
                resultInt = inta;
            }
            return resultInt;
        }
    }

    static class SolutionSingleNumber2 {
        public int singleNumber(int[] nums) {

            int retInt = 0;
            // Convert int[] into List
            List<Integer> intList = Arrays.stream(nums).boxed().collect(Collectors.toList());

            // Use a Map to count the number of times we've seen an element?
            HashMap<Integer, Integer> intMap = new HashMap<>();

            for (Integer intElem : intList) {
                if (intMap.containsKey(intElem)) {
                    // Have seen this elem, so increment value
                    intMap.put(intElem, intMap.get(intElem) + 1);
                } else {
                    // have not see this elem, so add
                    intMap.put(intElem, 1);
                }
            }
            // Iterate over Map for element with single occurrence
            // Collections<V> intColl = intMap.values();

            for (Integer intKey : intMap.keySet()) {
                if (intMap.get(intKey) == 1) {
                    retInt = intKey;
                    break;
                }
            }

            return retInt;
        }
    }

    static class SolutionFindRepeatedSequence {
        public List<String> findRepeatedDnaSequences(String s) {
            // Process String, collecting 10 letter long strings based on overall length.
            // For each, construct substring and use String.indexOf() to locate unique instances of the substring.

            List<String> strListResult = new ArrayList<>();

            for (int i = 0; i < s.length(); i++) {
                if ((i + 10) < s.length()) {
                    String currentSubstring = s.substring(i, i + 10);
                    int foundIndex = s.indexOf(currentSubstring, i + 1);
                    if (foundIndex > i) {
                        if (!strListResult.contains(currentSubstring))
                            strListResult.add(currentSubstring);
                    }
                }
            }
            return strListResult;
        }
    }
}
