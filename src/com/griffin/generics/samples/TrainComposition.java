package com.griffin.generics.samples;

import java.util.Deque;
import java.util.LinkedList;

/*
Class to hold and manage a queue of train cars.
 */
public class TrainComposition {

    /*
    Deque is a Java.util double-ended queue implemented by LinkedList.
     */
    Deque<Integer> wagonLL = new LinkedList<>();

    public void attachWagonFromLeft(int wagonId) {
        wagonLL.addFirst(wagonId);
    }

    public void attachWagonFromRight(int wagonId) {
        wagonLL.addLast(wagonId);
    }

    public int detachWagonFromLeft() {
        return wagonLL.removeFirst();
    }

    public int detachWagonFromRight() {
        return wagonLL.removeLast();
    }

    public static void main(String[] args) {
        TrainComposition train = new TrainComposition();
        train.attachWagonFromLeft(7);
        train.attachWagonFromLeft(13);
        System.out.println(train.detachWagonFromRight()); // 7
        System.out.println(train.detachWagonFromLeft()); // 13
    }
}