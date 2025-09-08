/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Tree;

import Core.Nodes.BinaryNode;
import java.lang.Math;
import java.util.ArrayList;

/**
 * Represents a general binary tree with utility methods for traversals, search,
 * structural verification, and various operations.
 *
 * <p>
 * This implementation is not restricted to BST rules, but it includes
 * verification methods to check if the tree is a {@code BST} or
 * {@code AVL}.</p>
 *
 * <p>
 * Supported features include:</p>
 * <ul>
 * <li>Height calculation.</li>
 * <li>Pre-order, in-order, post-order, and per-level traversals.</li>
 * <li>Search for nodes by value.</li>
 * <li>Tree comparison: check if two trees are identical (twins) or structurally
 * similar.</li>
 * <li>Summation of all elements.</li>
 * <li>Check if the tree is balanced (but not necessarily AVL).</li>
 * <li>Save all nodes of a given level into a list.</li>
 * <li>Cardinality (number of nodes in the tree).</li>
 * <li>Find the smallest and largest node values.</li>
 * <li>Invert the children of a given node.</li>
 * <li>Verify special properties: maximum tree, BST, and AVL.</li>
 * </ul>
 *
 * <p>
 * All nodes are instances of {@link Core.Nodes.BinaryNode}.</p>
 *
 * @author Rashid
 */
public class BinaryTree {

    /**
     * The root node of the binary tree.
     */
    public BinaryNode root;

    /**
     * Creates an empty binary tree.
     */
    public BinaryTree() {
        this.root = null;
    }

    /**
     * Creates a binary tree with the specified root node.
     *
     * @param root the root node of the tree
     */
    public BinaryTree(BinaryNode root) {
        this.root = root;
    }

    /**
     * Prints the height of the tree. The height of an empty tree is 0.
     */
    public void getHeight() {
        if (this.root == null) {
            System.out.println("The height of the tree is: 0");
        } else {
            System.out.println("The height of the tree is: " + getHeight(this.root));
        }
    }

