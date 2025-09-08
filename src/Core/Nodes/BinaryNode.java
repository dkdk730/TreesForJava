/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Nodes;

/**
 * Represents a node in a binary tree structure. Each node stores an integer
 * value, references to its left and right child, and a balance factor (commonly
 * used in AVL trees).
 *
 * @author Rashid
 */
public class BinaryNode {

    /**
     * The value stored in this node.
     */
    public int data;
    /**
     * Reference to the left child node.
     */
    public BinaryNode left;
    /**
     * Reference to the right child node.
     */
    public BinaryNode right;
    /**
     * Balance factor of the node (used in self-balancing trees like AVL).
     */
    public int balance;

    /**
     * Creates a binary node with no children.
     *
     * @param data The integer value to be stored in this node.
     */
    public BinaryNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.balance = balance;
    }

    /**
     * Creates a binary node with specified left and right children.
     *
     * @param data The integer value to be stored in this node.
     * @param left Reference to the left child node.
     * @param right Reference to the right child node.
     */
    public BinaryNode(int data, BinaryNode left, BinaryNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.balance = balance;
    }
}
