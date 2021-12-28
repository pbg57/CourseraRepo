package com.griffin.testdome;

import java.util.*;

public class RoutePlanner {

    public static boolean routeExists(int fromRow, int fromColumn, int toRow, int toColumn,
                                      boolean[][] mapMatrix) {
        // Check if beginning and ending points have roads (are true)?

        // First, increment Y axis while true (lookDown) and for each road found, increment
        // X axis while true (lookRight).

        int currFromRow = fromRow;
        int currFromCol = fromColumn;
        boolean retVal = false;

        for (int row = currFromRow; row <= toRow; row++) {
            for (int col = currFromCol; col <=toColumn; col++) {
                if (lookDown(row, col, mapMatrix)) {
                    if ((row == toRow) && (col == toColumn)) {
                        return true;
                    } else
                        // recurse
                        retVal = routeExists(row+1, col, toRow, toColumn, mapMatrix);
                }
            }
        }
        return retVal;
    }


    public static boolean lookUp(int x, int y, boolean[][] mapMatrix) {
        if (mapMatrix.length >= (x-1)) {
            return (mapMatrix[x-1][y]);
        }
        else
            return false;
    }

    public static boolean lookDown(int x, int y, boolean[][] mapMatrix) {
        if (mapMatrix.length > (x+1)) {
            return (mapMatrix[x+1][y]);
        }
        else
            return false;
    }

    public static boolean lookLeft(int x, int y, boolean[][] mapMatrix) {
        if (mapMatrix[x].length > (y-1)) {
            return (mapMatrix[x][y-1]);
        }
        else
            return false;
    }

    public static boolean lookRight(int x, int y, boolean[][] mapMatrix) {
        if (mapMatrix[x].length >= (y+1)) {
            return (mapMatrix[x+1][y+1]);
        }
        else
            return false;
    }



    public static void main(String[] args) {
        boolean[][] mapMatrix = {
                {true,  false, false},
                {true,  true,  false},
                {false, true,  true}
        };

        System.out.println("RouteExists?  " + routeExists(0, 0, 2, 2, mapMatrix));
    }
}