    /**
     * Calculates the height of the binary tree.
     * <p>
     * The height is defined as the number of edges on the longest path from the
     * root to a leaf. An empty tree has height -1.
     * </p>
     *
     * @param root the root node of the binary tree or subtree.
     * @return the height of the tree, or -1 if the root is null.
     */
    protected int getHeight(BinaryNode root) {
        if (root == null) {
            return -1;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    /**
     * Performs a pre-order traversal (root → left → right).
     */
    public void preOrderTraversal() {
        preOrderTraversal(this.root);
        System.out.println();
    }

    /**
     * Performs an in-order traversal (left → root → right).
     */
    public void inOrderTraversal() {
        inOrderTraversal(this.root);
        System.out.println();
    }

    /**
     * Performs a post-order traversal (left → right → root).
     */
    public void postOrderTraversal() {
        postOrderTraversal(this.root);
        System.out.println();
    }

    /**
     * Prints all tree levels from root to leaves.
     */
    public void perLevelsTraversal() {
        int height = getHeight(this.root);
        for (int i = 0; i <= height; i++) {
            printGivenLevel(this.root, i, height);
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Prints only a specific level of the tree.
     *
     * @param level the level to print
     */
    public void printGivenLevel(int level) {
        int height = getHeight(this.root);
        printGivenLevel(this.root, level, height);
    }

    private void preOrderTraversal(BinaryNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    private void inOrderTraversal(BinaryNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.data + " ");
            inOrderTraversal(root.right);
        }
    }

    private void postOrderTraversal(BinaryNode root) {
        if (root != null) {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.print(root.data + " ");
        }
    }

    /**
     * Calculates the height of the binary tree.
     * <p>
     * The height is defined as the number of edges on the longest path from the
     * root to a leaf. An empty tree has height -1.
     * </p>
     *
     * @param root the root node of the binary tree or subtree.
     * @return the height of the tree, or -1 if the root is null.
     */
    private void printGivenLevel(BinaryNode root, int level, int height) {
        if (level > height || level < 0) {
            System.out.println("Level does not exist");
        } else {
            System.out.println("Level: " + level + ":");
            printLevelRecursive(root, level);
        }
    }

    /**
     * Helper method for recursively printing all nodes at a given level.
     *
     * @param root the current node being inspected.
     * @param level the level to print (0 = this node).
     */
    private void printLevelRecursive(BinaryNode root, int level) {
        if (root != null) {
            if (level == 0) {
                System.out.print(root.data + " ");
            } else {
                printLevelRecursive(root.left, level - 1);
                printLevelRecursive(root.right, level - 1);
            }
        }
    }

    /**
     * Searches for a node by value and prints if it exists.
     *
     * @param data the value to search for
     */
    public void searchNode(int data) {
        if (recursiveSearch(root, data)) {
            System.out.println("Node " + data + " exists");
        } else {
            System.out.println("Node " + data + " doesn't exists");
        }
    }

    /**
     * Recursively searches for a value in the binary tree.
     *
     * @param root the root node of the binary tree or subtree.
     * @param data the value to search for.
     * @return true if the value exists in the tree, false otherwise.
     */
    private boolean recursiveSearch(BinaryNode root, int data) {
        if (root == null) {
            return false;
        }
        if (root.data == data) {
            return true;
        }
        return (recursiveSearch(root.left, data) || recursiveSearch(root.right, data));
    }

    /**
     * Checks if this tree is identical (twin) to another tree.
     *
     * @param Tree the tree to compare with
     */
    public void isSameAs(BinaryTree Tree) {
        if (sameVerification(this.root, Tree.root)) {
            System.out.println("The trees are twins");
        } else {
            System.out.println("The trees are NOT twins");
        }
    }

    /**
     * Verifies whether two binary trees are identical in both structure and
     * values.
     *
     * @param rootA the root of the first binary tree.
     * @param rootB the root of the second binary tree.
     * @return true if both trees are identical, false otherwise.
     */
    private boolean sameVerification(BinaryNode rootA, BinaryNode rootB) {
        if (rootA == null && rootB == null) {
            return true;
        }
        if (rootA == null || rootB == null) {
            return false;
        }
        return (rootA.data == rootB.data) && sameVerification(rootA.left, rootB.left) && sameVerification(rootA.right, rootB.right);
    }

    /**
     * Checks if this tree is structurally similar to another tree. Data values
     * are ignored, only structure is compared.
     *
     * @param Tree the tree to compare with
     */
    public void isSimilarTo(BinaryTree Tree) {
        if (similarVerification(this.root, Tree.root)) {
            System.out.println("The trees are similar");
        } else {
            System.out.println("The trees are NOT similar");
        }
    }

    /**
     * Verifies whether two binary trees are structurally similar, regardless of
     * their node values.
     *
     * @param rootA the root of the first binary tree.
     * @param rootB the root of the second binary tree.
     * @return true if both trees are structurally similar, false otherwise.
     */
    private boolean similarVerification(BinaryNode rootA, BinaryNode rootB) {
        if (rootA == null && rootB == null) {
            return true;
        }
        if (rootA == null || rootB == null) {
            return false;
        }
        return similarVerification(rootA.left, rootB.left) && similarVerification(rootA.right, rootB.right);
    }

    /**
     * Prints the sum of all node values in the tree.
     */
    public void sumOfElements() {
        System.out.println("The sum of all the elemtens of the tree is: " + recursiveSum(this.root));
    }

    /**
     * Computes the sum of all node values in the binary tree.
     *
     * @param root the root node of the binary tree or subtree.
     * @return the sum of all node values.
     */
    private int recursiveSum(BinaryNode root) {
        if (root == null) {
            return 0;
        }
        return root.data + recursiveSum(root.left) + recursiveSum(root.right);
    }

    /**
     * Verifies if the tree is balanced.
     * <p>
     * <b>Note:</b> Balanced does not necessarily mean AVL.</p>
     *
     * @param Tree ignored parameter (kept for compatibility)
     */
    public void isBalanced(BinaryTree Tree) {
        if (recursiveBalanceCheck(this.root)) {
            System.out.println("The tree is balanced");
        } else {
            System.out.println("The tree is NOT balanced");
        }
    }

    /**
     * Checks whether the binary tree is balanced.
     * <p>
     * A tree is considered balanced if, for every node, the difference in
     * height between its left and right subtrees is no more than 1.
     * </p>
     *
     * @param root the root node of the binary tree or subtree.
     * @return true if the tree is balanced, false otherwise.
     */
    private boolean recursiveBalanceCheck(BinaryNode root) {
        if (root == null) {
            return true;
        }
        if (Math.abs(getHeight(root.left) - getHeight(root.right)) > 1) {
            return false;
        }
        return recursiveBalanceCheck(root.left) && recursiveBalanceCheck(root.right);
    }

    /**
     * Saves all nodes at a given level into the provided list.
     *
     * @param list the list where nodes will be stored
     * @param level the level to save
     */
    public void saveLevel(ArrayList<BinaryNode> list, int level) {
        if (level > getHeight(root) || level < 0) {
            System.out.println("Level does not exist");
        } else {
            saveGivenLevel(list, this.root, level);
            /* for (BinaryNode node : list) {
                System.out.print(node.data + " ");
            }
            System.out.println(); */ // Consider if you want to print the list
        }
    }

    /**
     * Collects all nodes at a given level of the binary tree and stores them in
     * a list.
     *
     * @param list the list to store the nodes.
     * @param root the root node of the binary tree or subtree.
     * @param level the target level (0 = root).
     * @return the list of nodes at the given level.
     */
    private ArrayList<BinaryNode> saveGivenLevel(ArrayList<BinaryNode> list, BinaryNode root, int level) {
        if (root != null) {
            if (level == 0) {
                list.add(root);
            } else {
                saveGivenLevel(list, root.left, level - 1);
                saveGivenLevel(list, root.right, level - 1);
            }
        }
        return list;
    }

    /**
     * Prints the total number of nodes in the tree.
     */
    public void getCardinality() {
        System.out.println("The amount of nodes in the tree is: " + amountOfNodes(this.root));
    }

    private int amountOfNodes(BinaryNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + amountOfNodes(root.left) + amountOfNodes(root.right);
    }

    /**
     * Prints the smallest value in the tree.
     */
    public void getSmallest() {
        if (this.root == null) {
            System.out.println("Null/Empty tree");
        } else {
            System.out.println("Smallest data in the tree is: " + findSmallest(this.root).data);
        }
    }

    /**
     * Prints the largest value in the tree.
     */
    public void getLargest() {
        if (this.root == null) {
            System.out.println("Null/Empty tree");
        } else {
            System.out.println("Largest data in the tree is: " + findLargest(this.root).data);
        }
    }

    /**
     * Finds the node with the smallest value in the binary tree.
     *
     * @param root the root node of the binary tree or subtree.
     * @return the node with the minimum value, or null if the tree is empty.
     */
    private BinaryNode findSmallest(BinaryNode root) {
        if (root == null) {
            return null;
        }
        BinaryNode minNode = root;
        BinaryNode leftMin = findSmallest(root.left);
        if (leftMin != null && leftMin.data < minNode.data) {
            minNode = leftMin;
        }
        BinaryNode rightMin = findSmallest(root.right);
        if (rightMin != null && rightMin.data < minNode.data) {
            minNode = rightMin;
        }
        return minNode;
    }

    /**
     * Finds the node with the largest value in the binary tree.
     *
     * @param root the root node of the binary tree or subtree.
     * @return the node with the maximum value, or null if the tree is empty.
     */
    private BinaryNode findLargest(BinaryNode root) {
        if (root == null) {
            return null;
        }
        BinaryNode maxNode = root;
        BinaryNode leftMax = findLargest(root.left);
        if (leftMax != null && leftMax.data > maxNode.data) {
            maxNode = leftMax;
        }
        BinaryNode rightMax = findLargest(root.right);
        if (rightMax != null && rightMax.data > maxNode.data) {
            maxNode = rightMax;
        }
        return maxNode;
    }

    /**
     * Swaps the left and right children of the given node.
     *
     * @param subRoot the node whose children will be swapped
     */
    public void invertChildrens(BinaryNode subRoot) {
        if (subRoot != null) {
            BinaryNode auxLeft = subRoot.left;
            subRoot.left = subRoot.right;
            subRoot.right = auxLeft;
        }
    }

    /**
     * Verifies if the tree satisfies the "maximum tree" property.
     */
    public void isMax() {
        if (verifyIfMax(this.root)) {
            System.out.println("The tree is maximum");
        } else {
            System.out.println("The tree is NOT maximum");
        }
    }

    /**
     * Verifies whether the binary tree satisfies the conditions of a max-binary
     * tree.
     * <p>
     * A max-binary tree requires every node to either have two children or be a
     * leaf (no single-child nodes).
     * </p>
     *
     * @param root the root node of the binary tree or subtree.
     * @return true if the tree is a valid max-binary tree, false otherwise.
     */
    private boolean verifyIfMax(BinaryNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.left != null && root.right != null) {
            return verifyIfMax(root.left) && verifyIfMax(root.right);
        }
        return false;
    }

    /**
     * Verifies if the tree is a Binary Search Tree (BST).
     */
    public void isBST() {
        if (verifyIfBST(this.root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("The tree is BST");
        } else {
            System.out.println("The tree is NOT BST");
        }
    }

    /**
     * Verifies whether the binary tree satisfies the properties of a Binary
     * Search Tree (BST).
     *
     * @param node the root node of the binary tree or subtree.
     * @param min the minimum allowed value for the current node.
     * @param max the maximum allowed value for the current node.
     * @return true if the tree is a valid BST, false otherwise.
     */
    private boolean verifyIfBST(BinaryNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        long val = node.data;
        if (val <= min || val >= max) {
            return false;
        }
        return verifyIfBST(node.left, min, val) && verifyIfBST(node.right, val, max);
    }

    /**
     * Verifies if the tree is an AVL tree (i.e. BST + balanced).
     */
    public void isAVL() {
        if (verifyIfBST(this.root, Long.MIN_VALUE, Long.MAX_VALUE) && recursiveBalanceCheck(this.root)) {
            System.out.println("The tree is AVL");
        } else {
            System.out.println("The tree is NOT AVL");
        }
    }
}
