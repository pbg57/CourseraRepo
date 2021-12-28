package com.griffin.testdome;

import java.util.ArrayList;
import java.util.List;

public class TestDome {
    public static double average(int a, int b) {
        return ((double) a + b) / 2;
    }

    public static void main(String[] args) {
        System.out.println(average(2, 1));
    }


//    public class MergeNames {
//
//        public static String[] uniqueNames(String[] names1, String[] names2) {
//            List<String> strReturnList = new ArrayList<>();
//
//            for (String name : names1) {
//                if (!strReturnList.contains(name))
//                    strReturnList.add(name);
//            }
//
//            for (String name : names2) {
//                if (!strReturnList.contains(name))
//                    strReturnList.add(name);
//            }
//
//            String[] returnArray = new String[strReturnList.size()];
//
//            int i = 0;
//            for (String strElem : strReturnList)
//                returnArray[i++] = strElem;
//
//            return returnArray;
//
//        }
//
//
//        public static void main(String[] args) {
//            String[] names1 = new String[]{"Ava", "Emma", "Olivia"};
//            String[] names2 = new String[]{"Olivia", "Sophia", "Emma"};
//            System.out.println(String.join(", ", MergeNames.uniqueNames(names1, names2))); // should print Ava, Emma, Olivia, Sophia
//        }
//    }

    public class UserInput {

        public static class TextInput {
            static String inputValue = new String();

            public void add(char c) {


            }

            public String getValue() {
                return inputValue;
            }
        }

        public static class NumericInput extends TextInput {

            public void add(char c) {
                if (Character.isDigit(c)) {
                    inputValue += c;
                }
            }
        }

        public static void main(String[] args) {
            TextInput input = new NumericInput();
            input.add('1');
            input.add('a');
            input.add('0');
            System.out.println(input.getValue());
        }
    }
}
