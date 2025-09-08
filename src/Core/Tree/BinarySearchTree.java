/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Tree;

import Core.Nodes.BinaryNode;

/**
 * Represents a Binary Search Tree (BST), a specialized type of
 * {@link BinaryTree} where each node follows the ordering property:
 * <ul>
 * <li>All nodes in the left subtree contain values smaller than the
 * parent.</li>
 * <li>All nodes in the right subtree contain values greater than the
 * parent.</li>
 * </ul>
 *
 * <p>
 * This class provides functionality for insertion, deletion, and retrieving the
 * minimum and maximum values in the tree.</p>
 *
 * @author Rashid
 */
public class BinarySearchTree extends BinaryTree {

    /**
     * Creates an empty Binary Search Tree.
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Creates a Binary Search Tree with the specified root.
     *
     * @param root the root node of the tree
     */
    public BinarySearchTree(BinaryNode root) {
        this.root = root;
    }

    /**
     * Inserts a new node with the given value into the BST.
     * <p>
     * If the tree is empty, a new root is created. If the value already exists,
     * it is not inserted.</p>
     *
     * @param data the value to insert into the tree
     */
    public void addNode(int data) {
        if (this.root == null) {
            System.out.println("Is imposible to add " + data + " to an empty tree, creating tree...");
            this.root = new BinaryNode(data);
        } else {
            this.root = recursiveInsertion(this.root, data);
        }
    }

    /**
     * Recursively inserts a new node with the given value into the BST.
     * <p>
     * Ensures that the Binary Search Tree (BST) property is preserved: left
     * child < parent < right child. Duplicate values are not allowed. </p>
     *
     * @param root the root of the current subtree.
     * @param data the value to be inserted.
     * @return the root of the updated subtree.
     */
    private BinaryNode recursiveInsertion(BinaryNode root, int data) {
        if (root == null) {

            return new BinaryNode(data);
        }
        if (data < root.data) {
            root.left = recursiveInsertion(root.left, data);
        } else if (data > root.data) {
            root.right = recursiveInsertion(root.right, data);
        } else {
            System.out.println("Is impossible to add repeated elements to a tree");
        }
        return root;
    }

    /**
     * Deletes a node with the given value from the BST.
     * <p>
     * If the tree is empty, nothing is deleted.</p>
     *
     * @param data the value of the node to delete
     */
    public void deleteNode(int data) {
        if (this.root == null) {
            System.out.println("The tree doesn't have anything to delete");
        } else {
            this.root = delete(this.root, data);
        }
    }

    /**
     * Recursively deletes a node with the given value from the BST.
     * <p>
     * Handles three cases:
     * <ul>
     * <li>Node has no children → it is removed directly.</li>
     * <li>Node has one child → child replaces the node.</li>
     * <li>Node has two children → in-order successor replaces the node.</li>
     * </ul>
     * </p>
     *
     * @param root the root of the current subtree.
     * @param data the value to be deleted.
     * @return the root of the updated subtree.
     */
    private BinaryNode delete(BinaryNode root, int data) {
        if (root == null) {
            return null;
        }

        if (data < root.data) {
            root.left = delete(root.left, data);
        } else if (data > root.data) {
            root.right = delete(root.right, data);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            BinaryNode successor = findMin(root.right);
            root.data = successor.data;
            root.right = delete(root.right, successor.data);
        }
        return root;
    }

    /**
     * Finds the node with the minimum value in the given subtree.
     *
     * @param node the subtree root
     * @return the node with the smallest value
     */
    protected BinaryNode findMin(BinaryNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * Prints the smallest value stored in the tree.
     * <p>
     * If the tree is empty, a message is shown instead.</p>
     */
    public void getSmallest() {
        if (this.root == null) {
            System.out.println("Null/Empty tree");
        } else {
            System.out.println("Smallest data in the tree is: " + findSmallest(this.root).data);
        }
    }

    /**
     * Prints the largest value stored in the tree.
     * <p>
     * If the tree is empty, a message is shown instead.</p>
     */
    public void getLargest() {
        if (this.root == null) {
            System.out.println("Null/Empty tree");
        } else {
            System.out.println("Largest data in the tree is: " + findLargest(this.root).data);
        }
    }

    /**
     * Finds the node with the smallest value in a given subtree.
     * <p>
     * Traverses the leftmost path of the tree to locate the minimum element.
     * </p>
     *
     * @param root the root of the subtree.
     * @return the node with the smallest value, or null if the subtree is
     * empty.
     */
    private BinaryNode findSmallest(BinaryNode root) {
        if (root == null) {
            return null;
        }
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    /**
     * Finds the node with the largest value in a given subtree.
     * <p>
     * Traverses the rightmost path of the tree to locate the maximum element.
     * </p>
     *
     * @param root the root of the subtree.
     * @return the node with the largest value, or null if the subtree is empty.
     */
    private BinaryNode findLargest(BinaryNode root) {
        if (root == null) {
            return null;
        }
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }
}
