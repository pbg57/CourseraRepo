package com.griffin.utility;

/* Application scope checked exception (not runtime exception).
Application classes and methods must declare and handle.
 */
public class AppException extends Exception {

    public AppException(String message) {
        super(message);
    }

}
