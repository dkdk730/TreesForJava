
![Logo](https://media.discordapp.net/attachments/827018467231268875/1414364818563010650/Logo.png?ex=68bf4d62&is=68bdfbe2&hm=a4c558ffbc88366c5b361e9b7a40faba4f3bc177161fcdd43d690f06366239e6&=&format=webp&quality=lossless&width=996&height=206)


# 🌳 Data Structures with Trees

This project provides a collection of **Java implementations of tree-based data structures**, designed both for learning purposes and as utilities that can be extended into larger projects.

## 📦 What's inside?

**Nodes:**

Core building blocks for all tree structures:
- **N-Ary Nodes** → Basis for **N-Ary Trees**, where each node can have any number of children.
- **Binary Nodes** → Basis for **General Binary Trees, Binary Search Trees (BSTs)**, and **AVL Trees**.
- **B-Nodes** → Special nodes designed for **B-Trees**, combining the flexibility of N-Ary Nodes with the ordering rules of Binary Trees.

**Trees:**

Fully implemented tree structures with methods for **traversals**, **insertion, deletion, searching**, and more:

**N-Ary Trees** → Nodes can branch into as many children as needed.

**Binary Trees** → Fundamental structure for representing hierarchical data.

**Binary Search Trees (BST)** → Maintain order, enabling efficient searches and insertions.

**AVL Trees (Self-Balancing BST)** → Automatically balance themselves to guarantee optimal search, insertion, and deletion times.

**B-Trees** → Used in databases and file systems to manage large amounts of sorted data efficiently.


## 🚀 Features
- Implemented in **pure Java**, with no external dependencies.
- Clean and extensible **OOP design**.
- Educational: great for students or developers who want to understand how trees work under the hood.
- Includes classic algorithms such as preorder, inorder, postorder, and level-order traversals.


## 💻 Code Examples

- **Binary Search Tree**
```java
BinarySearchTree BST = new BinarySearchTree(); 
// Insert values
BST.addNode(10);
BST.addNode(5);
BST.addNode(15);
BST.addNode(3);
BST.addNode(7);

// Traversals
System.out.println("Inorder Traversal (sorted):");
BST.inOrderTraversal();

System.out.println("\nPreorder Traversal:");
BST.preOrderTraversal();

System.out.println("\nPostorder Traversal:");
BST.postOrderTraversal();
```
Output:
```
Is imposible to add 10 to an empty tree, creating tree...
Inorder Traversal (sorted):
3 5 7 10 15 

Preorder Traversal:
10 5 3 7 15 

Postorder Traversal:
3 7 5 15 10 
```
- **Self-Balacing Tree**
```java
 SelfBalancingBST  AVL = new SelfBalancingBST();

// Insert values (tree balances itself automatically)
AVL.addNode(30);
AVL.addNode(20);
AVL.addNode(40);
AVL.addNode(10);
AVL.addNode(5) // triggers rotation

System.out.println("Inorder Traversal (balanced):");
AVL.inOrderTraversal();
```
Output:
```
Is imposible to add 30 to an empty tree, creating tree...
Inorder Traversal (balanced):
5 10 20 30 40
```
- **B-Trees**
```java
BTree BTree = new BTree(4); // Order 4 B-Tree

// Insert values
BTree.insertKey(10);
BTree.insertKey(20);
BTree.insertKey(5);
BTree.insertKey(6);
BTree.insertKey(12);

System.out.println("Traversal of B-Tree:");
BTree.preOrderTraversal();
```
Output:
```
Traversal of B-Tree:
[ 10 ] [ 5 6 ] [ 12 20 ]
```



## Roadmap

- ✅ Implement core data structures (N-Ary Trees, Binary Trees, BST, AVL, B-Trees).
- 🔍 Review the code to make optimizations regarding efficiency.
- 🧪 Debug the program with several extreme cases.
- 🛠️ Add more tools if needed.


## Lessons Learned

Working on this project was a great challenge, especially when it came to debugging and ensuring that all subprograms were as **robust** and **efficient** as possible.  
Throughout the process, I gained a deeper understanding of **tree data structures** and the **logic behind their algorithms**.  

I also learned how to make design decisions when creating algorithms and defining rules, which helped me improve both my **problem-solving skills** and my ability to write **clean, maintainable code**.    

## Feedback

I’d love to hear your thoughts, suggestions, or ideas to improve this project.  
Feel free to reach out to me at **rashosubs@gmail.com** or open an issue in the repository.


## Author

- [@RasHOSub5](https://github.com/RasHOSub5)

