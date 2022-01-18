package com.griffin.javatutorial;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;

/*
This class reads and writes list of numbers from files to examine int/char casting and also exception processing.
 */
public class ListOfNumbers {
    private final List<Integer> list;
    private final int SIZE = 10;
    private final String inputFile = "/users/griffinhome/IdeaProjects/CourseraJavaSamples/src/com/griffin/javatutorial/inputFile.txt";

    // Use constructor to initialize class.
    public ListOfNumbers() {
        list = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; i++)
            list.add(i);
    }

    public static void main(String[] args) {
        ListOfNumbers listOfNumbers = new ListOfNumbers();
        listOfNumbers.readList(5);
        listOfNumbers.readList(listOfNumbers.inputFile);
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

    /*
    readList assumes a file containing chars and reads an int at a time.
     */
    public void readList(int numToRead) {

        List<Integer> intList = new ArrayList<>();    // Capacity grows automatically. Not synchronized.

        // Open file and read numToRead ints from file, and add them to a List
        try {
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader reader = new BufferedReader(fileReader);

            // Read int values
            for (int i = 0; i < numToRead; i++) {
                int inputInt = reader.read();       // Reads single int (i.e. 51 (== '3' char ascii value)
                intList.add(Character.getNumericValue((char) inputInt));    // cast int -> char and store its numeric value (3)

                System.out.println("Next input as int: " +  inputInt);
                System.out.println("Next input as numeric val: " +  intList.get(i));

            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("ListOfNumbers didn't find input file: " + inputFile);
        } catch (IOException ioe) {
            System.out.println("ListOfNumbers error reading input file: " + inputFile);
        }

        // Write out ints
        for (Integer intVal : intList) {
            System.out.println("Next Val: " + intVal);
        }
    }

    // NOTE: alternative impl using RandomAccessFile
    /*
    readList assumes a file containing *numeric* chars and reads a line at a time.
     */

    public void readList(String fileName) {
        Vector<Integer> vector = new Vector<>();

        String line;
        try {
            RandomAccessFile raf = new RandomAccessFile(fileName, "r");
            while ((line = raf.readLine()) != null) {
                Integer i = Integer.parseInt(line);     // requires entire line to be int chars
                System.out.println("readList raf: " + i);
                vector.addElement(i);
            }
        } catch (FileNotFoundException fnf) {
            System.err.println("File: " + fileName + " not found.");
        } catch (IOException io) {
            System.err.println(io);     // TODO: Hides stack trace. Use logger instead.
        }
        for (Integer i : vector)
            System.out.println("readList raf vector: " + i);
    }
}

