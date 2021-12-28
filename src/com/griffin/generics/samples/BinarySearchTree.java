package com.griffin.generics.samples;


public class BinarySearchTree {
    public static boolean contains(Node root, int value) {

        Node searchNode = root;

        if (searchNode.left != null) {
            if (searchNode.left.value < value) {
                return true;
            }
        }
        if (searchNode.right != null) {
            if (searchNode.right.value >= value) {
                return true;
            }
        }
        // Recurse for each child node
        if (searchNode.left != null) {
            if (contains(searchNode.left, value)) {
                return true;
            }
        }
        if (searchNode.right != null) {
            if (contains(searchNode.right, value)) {
                return true;
            }
        }
        return false;
    }
    class Node {
        public int value;
        public Node left, right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String [] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.testRunner();
      }

    public void testRunner() {
        Node n1 = new Node(1, null, null);
        Node n3 = new Node(3, null, null);
        Node n2 = new Node(2, n1, n3);

        System.out.println(contains(n2, 3));

        Node n14 = new Node(3, null, null);
        Node n11 = new Node(3, n14, null);
        Node n13 = new Node(3, null, null);
        Node n12 = new Node(3, n11, n13);

        System.out.println(contains(n12, 3));

    }
}

