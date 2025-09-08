/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Tree;

import Core.Nodes.BinaryNode;

/**
 * Represents a self-balancing binary search tree (AVL Tree implementation).
 * <p>
 * This tree maintains the height-balanced property, ensuring that the height
 * difference (balance factor) between left and right subtrees is at most 1.
 * This guarantees logarithmic time complexity for insertion, deletion, and
 * search.
 * </p>
 *
 * @author Rashid
 */
public class SelfBalancingBST extends BinarySearchTree {

    /**
     * Constructs an empty self-balancing binary search tree.
     */
    public SelfBalancingBST() {
        this.root = null;
    }

    /**
     * Constructs a self-balancing binary search tree with a given root node.
     *
     * @param root the root node of the tree
     */
    public SelfBalancingBST(BinaryNode root) {
        this.root = root;
    }

    /**
     * Computes the balance factor of a node.
     * <p>
     * Balance factor is defined as the height of the left subtree minus the
     * height of the right subtree.
     * </p>
     *
     * @param node the node to evaluate
     * @return the balance factor of the node, or 0 if the node is null
     */
    private int getBalance(BinaryNode node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * Performs a simple left rotation around the given node.
     *
     * @param node the unbalanced node
     * @return the new root after rotation
     */
    private BinaryNode SimpleLeftRotation(BinaryNode node) {
        BinaryNode aux = node.right;
        node.right = aux.left;
        aux.left = node;
        return aux;
    }

    /**
     * Performs a simple right rotation around the given node.
     *
     * @param node the unbalanced node
     * @return the new root after rotation
     */
    private BinaryNode SimpleRightRotation(BinaryNode node) {
        BinaryNode aux = node.left;
        node.left = aux.right;
        aux.right = node;
        return aux;
    }

    /**
     * Performs a double left-right rotation (Right rotation followed by Left
     * rotation).
     *
     * @param node the unbalanced node
     * @return the new root after rotation
     */
    private BinaryNode DoubleLeftRightRotation(BinaryNode node) {
        node.right = SimpleRightRotation(node.right);
        return SimpleLeftRotation(node);
    }

    /**
     * Performs a double right-left rotation (Left rotation followed by Right
     * rotation).
     *
     * @param node the unbalanced node
     * @return the new root after rotation
     */
    private BinaryNode DoubleRightLeftRotation(BinaryNode node) {
        node.left = SimpleLeftRotation(node.left);
        return SimpleRightRotation(node);
    }

    @Override
    /**
     * Inserts a new node into the AVL tree.
     * <p>
     * This method overrides the standard insertion from
     * {@link BinarySearchTree} using the balanced AVL insertion. After each
     * insertion, the tree automatically restructures itself with rotations if
     * necessary to maintain the AVL balance property.
     * </p>
     *
     * @param data integer value to be inserted into the tree.
     */
    public void addNode(int data) {
        this.root = insertNode(this.root, data);
    }

    /**
     * Inserts a new node into the AVL tree and rebalances if necessary.
     *
     * @param root the root of the current subtree
     * @param data the value to insert
     * @return the (possibly new) root of the subtree
     */
    private BinaryNode insertNode(BinaryNode root, int data) {
        if (root == null) {
            return new BinaryNode(data);
        }
        if (data < root.data) {
            root.left = insertNode(root.left, data);
        } else if (data > root.data) {
            root.right = insertNode(root.right, data);
        } else {
            System.out.println("Duplicated node, " + data + " doesn't get inserted");
            return root;
        }
        int balance = getBalance(root);
        if (balance > 1 && data < root.left.data) {
            return SimpleRightRotation(root);
        }
        if (balance < -1 && data > root.right.data) {
            return SimpleLeftRotation(root);
        }
        if (balance > 1 && data > root.left.data) {
            root.left = SimpleLeftRotation(root.left);
            return SimpleRightRotation(root);
        }
        if (balance < -1 && data < root.right.data) {
            root.right = SimpleRightRotation(root.right);
            return SimpleLeftRotation(root);
        }
        return root;
    }

    @Override
    /**
     * Deletes a node from the AVL tree.
     * <p>
     * This method overrides the standard deletion from {@link BinarySearchTree}
     * using the balanced AVL deletion. After the deletion, the tree
     * automatically restructures itself with rotations if necessary to maintain
     * the AVL balance property.
     * </p>
     *
     * @param data integer value of the node to be removed from the tree.
     */
    public void deleteNode(int data) {
        this.root = deleteNode(this.root, data);
    }

    /**
     * Deletes a node from the AVL tree and rebalances if necessary.
     *
     * @param root the root of the current subtree
     * @param data the value to delete
     * @return the (possibly new) root of the subtree
     */
    private BinaryNode deleteNode(BinaryNode root, int data) {
        if (root == null) {
            return null;
        }
        if (data < root.data) {
            root.left = deleteNode(root.left, data);
        } else if (data > root.data) {
            root.right = deleteNode(root.right, data);
        } else {
            if (root.left == null || root.right == null) {
                root = (root.left != null) ? root.left : root.right;
            } else {
                BinaryNode succesor = findMin(root.right);
                root.data = succesor.data;
                root.right = deleteNode(root.right, succesor.data);
            }
        }
        if (root == null) {
            return null;
        }
        int balance = getBalance(root);
        if (balance > 1 && getBalance(root.left) >= 0) {
            return SimpleRightRotation(root);
        }
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = SimpleLeftRotation(root.left);
            return SimpleRightRotation(root);
        }
        if (balance < -1 && getBalance(root.right) <= 0) {
            return SimpleLeftRotation(root);
        }
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = SimpleRightRotation(root.right);
            return SimpleLeftRotation(root);
        }
        return root;
    }
}
