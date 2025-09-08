/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Tree;

import Core.Nodes.NAryNode;

/**
 * Represents an N-ary tree structure where each node can have zero or more
 * children. Provides utilities for common tree operations such as insertion,
 * deletion, searching, traversals, and structural properties (height,
 * cardinality, degree, etc.).
 *
 * <p>
 * This implementation supports:</p>
 * <ul>
 * <li>Pre-order, in-order, post-order, and per-level traversals.</li>
 * <li>Insertion of new nodes under a given parent.</li>
 * <li>Searching for nodes by value.</li>
 * <li>Deletion of nodes by value.</li>
 * <li>Calculation of tree properties such as height, cardinality, degree, and
 * leaf count.</li>
 * </ul>
 *
 * <p>
 * All tree nodes are instances of {@link Core.Nodes.NAryNode}.</p>
 *
 * @author Rashid
 */
public class NAryTree {

    /**
     * The root node of the N-ary tree.
     */
    public NAryNode root;

    /**
     * Creates an empty N-ary tree.
     */
    public NAryTree() {
        this.root = null;
    }

    /**
     * Creates a new N-ary tree with the given root.
     *
     * @param root the root node of the tree
     */
    public NAryTree(NAryNode root) {
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
     * Calculates the height of the N-ary tree.
     * <p>
     * The height is defined as the length of the longest path from the root
     * node to any leaf node.
     * </p>
     *
     * @param root the root node of the N-ary tree or subtree.
     * @return the height of the tree; -1 if the root is null.
     */
    private int getHeight(NAryNode root) {
        if (root == null) {
            return -1;
        }
        if (root.children.isEmpty()) {
            return 0;
        }
        int maxHeight = 0;
        for (NAryNode hijo : root.children) {
            int h = getHeight(hijo);
            if (h > maxHeight) {
                maxHeight = h;
            }
        }
        return 1 + maxHeight;
    }

    /**
     * Performs a pre-order traversal of the tree and prints the nodes.
     */
    public void preOrderTraversal() {
        preOrderTraversal(this.root);
        System.out.println();
    }

    /**
     * Performs an in-order traversal of the tree and prints the nodes. In the
     * context of N-ary trees, this prints half the children before the node and
     * half after.
     */
    public void inOrderTraversal() {
        inOrderTraversal(this.root);
        System.out.println();
    }

    /**
     * Performs a post-order traversal of the tree and prints the nodes.
     */
    public void postOrderTraversal() {
        postOrderTraversal(this.root);
        System.out.println();
    }

    /**
     * Prints the nodes of the tree level by level.
     */
    public void perLevelsTraversal() {
        int height = getHeight(this.root);
        for (int i = 0; i <= height; i++) {
            System.out.println("Level " + i + ":");
            printGivenLevel(this.root, i, height);
            System.out.println();
        }
        System.out.println();
    }

    private void preOrderTraversal(NAryNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            for (int i = 0; i < root.children.size(); i++) {
                preOrderTraversal(root.children.get(i));
            }
        }
    }

    private void inOrderTraversal(NAryNode root) {
        if (root != null) {
            int half = root.children.size() / 2;
            for (int i = 0; i < half; i++) {
                inOrderTraversal(root.children.get(i));
            }
            System.out.print(root.data + " ");
            for (int i = half; i < root.children.size(); i++) {
                inOrderTraversal(root.children.get(i));
            }
        }
    }

    private void postOrderTraversal(NAryNode root) {
        if (root != null) {
            for (int i = 0; i < root.children.size(); i++) {
                postOrderTraversal(root.children.get(i));
            }
            System.out.print(root.data + " ");
        }
    }

    /**
     * Prints all nodes at a given level of the N-ary tree.
     * <p>
     * If the level does not exist, an error message is printed instead.
     * </p>
     *
     * @param root the root node of the N-ary tree or subtree.
     * @param level the level to print (0 = root).
     * @param height the height of the tree used to validate the requested
     * level.
     */
    public void printGivenLevel(NAryNode root, int level, int height) {
        if (level > height || level < 0) {
            System.out.println("Level does not exist");
            return;
        }
        if (root != null) {
            if (level == 0) {
                System.out.print(root.data + " ");
            } else {
                for (NAryNode child : root.children) {
                    printGivenLevel(child, level - 1, height);
                }
            }
        }
    }

