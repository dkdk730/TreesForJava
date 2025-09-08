/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Tree;

import Core.Nodes.BNode;

/**
 * Implementation of a B-Tree data structure.
 * <p>
 * A B-Tree is a self-balancing search tree that maintains sorted data and
 * allows searches, sequential access, insertions, and deletions in logarithmic
 * time. It is optimized for systems that read and write large blocks of data.
 * </p>
 *
 * @author Rashid
 */
public class BTree {

    /**
     * Root node of the B-Tree.
     */
    public BNode root;

    /**
     * The maximum number of children a node can have (tree order).
     */
    public int P;

    /**
     * Creates a new empty B-Tree of order {@code P}.
     *
     * @param P the order of the tree (maximum number of children per node).
     */
    public BTree(int P) {
        this.root = new BNode(P, true);
        this.P = P;
    }

    /**
     * Performs a preorder traversal of the B-Tree and prints the keys.
     * <p>
     * Preorder: Visit the node first, then recursively its children.
     * </p>
     */
    public void preOrderTraversal() {
        preOrderTraversal(this.root);
    }

    /**
     * Performs an inorder traversal of the B-Tree and prints the keys.
     * <p>
     * Inorder: Visit the left child, then the node, then the right child.
     * </p>
     */
    public void inOrderTraversal() {
        inOrderTraversal(this.root);
    }

    /**
     * Performs a postorder traversal of the B-Tree and prints the keys.
     * <p>
     * Postorder: Visit all children first, then the node itself.
     * </p>
     */
    public void postOrderTraversal() {
        postOrderTraversal(this.root);
    }

    private void preOrderTraversal(BNode root) {
        if (root != null) {
            System.out.print("[ ");
            for (int i = 0; i < root.n; i++) {
                System.out.print(root.keys[i] + " ");
            }
            System.out.print("] ");
            for (int j = 0; j <= root.n; j++) {
                preOrderTraversal(root.children[j]);
            }
        }
    }

