package com.griffin.generics.samples;

import java.util.*;

public class JdkTutorial2 {

    void testRunner() {
        /*
        Print a list of a generic input type in a random order without copying.
         */
        List<String> stringList = Arrays.asList("first", "second", "third", "fourth", "fifth");
        new RandomOrder().printRandom(stringList);
        List<Integer> intList = Arrays.asList(2, 5, 6, 3);
        new RandomOrder().printRandom(intList);

        /*
        Find duplicates in an input array utilizing a SortedSet and custom Comparator.
         */
        String[] stringArray = {"one", "Two", "three", "two"};
        new FindDupsSortedSet().printFindDupsSortedSet(stringArray);

        /*
        Write a method that takes a List<String> and applies String.trim to each element.
         */
        String[] stringTrimArray = {" leading whitespace", "trailing whitespace  "};
        stringTrim(Arrays.asList(stringTrimArray));
        trimSameList(Arrays.asList(stringTrimArray));

    }

    public static void main(String[] args) {

        JdkTutorial2 st = new JdkTutorial2();
        st.testRunner();
    }



    // Write a method that takes a List<String> and applies String.trim to each element.
    List<String> stringTrim(List<String> stringList) {

        System.out.println("stringTrim input: ");
        stringList.forEach(System.out::println);
        stringList.forEach(String::trim);   // Note that the stream content is not modified (trimmed):
        System.out.println("stringTrim output: ");
        stringList.forEach(System.out::println);

        List<String> retStringList = new ArrayList<>();
        for (String s : stringList) {
            retStringList.add(s.trim());
            System.out.format("\"%s\"%n", s);
        }
        System.out.println("retStringList output: ");
        retStringList.forEach(System.out::println);


        return retStringList;
    }

    private List<String> trimSameList(List<String> stringList) {
        // Use ListIterator to modify list elements

        // Before modify
        stringList.forEach(System.out::println);

        ListIterator<String> listIterator = stringList.listIterator();
        while (listIterator.hasNext()) {
            String nextString = listIterator.next();
            listIterator.set(nextString.trim());
        }
        // After modify
        stringList.forEach(System.out::println);

        return stringList;
    }

//    Write a program that prints its arguments in random order. Do not make a copy of the argument array.
//    Demonstrate how to print out the elements using both streams and the traditional enhanced for statement

    private static class RandomOrder {
        // LinkedHashSet Iterator will preserve insertion-order needed and Sets do not allow duplicate entries,
        // which is needed.
        final Set<Integer> visitedIndexes = new LinkedHashSet<>(); // predictable iteration order
        Integer randomInt;

        public <T> void printRandom(List<T> argList) {

            // Using the size of argList, generate bounded random int's until all argList entries have been printed
            // Use a LinkedHashSet, which maintains insertion order, to keep unique visited argList indexes

            int argListCount = argList.size();
            Random randomGen = new Random();

            System.out.println("RandomOrder processing argList" + argList);

            while (argListCount > 0) {
                randomInt = randomGen.nextInt(argList.size());
                if (!visitedIndexes.contains(randomInt)) {
                    visitedIndexes.add(randomInt);
                    --argListCount;
                }
            }

            // Print using enhanced For iteration
            for (Integer randomIndex : visitedIndexes) {
                System.out.println("RandomOrder LinkedHashSet found arg[" + randomIndex + "] " + argList.get(randomIndex).toString());
            }

            // Print using Streams
            Collections.shuffle(argList);
            argList.forEach(e -> System.out.format("%s ", e));
            System.out.println();

        }
    } // End inner class RandomOrder

    private static class FindDupsSortedSet {
        //
        //        Take the FindDups example and modify it to use a SortedSet instead of a Set.
        //        Specify a Comparator so that case is ignored when sorting and identifying set elements.
        //
        public void printFindDupsSortedSet(String[] args) {

            // Create case-insensitive Comparator with Anonymous class impl of abstract Comparator interface.
            // Note: Anonymous new Comparator<>() can be replaced by Lambda, if desired.
            final Comparator<String> IGNORE_CASE_ORDER = new Comparator<>() {
                public int compare(String s1, String s2) {
                    return s1.compareToIgnoreCase(s2);
                }
            };

            // The TreeSet elements are ordered using their natural ordering, or by a Comparator provided at
            // set creation time, depending on which constructor is used.
            SortedSet<String> noDupSet = new TreeSet<>(IGNORE_CASE_ORDER);

            noDupSet.addAll(Arrays.asList(args));

            System.out.println("\nNoDupTreeSet input:");
            //  Arrays.asList(args).stream().forEach(System.out::println);
            System.out.println(Arrays.toString(args));
            System.out.println("Found " + noDupSet.size() + " distinct words: " + noDupSet);
        }
    } // End inner class FindDupsSortedSet