    /**
     * Adds a new node with the given data as a child of the specified parent.
     * If the tree is empty, the new node becomes the root.
     *
     * @param parent the parent node under which the new node will be added
     * @param data the value for the new node
     */
    public void addNode(NAryNode parent, int data) {
        if (this.root == null) {
            System.out.println("Is impossible to add " + data + " to an empty tree, creating tree...");
            this.root = new NAryNode(data);
        } else {
            if (parent == null) {
                System.out.println("Can not insert, parent node is null.");
            } else {
                NAryNode newNode = new NAryNode(data);
                parent.addChildren(newNode);
            }
        }
    }

    /**
     * Searches for a node by its value and prints whether it exists.
     *
     * @param data the value to search for
     */
    public void searchNode(int data) {
        if (recursiveSearch(this.root, data) == null) {
            System.out.println("Node " + data + " doesn't exist");
        } else {
            System.out.println("Node " + data + " exists");
        }
    }

    /**
     * Recursively searches for a node containing the given value in the N-ary
     * tree.
     *
     * @param root the root node of the N-ary tree or subtree.
     * @param value the value to be searched.
     * @return the node containing the value, or null if not found.
     */
    private NAryNode recursiveSearch(NAryNode root, int value) {
        if (root == null) {
            return null;
        }
        if (root.data == value) {
            return root;
        }
        for (NAryNode child : root.children) {
            NAryNode result = recursiveSearch(child, value);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    /**
     * Deletes a node with the given value from the tree. If the root matches
     * the value, the tree becomes empty.
     *
     * @param parent the parent of the node to delete (can be null if deleting
     * root)
     * @param data the value of the node to delete
     * @param replaceWithChild unused parameter placeholder for future logic
     */
    public void deleteNode(NAryNode parent, int data, boolean replaceWithChild) {
        if (!deleteNode(parent, this.root, data)) {
            System.out.println("Element not found");
        }
    }

    /**
     * Recursively deletes a node with the specified data from the N-ary tree.
     * <p>
     * If the root itself matches the data and has no parent, the entire tree is
     * set to null.
     * </p>
     *
     * @param parent the parent of the current node.
     * @param root the current node being inspected.
     * @param data the value of the node to be deleted.
     * @return true if the node was deleted, false otherwise.
     */
    private boolean deleteNode(NAryNode parent, NAryNode root, int data) {
        if (root == null) {
            return false;
        }
        if (parent == null && root.data == data) {
            this.root = null;
            return true;
        }
        for (int i = 0; i < root.children.size(); i++) {
            NAryNode child = root.children.get(i);

            if (child.data == data) {
                root.children.remove(i);
                return true;
            } else {
                boolean deleted = deleteNode(root, child, data);
                if (deleted) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Prints the number of nodes in the tree (cardinality).
     */
    public void getCardinality() {
        System.out.println("The amount of nodes in the tree is: " + amountOfNodes(this.root));
    }

    private int amountOfNodes(NAryNode root) {
        if (root == null) {
            return 0;
        }
        int card = 1;
        for (int i = 0; i < root.children.size(); i++) {
            card += amountOfNodes(root.children.get(i));
        }
        return card;
    }

    /**
     * Prints the maximum degree of the tree (the highest number of children of
     * any node).
     */
    public void getGrade() {
        System.out.println("The degree of the tree is: " + maxGrade(this.root));
    }

    /**
     * Computes the maximum degree (number of children) among all nodes in the
     * N-ary tree.
     *
     * @param root the root node of the N-ary tree or subtree.
     * @return the maximum degree found in the tree.
     */
    private int maxGrade(NAryNode root) {
        if (root == null) {
            return 0;
        }
        int maxGrad = root.children.size();
        for (int i = 0; i < root.children.size(); i++) {
            maxGrad = Math.max(maxGrad, maxGrade(root.children.get(i)));
        }
        return maxGrad;
    }

    /**
     * Prints the number of leaf nodes (nodes without children) in the tree.
     */
    public void getAmountOfLeafs() {
        System.out.println("The amount of leaf nodes: " + amountOfLeafNodes(this.root));
    }

    /**
     * Counts the number of leaf nodes in the N-ary tree.
     * <p>
     * A leaf node is defined as a node without children.
     * </p>
     *
     * @param root the root node of the N-ary tree or subtree.
     * @return the total number of leaf nodes.
     */
    private int amountOfLeafNodes(NAryNode root) {
        if (root == null) {
            return 0;
        }
        if (root.children.isEmpty()) {
            return 1;
        }
        int amount = 0;
        for (int i = 0; i < root.children.size(); i++) {
            amount += amountOfLeafNodes(root.children.get(i));
        }
        return amount;
    }
}
