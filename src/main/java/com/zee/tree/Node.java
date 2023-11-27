package com.zee.tree;

import lombok.Data;
import lombok.NonNull;

/**
 * @author Shahid Akhtar.
 */
@Data
public class Node<T extends Comparable<T>> {

    @NonNull T data;
    private int height = 1;
    private Node<T> leftChild;
    private Node<T> rightChild;
}