       /*
    The following contains unused references. These are notes on Generics for private use.
     */

//    A generic class or interface is defined with the following format: name, type variables and body.
//    Class name<T1, T2, ..., Tn> { /* ... */ }

    public interface Pair<K, V> {
        V get(K key);

        void set(K key, V value);
    }

    public static class SampleGenericClass<T> {
        private T elem;   // instance var ref to generic class

        public List<T> sampleGenericMethod(List<T> elemList) {
            return null;
        }
    }

//    Generic methods are methods that introduce their own type parameters.
//    This is similar to declaring a generic type, but the type parameter's scope is
//    limited to the method where it is declared.
//    Static and non-static generic methods are allowed, as well as generic class constructors.
//    The syntax for a generic method includes:
//    Visibility modifier, list of type parameters inside angle brackets, return type, method name and args.

    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return false;
    }

    static <T> void printRandom(List<T> argList) {
    }

    public static <T extends Comparable<T>> T findMaximumElement(List<T> elemList, T[] elemArray, int begin,
                                                                 int end) {
        return null;
    }

    // Unbounded wildcards in methods. Method can only invoke Object class methods on unboundedList.
    public static void printUnbounded(List<?> unboundedList) {
        for (Object o : unboundedList) {
            Class<? extends Object> foo = o.getClass();
        }
    }

    // Upper/Lower bounded wildcards in variables.
    List<? extends Integer> upperExtendVar = new ArrayList<>();   // Upper bound: unknown type is Integer or a subtype of Integer
    List<? super Integer> lowerExtendVar = new ArrayList<>();   // Lower bound: unknown type is Integer or a super class of Integer

    // Anonymous Class usage:
    // An anonymous class in Java is a class not given a name and is both declared and instantiated in a single statement.
    // You should consider using an anonymous class whenever you need to create a class that will be instantiated only once.
    // Syntax: new interface-or-class-name() { class-body }
    // Within the class body, you must provide an implementation for each abstract method defined by the interface or abstract class.
    // An anonymous class cannot have a constructor. Thus, you cannot pass parameters to an anonymous class when you instantiate it.
    // An anonymous class can access any variables visible to the block within which the anonymous class is declared, including local variables.
    // An anonymous class can also access methods of the class that contains it.

    final Comparator<String> IGNORE_CASE_ORDER = new Comparator<>() {
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }
    };

    // Lambda equivalent of Anonymous class decl:
    final Comparator<String> IGNORE_CASE_ORDER2 = (s1, s2) -> s1.compareToIgnoreCase(s2);


    // Exercises:
    // Consider the four core interfaces, Set, List, Queue, and Map. For each of the following four assignments,
    // specify which of the four core interfaces is best-

    //Whimsical Toys Inc (WTI) needs to record the names of all its employees. Every month, an employee will be chosen
    // at random from these records to receive a free toy.
    // Answer: Use Set interface because no duplicate (employee) entries are desired.
    // Answer: Temporarily converting the Set to a List would allow Collections.shuffle(List l) to generate toy winner.

    //WTI has decided that each new product will be named after an employee but only first names will be used,
    // and each name will be used only once. Prepare a list of unique first names.
    // Answer: A Comparator<String>() class can be used to instantiate a Set with unique first names from the
    // master employee Set, and then converted to a List.

    //WTI decides that it only wants to use the most popular names for its toys.
    // Answer: Count up the number of employees who have each first name.
    // A Map using employee first name as a key can be used to tally a value/count.

    //WTI acquires season tickets for the local lacrosse team, to be shared by employees.
    // Create a waiting list for this popular sport.
    // Answer: Use a Queue to implement a FIFO ordering for ticket requests

} // End class JdkTutorial2


