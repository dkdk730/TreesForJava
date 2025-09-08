/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Nodes;

import java.util.ArrayList;

/**
 * Represents a node in an N-ary tree structure. Each node contains an integer
 * value (data) and a list of children. An N-ary node can have zero or more
 * child nodes.
 *
 * @author Rashid
 */
public class NAryNode {

    /**
     * The value stored in this node.
     */
    public int data;
    /**
     * The list of children of this node.
     */
    public ArrayList<NAryNode> children;

    /**
     * Creates a new N-ary node with no children.
     *
     * @param data The integer value to be stored in this node.
     */
    public NAryNode(int data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    /**
     * Creates a new N-ary node with the specified children.
     *
     * @param data The integer value to be stored in this node.
     * @param children The list of child nodes to be linked with this node.
     */
    public NAryNode(int data, ArrayList<NAryNode> children) {
        this.data = data;
        this.children = children;
    }

    /**
     * Adds a new child to this node's list of children.
     *
     * @param children The child node to be added.
     */
    public void addChildren(NAryNode children) {
        this.children.add(children);
    }
}
