package com.griffin.generics.samples;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        System.out.println("Raw type 1");
        ArrayList list = new ArrayList(10);
        for (int i = 0; i < 10; i++) {
            list.add(new String(Integer.toString(i)));
        }
	// Raw type. Casting required.
        for (Object o : list) {
            String value = (String) o;
            System.out.println(value);
        }

        // Generic type. No casting required
        System.out.println("Generic type 1");
        ArrayList<String> genericList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            genericList.add(new String(Integer.toString(i)));
        }

        for (String listString : genericList) {
            System.out.println(listString);
        }

        // Usage of generic class
        System.out.println("Usage of generic class");
        Box<Integer> box = new Box<>();
        box.setBox(5);
        Integer boxInt = box.getBox();
        System.out.println(box.getBox());

        Box<String> boxString = new Box<String>();
        boxString.setBox("Box String");
        String boxStringBox = boxString.getBox();
        System.out.println(boxStringBox);

        // Usage of generic class
        System.out.println("Usage of generic class multiple types");
        SampleMap <String, Integer> sampleMap;
        sampleMap = new SampleMap<>();
        sampleMap.set("One", 1);
        System.out.println("SampleMap(String, Integer: " + sampleMap.get("One"));

        // Usage of inheritance in generic class
        System.out.println("Usage of generic class and inheritance of Number base class");
        NumberClass<Number> numberClass = new NumberClass<>(Integer.valueOf(10));
        System.out.println("Inherited Integer value of : "+numberClass.getNumber());
        NumberClass<Number> numberClass1 = new NumberClass<>(Double.valueOf(100.0));
        System.out.println("Inherited Integer value of : "+numberClass1.getNumber());

        // Usage of Unbounded Wildcards in Generics
        System.out.println("Usage of Unbounded Wildcards");
        List<String> stringList = Arrays.asList("One", "Two");
        List<Integer> integerList = Arrays.asList(3,4);
        printUnbounded(stringList);
        printUnbounded(integerList);

        // Usage of inheritance in generic classes
        // Upper Bounded Wildcards
        // Upper bounded wildcard restricts the unknown type to be a specific type or a subtype of that type
        ArrayList<? extends Car> carList;
        ArrayList<SportsCar> sportsCarList = new ArrayList<>();
        sportsCarList.add(new SportsCar("RX7"));
        ArrayList<Car> baseCarList = new ArrayList<>();
        baseCarList.add(new Car());
        baseCarList.add(new SportsCar("Allowed"));  // SportsCar is a Car
//        baseCarList.add((Car)new Vehicle());  // Will cause runtime CCE



        carList = sportsCarList;
        for (Car car : carList) {
            System.out.println("CarList iteration: " + car.toString());
        }
        carList = baseCarList;
        for (Car car : carList) {
            System.out.println("BaseCarList iteration: " + car.toString());
        }

        // Lower bounded Wildcards
        // A lower bounded wildcard restricts the unknown type to be a specific type or a super type of that type.
        // ??? Not sure why SportsCar is allowed here??
        List<? super Car> superCarList = Arrays.asList(new Vehicle(), new Car(), new SportsCar("Ferrari"));
//        superCarList.add(new SportsCar("not allowed"));   // Note: causes runtime Unsupported Operation Exception


        for (Object car:  superCarList) {
            System.out.println("superVehicleList iteration: " + car.toString() + " class: " + car.getClass());
        }

        // Note: Type Erasure
        // To implement generics, the Java compiler applies type erasure to:
        // Replace all type parameters in generic types with their bounds or Object if the type parameters are unbounded. The produced bytecode, therefore, contains only ordinary classes, interfaces, and methods.
        //Insert type casts if necessary to preserve type safety.
        //Generate bridge methods to preserve polymorphism in extended generic types.
        //Type erasure ensures that no new classes are created for parameterized types; consequently, generics incur no runtime overhead.

        // How to use streams to convert a String [] args into a List
        int [] nums = {1, 2, 3};
        List<Integer> inpList = Arrays.stream(nums).boxed().collect(Collectors.toList());

        String [] strings = {"one", "two", "three"};
        List<String> strList = Arrays.stream(strings).toList();
    }


// Generic inner Class
    static class Box<T> {

    private T boxObject;

    public T getBox() {
        return boxObject;
        }

    public void setBox(T input) {
        boxObject = input;
        }
    }

    // Generic class multiple type params
    static class SampleMap<K,V> {
        private K key;
        private V value;
        private final HashMap<K,V> map = new HashMap<>();

         public SampleMap() {

        };

        public void set(K key, V value) {
            map.put(key, value);
        }
        public V get(K key){
            return map.get(key);
        }
    }

    // Generic class and inheritance
    static class NumberClass <Number> {
        private Number aNumber;
        public NumberClass(Number number) {
            this.aNumber = number;
        }
        public void setNumber(Number number) {
            this.aNumber = number;
        }
        public Number getNumber() {
            return aNumber;
        }
    }

    // Generic class and wildcard inheritance examples
    static class Vehicle {
    }
    static class Car extends Vehicle {
        String carType = "Unassigned";
        @Override
        public String toString() {
            return carType;
        }
    }

    static class SportsCar extends Car {
        public SportsCar(String type) {
            super.carType = type;
        }
    }

    public static void printUnbounded(List<?> unboundedList) {
        for (Object elem : unboundedList) {
//            unboundedList.add(Integer.valueOf(3));   // Cannot add a raw type
//            unboundedList.add(new Object());         // Cannot even add Object to List of unknown type
            System.out.println(elem);
        }
    }

} // End class Main

