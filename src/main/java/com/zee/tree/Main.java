package com.zee.tree;

/**
 * @author Shahid Akhtar.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        BinarySearchTree<String> binarySearchTree = new BinarySearchTree<>();
        Tree<String> tree = binarySearchTree.insert("b").insert("a").insert("c");
        binarySearchTree.traverse();
        System.out.println(tree.getMax());
        binarySearchTree.delete("a");
        binarySearchTree.traverse();
        System.out.println(binarySearchTree.getMin());
    }

}