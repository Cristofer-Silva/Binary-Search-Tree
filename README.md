# Binary Search Tree Implementation

A generic Java implementation of a Binary Search Tree (BST) that supports standard operations and tree rotations.

## Overview

This project provides a robust `BinarySearchTree` class that implements the `SortedCollection` interface. It maps values to nodes in a hierarchical structure, allowing for efficient searching, insertion, and traversal of data.

## Features

- **Generic Implementation**: Supports any data type that implements `Comparable<T>`.
- **Core Operations**: `insert`, `contains`, `size`, `isEmpty`, and `clear`.
- **Rotations**: Includes a `BSTRotation` class to demonstrate and test tree rotation logic (left and right rotations).
- **Node Structure**: deeply linked `BinaryNode` class with parent and child references.

## File Structure

- `BinarySearchTree.java`: Main implementation of the BST logic.
- `BinaryNode.java`: Node class representing elements in the tree.
- `SortedCollection.java`: Interface defining the required operations.
- `BSTRotation.java`: Extension of the BST to handle and test tree rotations.

## Usage

```java
BinarySearchTree<Integer> bst = new BinarySearchTree<>();
bst.insert(10);
bst.insert(5);
bst.insert(15);

System.out.println(bst.contains(10)); // true
System.out.println(bst.size());       // 3
```

## Running Tests

To run the rotation tests included in `BSTRotation.java`:

```bash
javac BSTRotation.java BinarySearchTree.java BinaryNode.java SortedCollection.java
java BSTRotation
```

## License

This project is open-source and available under the MIT License.
