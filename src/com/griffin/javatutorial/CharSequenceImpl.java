package com.griffin.javatutorial;

import java.net.InetAddress;
import java.sql.Time;
import java.util.Collections;
import java.util.Date;

// Note that a class can only extend one other class, but may implement multiple interfaces

public  class CharSequenceImpl extends foo implements CharSequence, Marker1, Marker2 {
    private char[] charSeq = null;

    public static void main(String[] args) {

        String testStr = new String(("Select one of the sentences from this book to use as the data."));
        char [] testSeq = testStr.toCharArray();
        CharSequenceImpl charSequence = new CharSequenceImpl(testSeq);

        System.out.println("Initial string: " + charSequence.toString());
        System.out.println("Length: " + charSequence.length());
        System.out.println("Char at index 5: " + charSequence.charAt(5));
        System.out.println("new seq from index 10 to 20 " + charSequence.subSequence(10, 20).toString());
    }

    public CharSequenceImpl(char[] charSeq) {
        this.charSeq = charSeq;
    }

    @Override
    public int length() {
        return charSeq.length;
    }

    @Override
    public char charAt(int index) {
        if (index < charSeq.length)
            return charSeq[index];
        else
            throw new IndexOutOfBoundsException("Invalid index: " + index );
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        if ((start >= 0) && (start <= end) && (end < charSeq.length)) {
            int newLength = end - start;

            // Form a reverse ordering of chars
            char[] newCharSeq = new char[newLength];
            StringBuffer strBuffer = new StringBuffer(this);
            strBuffer.reverse();
            strBuffer.getChars(start, end, newCharSeq, 0);

            return new CharSequenceImpl(newCharSeq);
        } else
            throw new IndexOutOfBoundsException("Invalid start/end: " + start + "/" + end);
    }
    @Override
    public String toString() {
        StringBuffer strBuffer = new StringBuffer(this);
        return strBuffer.toString();

    }

    //Suppose you have written a time server that periodically notifies its clients of the current
    // date and time. Write an interface the server could use to enforce a particular protocol on its clients.

    public interface DayAndTimeInterface {

        InetAddress getClientAddress();
        void notifyClientDate(Date currentDate);
        void notifyClientTime(Time currentTime);
    }

}
