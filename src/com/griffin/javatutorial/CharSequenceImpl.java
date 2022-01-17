package com.griffin.javatutorial;

import java.net.InetAddress;
import java.sql.Time;
import java.util.Date;

/*
Write a class that implements the java.lang.CharSequence interface.
Use your impl of the abstract subsequence method to return a reversed sequence of chars.
 */
// Note that a class can only extend one other class, but may implement multiple interfaces
public  class CharSequenceImpl extends foo implements CharSequence, Marker1, Marker2 {
    private final char[] charSeq;

    public static void main(String[] args) {

        String testStr = "Select one of the sentences from this book to use as the data.";
        char [] testSeq = testStr.toCharArray();
        CharSequenceImpl charSequence = new CharSequenceImpl(testSeq);

        System.out.println("Initial string: " + charSequence);
        System.out.println("Length: " + charSequence.length());
        System.out.println("Char at index 5: " + charSequence.charAt(5));
        System.out.println("new seq from index 10 to 20 " + charSequence.subSequence(10, 20));
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
            // Construct StringBuilder with this CharSequence impl.
            StringBuilder strBuffer = new StringBuilder(this);
            strBuffer.reverse();
            strBuffer.getChars(start, end, newCharSeq, 0);

            return new CharSequenceImpl(newCharSeq);
        } else
            throw new IndexOutOfBoundsException("Invalid start/end: " + start + "/" + end);
    }
    @Override
    public String toString() {
//        return String.valueOf(this);
        StringBuilder retStr = new StringBuilder(this);
        return retStr.toString();

    }

    //Suppose you have written a time-server that periodically notifies its clients of the current
    // date and time. Write an interface the server could use to enforce a particular protocol on its clients.

    public interface DayAndTimeInterface {

        /*
        The client implements the DayAndTimeInterface and delivers their library to us.
        We use their library's impl of the notify methods to inform them of date and time.
         */
        InetAddress getClientAddress();
        InetAddress setClientAddress();         // Client address can be set by time-server
        void notifyClientDate(Date currentDate);
        void notifyClientTime(Time currentTime);
    }

}
