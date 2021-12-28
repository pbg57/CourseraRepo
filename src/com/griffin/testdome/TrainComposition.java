package com.griffin.testdome;

import java.util.Deque;
import java.util.LinkedList;

public class TrainComposition {

    private Deque<Integer> deque = new LinkedList<>();

    public void attachWagonFromLeft(int wagonId) {
        Integer intInput = new Integer(wagonId);
        deque.addFirst(intInput);
    }

    public void attachWagonFromRight(int wagonId) {
        deque.addLast(new Integer(wagonId));

    }

    public int detachWagonFromLeft() {
        return deque.removeFirst().intValue();

    }

    public int detachWagonFromRight() {
        return deque.removeLast().intValue();
    }

    public static void main(String[] args) {
        TrainComposition train = new TrainComposition();
        train.attachWagonFromLeft(7);
        train.attachWagonFromLeft(13);
        System.out.println(train.detachWagonFromRight()); // 7 
        System.out.println(train.detachWagonFromLeft()); // 13
    }
}