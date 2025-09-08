/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Nodes;

/**
 * Represents a node of a B-Tree structure. A B-Tree node can store multiple
 * keys and references to children, depending on the defined order (P) of the
 * B-Tree.
 *
 * <p>
 * Each node contains an array of keys, an array of children, the current number
 * of stored keys, and a flag to indicate if the node is a leaf.</p>
 *
 * @author Rashid
 */
public class BNode {

    /**
     * Array of keys stored in this node. Maximum size is P - 1.
     */
    public int[] keys;
    /**
     * Array of references to child nodes. Maximum size is P.
     */
    public BNode[] children;
    /**
     * Current number of keys in this node.
     */
    public int n;
    /**
     * Indicates if this node is a leaf (true) or not (false).
     */
    public boolean leaf;

    /**
     * Creates a new B-Tree node.
     *
     * @param P The order of the B-Tree. Determines maximum keys (P - 1) and
     * maximum children (P).
     * @param leaf True if this node is a leaf, false otherwise.
     */
    public BNode(int P, boolean leaf) {
        this.keys = new int[P - 1];
        this.children = new BNode[P];
        this.n = 0;
        this.leaf = leaf;
    }
}
