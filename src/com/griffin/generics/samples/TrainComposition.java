package com.griffin.generics.samples;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class TrainComposition {

    Deque<Integer> wagonLL = new LinkedList<>();

    public void attachWagonFromLeft(int wagonId) {
        wagonLL.addFirst(new Integer(wagonId));
    }

    public void attachWagonFromRight(int wagonId) {
        wagonLL.addLast(new Integer(wagonId));
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