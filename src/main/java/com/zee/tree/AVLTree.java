package com.zee.tree;

/**
 * @author Shahid Akhtar.
 */
public class AVLTree<T extends Comparable<T>> implements Tree<T>{

    private Node<T> root;
    @Override
    public Tree<T> insert(T data) {
        insert(data, root);
        return this;
    }

    private Node<T> insert(T data, Node<T> node) {
        if(node == null) return new Node<>(data);
        if(data.compareTo(node.getData()) < 0) {
            node.setLeftChild(insert(data, node.getLeftChild()));
        } else if(data.compareTo(node.getData()) > 0) {
            node.setRightChild(insert(data, node.getRightChild()));
        } else {
            return node;
        }
        updateHeight(node);
        return applyRotation(node);

    }

    @Override
    public void delete(T data) {
        delete(data, root);
    }

    private Node<T> delete(T data, Node<T> node) {
        if(node == null) return null;
        if(data.compareTo(node.getData()) < 0) {
            node.setLeftChild(delete(data, node.getLeftChild()));
        } else if(data.compareTo(node.getData()) > 0) {
            node.setRightChild(delete(data, node.getRightChild()));
        } else {
            if(node.getLeftChild() == null) return node.getRightChild();
            else if(node.getRightChild() == null) return node.getLeftChild();

            node.setData(getMax(node.getLeftChild()));
            node.setLeftChild(delete(data, node.getLeftChild()));
        }
        updateHeight(node);
        return applyRotation(node);
    }

    private Node<T> applyRotation(Node<T> node) {
        int balance = balance(node);
        if(balance > 1) {
            if(balance(node.getLeftChild()) < 0) {
                node.setLeftChild(rotateLeft(node.getLeftChild()));
            }
            return rotateRight(node);
        }
        if(balance < -1) {
            if(balance(node.getRightChild()) > 0) {
                node.setRightChild(rotateRight(node.getRightChild()));
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> leftNode = node.getLeftChild();
        Node<T> centreNode = leftNode.getRightChild();
        leftNode.setRightChild(node);
        node.setLeftChild(centreNode);
        updateHeight(node);
        updateHeight(leftNode);
        return leftNode;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> rightNode = node.getRightChild();
        Node<T> centreNode = rightNode.getLeftChild();
        rightNode.setLeftChild(node);
        node.setRightChild(centreNode);
        updateHeight(node);
        updateHeight(rightNode);
        return rightNode;
    }

    private int balance(Node<T> node) {
        return node == null ? 0 : height(node.getLeftChild()) - height(node.getRightChild());
    }

    private void updateHeight(Node<T> node) {
        int maxHeight = Math.max(height(
                node.getLeftChild()),
                height(node.getRightChild()));
        node.setHeight(maxHeight + 1);
    }

    private int height(Node<T> node) {
        return node != null ? node.getHeight() : 0;
    }

    private T getMax(Node<T> leftChild) {
        while(leftChild.getRightChild() != null) {
            return getMax(leftChild.getRightChild());
        }
        return leftChild.getData();
    }

    @Override
    public void traverse() {

    }

    @Override
    public T getMax() {
        if(isEmpty()) return null;
        Node<T> node = root;
        while(node.getRightChild() != null) {
            node = node.getRightChild();
        }
        return node.getData();
    }

    @Override
    public T getMin() {
        if(isEmpty()) return  null;
        Node<T> node = root;
        while(node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node.getData();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }
}