    private void inOrderTraversal(BNode node) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < node.n; i++) {
            inOrderTraversal(node.children[i]);
            if (i == 0) {
                System.out.print("[ ");
                for (int k = 0; k < node.n; k++) {
                    System.out.print(node.keys[k] + " ");
                }
                System.out.print("] ");
            }
        }
        inOrderTraversal(node.children[node.n]);
    }

    private void postOrderTraversal(BNode root) {
        if (root != null) {
            for (int j = 0; j <= root.n; j++) {
                postOrderTraversal(root.children[j]);
            }
            System.out.print("[ ");
            for (int i = 0; i < root.n; i++) {
                System.out.print(root.keys[i] + " ");
            }
            System.out.print("] ");
        }
    }

    /**
     * Inserts a new key into the B-Tree.
     *
     * @param key the key to insert
     */
    public void insertKey(int key) {
        if (root == null) {
            root = new BNode(P, true);
            root.keys[0] = key;
            root.n = 1;
        } else {
            int maxKeys = P - 1;
            if (root.n == maxKeys) {
                BNode s = new BNode(P, false);
                s.children[0] = root;
                splitChild(s, 0, root);
                int i = 0;
                if (s.keys[0] < key) {
                    i++;
                }
                insertNonFull(s.children[i], key);
                root = s;
            } else {
                insertNonFull(root, key);
            }
        }
    }

    /**
     * Inserts a key into a node that is guaranteed not to be full.
     *
     * @param x the node that is not full
     * @param k the key to insert
     */
    private void insertNonFull(BNode x, int k) {
        int maxKeys = P - 1;
        int i = x.n - 1;

        if (x.leaf) {
            while (i >= 0 && k < x.keys[i]) {
                x.keys[i + 1] = x.keys[i];
                i--;
            }
            x.keys[i + 1] = k;
            x.n++;
        } else {
            while (i >= 0 && k < x.keys[i]) {
                i--;
            }
            i++;
            if (x.children[i].n == maxKeys) {
                splitChild(x, i, x.children[i]);
                if (k > x.keys[i]) {
                    i++;
                }
            }
            insertNonFull(x.children[i], k);
        }
    }

    /**
     * Splits a full child node of a parent into two nodes.
     * <p>
     * The median key of the full child is promoted to the parent, and the child
     * is divided into two smaller nodes.
     * </p>
     *
     * @param parent the parent node
     * @param index the index of the child to split
     * @param fullChild the full child node that needs to be split
     */
    private void splitChild(BNode parent, int index, BNode fullChild) {
        int maxKeys = P - 1;
        int mid = maxKeys / 2;
        int rightCount = maxKeys - mid - 1;

        BNode z = new BNode(P, fullChild.leaf);
        z.n = rightCount;
        for (int j = 0; j < rightCount; j++) {
            z.keys[j] = fullChild.keys[mid + 1 + j];
        }
        if (!fullChild.leaf) {
            for (int j = 0; j <= rightCount; j++) {
                z.children[j] = fullChild.children[mid + 1 + j];
            }
        }
        fullChild.n = mid;
        for (int j = parent.n; j >= index + 1; j--) {
            parent.children[j + 1] = parent.children[j];
        }
        parent.children[index + 1] = z;
        for (int j = parent.n - 1; j >= index; j--) {
            parent.keys[j + 1] = parent.keys[j];
        }
        parent.keys[index] = fullChild.keys[mid];
        parent.n++;
    }

    /**
     * Deletes a key from the B-Tree if it exists.
     *
     * @param k the key to delete
     */
    public void deleteKey(int k) {
        if (root == null) {
            System.out.println("The tree is empty, there is nothing to delete.");
            return;
        }
        deleteFromNode(root, k);
        if (root.n == 0) {
            if (root.leaf) {
                root = null;
            } else {
                root = root.children[0];
            }
        }
    }

    /**
     * Deletes a key from a given node in the B-Tree.
     * <p>
     * Handles the different cases of B-Tree deletion: - Deleting from a leaf
     * node. - Deleting from an internal node using predecessor/successor. -
     * Merging or borrowing keys to maintain B-Tree properties.
     * </p>
     *
     * @param node the node where the deletion should occur
     * @param k the key to delete
     */
    private void deleteFromNode(BNode node, int k) {
        int idx = findKey(node, k);

        int maxKeys = P - 1;
        int minKeys = (P - 1) / 2;
        if (idx < node.n && node.keys[idx] == k) {
            if (node.leaf) {
                for (int i = idx; i < node.n - 1; i++) {
                    node.keys[i] = node.keys[i + 1];
                }
                node.n--;
                return;
            } else {
                BNode leftChild = node.children[idx];
                BNode rightChild = node.children[idx + 1];
                if (leftChild.n > minKeys) {
                    int pred = getPredecessor(node, idx);
                    node.keys[idx] = pred;
                    deleteFromNode(leftChild, pred);
                } else if (rightChild.n > minKeys) {
                    int succ = getSuccessor(node, idx);
                    node.keys[idx] = succ;
                    deleteFromNode(rightChild, succ);
                } else {
                    merge(node, idx);
                    deleteFromNode(node.children[idx], k);
                }
                return;
            }
        } else {
            if (node.leaf) {
                System.out.println("The key " + k + " doesn't exist in the tree.");
                return;
            }
            boolean last = (idx == node.n);
            if (node.children[idx].n == minKeys) {
                fill(node, idx);
            }
            if (last && idx > node.n) {
                deleteFromNode(node.children[idx - 1], k);
            } else {
                deleteFromNode(node.children[idx], k);
            }
        }
    }

    /**
     * Finds the index of a key within a node.
     *
     * @param node the node to search
     * @param k the key to find
     * @return the index where the key is found or where it should be inserted
     */
    public int findKey(BNode node, int k) {
        int idx = 0;
        while (idx < node.n && node.keys[idx] < k) {
            idx++;
        }
        return idx;
    }

    /**
     * Retrieves the predecessor of a key in a given node.
     * <p>
     * The predecessor is the largest key in the left subtree.
     * </p>
     *
     * @param node the node containing the key
     * @param idx the index of the key
     * @return the predecessor key
     */
    private int getPredecessor(BNode node, int idx) {
        BNode cur = node.children[idx];
        while (!cur.leaf) {
            cur = cur.children[cur.n];
        }
        return cur.keys[cur.n - 1];
    }

    /**
     * Retrieves the successor of a key in a given node.
     * <p>
     * The successor is the smallest key in the right subtree.
     * </p>
     *
     * @param node the node containing the key
     * @param idx the index of the key
     * @return the successor key
     */
    private int getSuccessor(BNode node, int idx) {
        BNode cur = node.children[idx + 1];
        while (!cur.leaf) {
            cur = cur.children[0];
        }
        return cur.keys[0];
    }

    /**
     * Ensures that the child at index {@code idx} has at least the minimum
     * number of keys.
     * <p>
     * If the child has fewer than the minimum, it borrows a key from a sibling
     * or merges with a sibling.
     * </p>
     *
     * @param node the parent node
     * @param idx the index of the child
     */
    private void fill(BNode node, int idx) {
        int minKeys = (P - 1) / 2;
        if (idx != 0 && node.children[idx - 1].n > minKeys) {
            borrowFromPrev(node, idx);
        } else if (idx != node.n && node.children[idx + 1].n > minKeys) {
            borrowFromNext(node, idx);
        } else {
            if (idx != node.n) {
                merge(node, idx);
            } else {
                merge(node, idx - 1);
            }
        }
    }

    /**
     * Borrows a key from the left sibling of a child node.
     * <p>
     * The parent key moves down into the child, and the sibling's last key
     * moves up into the parent.
     * </p>
     *
     * @param node the parent node
     * @param idx the index of the child
     */
    private void borrowFromPrev(BNode node, int idx) {
        BNode child = node.children[idx];
        BNode sibling = node.children[idx - 1];
        for (int i = child.n - 1; i >= 0; i--) {
            child.keys[i + 1] = child.keys[i];
        }
        if (!child.leaf) {
            for (int i = child.n; i >= 0; i--) {
                child.children[i + 1] = child.children[i];
            }
        }
        child.keys[0] = node.keys[idx - 1];
        if (!child.leaf) {
            child.children[0] = sibling.children[sibling.n];
        }
        node.keys[idx - 1] = sibling.keys[sibling.n - 1];
        sibling.n--;
        child.n++;
    }

    /**
     * Borrows a key from the right sibling of a child node.
     * <p>
     * The parent key moves down into the child, and the sibling's first key
     * moves up into the parent.
     * </p>
     *
     * @param node the parent node
     * @param idx the index of the child
     */
    private void borrowFromNext(BNode node, int idx) {
        BNode child = node.children[idx];
        BNode sibling = node.children[idx + 1];
        child.keys[child.n] = node.keys[idx];
        if (!child.leaf) {
            child.children[child.n + 1] = sibling.children[0];
        }
        node.keys[idx] = sibling.keys[0];
        for (int i = 1; i < sibling.n; i++) {
            sibling.keys[i - 1] = sibling.keys[i];
        }
        if (!sibling.leaf) {
            for (int i = 1; i <= sibling.n; i++) {
                sibling.children[i - 1] = sibling.children[i];
            }
        }
        sibling.n--;
        child.n++;
    }

    /**
     * Merges a child with its right sibling.
     * <p>
     * The parent key at index {@code idx} is moved down into the child, and all
     * keys and children of the sibling are appended to the child.
     * </p>
     *
     * @param node the parent node
     * @param idx the index of the child to merge with its sibling
     */
    private void merge(BNode node, int idx) {
        BNode child = node.children[idx];
        BNode sibling = node.children[idx + 1];
        child.keys[child.n] = node.keys[idx];
        for (int i = 0; i < sibling.n; i++) {
            child.keys[child.n + 1 + i] = sibling.keys[i];
        }
        if (!child.leaf) {
            for (int i = 0; i <= sibling.n; i++) {
                child.children[child.n + 1 + i] = sibling.children[i];
            }
        }
        child.n = child.n + 1 + sibling.n;
        for (int i = idx + 1; i < node.n; i++) {
            node.keys[i - 1] = node.keys[i];
        }
        for (int i = idx + 2; i <= node.n; i++) {
            node.children[i - 1] = node.children[i];
        }
        node.n--;
    }
}
