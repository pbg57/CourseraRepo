package com.griffin.javatutorial;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;

public class ListOfNumbers {
    private List<Integer> list;
    private static final int SIZE = 10;

    public static void main(String[] args) {
        ListOfNumbers listOfNumbers = new ListOfNumbers();
        listOfNumbers.readList(5);
    }

    public ListOfNumbers() {
        list = new ArrayList<Integer>(SIZE);
        for (int i = 0; i < SIZE; i++)
            list.add(i);
    }

    public void writeList() {
        PrintWriter out = null;

        try {
            System.out.println("Entering try statement");
            out = new PrintWriter(new FileWriter("OutFile.txt"));

            for (int i = 0; i < SIZE; i++)
                out.println("Value at: " + i + " = " + list.get(i));
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Caught IndexOutOfBoundsException: " +
                    e.getMessage());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        } finally {
            if (out != null) {
                System.out.println("Closing PrintWriter");
                out.close();
            } else {
                System.out.println("PrintWriter not open");
            }
        }
    }

    public void readList(int numToRead)  {

        Vector<Integer> intVector = new Vector<>(numToRead);
        FileInputStream fileInputStream = null;
        // Open file and read numToRead integers from file, and add them to a Vector

        String inputFile = "/users/griffinhome/IdeaProjects/CourseraJavaSamples/src/com/griffin/javatutorial/inputFile.txt";
        try {
            FileReader fileReader = new FileReader(new File(inputFile));
            BufferedReader reader = new BufferedReader(fileReader);

            // Read int values
            for (int i = 0; i <numToRead; i++) {
                int inputInt = reader.read();
                intVector.add(Character.getNumericValue((char) inputInt));
                System.out.println("Next input: " + (char)inputInt);

            }

        } catch (FileNotFoundException fnfe) {
            System.out.println("ListOfNumbers didn't find input file: " + inputFile);
        } catch (IOException ioe) {
            System.out.println("ListOfNumbers error reading input file: " + inputFile);
        }

        // Write out ints
        for (Integer intVal : intVector) {
            System.out.println("Next Val: " + intVal);
        }
    }

    // NOTE: alternative impl using RandomAccessFile

    public void readList(String fileName) {
        Vector<Integer> victor = new Vector<>();

        String line = null;
        try {
            RandomAccessFile raf = new RandomAccessFile(fileName, "r");
            while ((line = raf.readLine()) != null) {
                Integer i = Integer.parseInt(line);
                System.out.println(i);
                victor.addElement(i);
            }
        } catch(FileNotFoundException fnf) {
            System.err.println("File: " + fileName + " not found.");
        } catch (IOException io) {
            System.err.println(io.toString());
        }
    }
}

