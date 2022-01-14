package com.griffin.utility;

/* Application scope checked exception (not runtime exception).
Application classes and methods must declare and handle.
 */
public class AppException extends Exception {

    public int appValue;  // Exception related value
//    public AppException() {
//    }

    public AppException(String message, int value) {
        super(message);
        this.appValue = value;
    }

}
