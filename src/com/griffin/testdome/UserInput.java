package com.griffin.testdome;

public class UserInput {

    public static class TextInput {
        static String inputValue = new String();

        public void add(char c) {


        }

        public String getValue() {
            System.out.println("in getValue()");
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
        input.add('c');
        input.add('7');
        System.out.println(input.getValue());
    }




}
