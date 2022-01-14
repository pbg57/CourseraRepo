package com.griffin.generics.samples;


import com.griffin.utility.BTreeException;

public class BinarySearchTree {

    /*
    A Binary Tree consists of  nodes each with only zero, one, or two subtree nodes themselves.
    The left subtree of a node contains only nodes with keys of lesser value than the node’s key.
    The right subtree of a node contains only nodes with keys greater than the node’s key.
    The left and right subtree each must also be a binary search tree.
    There must be no duplicate nodes.
     */


    public enum Operation {
        FIND, INSERT
    }

    Node rootNode = null;   // BTree is scoped to this class instance

    static class Node {
        public int value;
        public Node left, right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.testRunner();
    }

    public static boolean locateKey(Node treeNode, Node newNode, Operation operation) throws BTreeException {

        /*
        A new key is always inserted at the leaf. We start searching a key from the root until we hit
        a leaf node. Once a leaf node is found, the new node is added as a child of the leaf node.
         */

        //System.out.println("insertKey adding key " + newNode.value + " to node " + treeNode.value);

        boolean retVal = false;     // True return value indicates FIND operation succeeded

        if (newNode.value == treeNode.value) {
            if (operation == Operation.FIND) {
                return true;
            } else if (operation == Operation.INSERT)
                throw new BTreeException("Key already exists in Binary Tree: ", newNode.value);
        }

        if (newNode.value < treeNode.value) {
            if (treeNode.left != null)
                retVal = locateKey(treeNode.left, newNode, operation);  // recurse to continue traversing the binary tree
            else
                treeNode.left = newNode;    // new Node added at leaf of binary tree
        } else {
            // newNode.value > treeNode.value; already have checked for equality
            if (treeNode.right != null)
                retVal = locateKey(treeNode.right, newNode, operation);  // recurse to continue traversing the binary tree
            else
                treeNode.right = newNode;   // new Node added at leaf of binary tree
        }

        return retVal;
    }


    public void testRunner() {

        // Build a tree. Negative test case: expect to only catch one application exception for
        // attempt to insert duplicate key.
        int DUPLICATE_KEY = 50;
        rootNode = new Node(DUPLICATE_KEY, null, null);

        // Run tests creating a new tree.
        try {
            // Add ascending even numbers
            for (int i = 0; i < 101; i += 2) {
                // Skip the pre-existing root node
                if (i != DUPLICATE_KEY)
                    locateKey(rootNode, new Node(i, null, null), Operation.INSERT);
            }

            // Add descending odd numbers
            for (int i = 99; i > 0; i -= 2) {
                locateKey(rootNode, new Node(i, null, null), Operation.INSERT);
            }
        } catch (BTreeException appException) {
            System.out.println(appException.toString() + appException.appValue);
            if (appException.appValue != DUPLICATE_KEY) {
                System.out.println("Test Error: received unexpected BTREE key insertion error");
                //LOG error to logging system.
            }
        }

        /*
        Attempt to add a pre-existing key
         */
        boolean foundDuplicate = false;
        try {
                      locateKey(rootNode, new Node(DUPLICATE_KEY, null, null), Operation.INSERT);
            }
        catch (BTreeException appException) {
//            System.out.println(appException.toString() + appException.appValue);
            if (appException.appValue != DUPLICATE_KEY) {
                System.out.println("Test Error: received unexpected BTREE key insertion error");
                //LOG error to logging system.
            } else
                // Expected duplicate found
            foundDuplicate = true;
        }
        if (!foundDuplicate)
            System.out.println("Test Error: did not find expected duplicate key insertion error");
            //LOG error to logging system.


        /*
        Repeat tests on the binary tree for the FIND operation
         */
        try {
            // Add ascending even numbers
            for (int i = 0; i < 101; i += 2) {
                if (!locateKey(rootNode, new Node(i, null, null), Operation.FIND)) {
                    throw new BTreeException("Test Error: Did not locate key ", i);
                }
            }

            // Add descending odd numbers
            for (int i = 99; i > 0; i -= 2) {
                if (!locateKey(rootNode, new Node(i, null, null), Operation.FIND)) {
                    throw new BTreeException("Test Error: Did not locate key ", i);
                }
            }
        } catch (BTreeException appException) {
            System.out.println("Test Error: received unexpected BTREE key FIND error for key: " + appException.appValue);
            //LOG error to logging system.
        }
    }
}

