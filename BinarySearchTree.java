import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements SortedCollection<T> {

    protected BinaryNode<T> root = null; // reference to root node of tree, null when empty
    protected int numElements = 0; // number of elements in the tree

    /**
     * Default constructor.
     */
    public BinarySearchTree() {
        this.root = null;
        this.numElements = 0;
    }

    /**
     * Inserts a new data value into the sorted collection.
     * @param data the new value being inserted
     * @throws NullPointerException if data argument is null, we do not allow
     * null values to be stored within a SortedCollection
     */
    @Override
    public void insert(T data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException("Cannot insert null data into BinarySearchTree");
        }
        BinaryNode<T> newNode = new BinaryNode<>(data);
        if (this.root == null) {
            this.root = newNode;
            this.numElements++;
        } else {
            insertHelper(newNode, this.root);
        }
    }

    /**
     * Recursive helper method to insert a new node into the tree.
     * @param newNode the node to insert
     * @param subTree the root of the subtree to insert into
     */
    protected void insertHelper(BinaryNode<T> newNode, BinaryNode<T> subTree) {
        int comparison = newNode.getData().compareTo(subTree.getData());
        if (comparison <= 0) { // Go left for less than or equal
            if (subTree.getLeft() == null) {
                subTree.setLeft(newNode);
                newNode.setUp(subTree);
                this.numElements++;
            } else {
                insertHelper(newNode, subTree.getLeft());
            }
        } else { // Go right for greater than
            if (subTree.getRight() == null) {
                subTree.setRight(newNode);
                newNode.setUp(subTree);
                this.numElements++;
            } else {
                insertHelper(newNode, subTree.getRight());
            }
        }
    }

    /**
     * Check whether data is stored in the tree.
     * @param find the value to check for in the collection
     * @return true if the collection contains data one or more times,
     * and false otherwise
     */
    @Override
    public boolean contains(Comparable<T> find) {
        if (find == null) {
             return false; // Or throw NPE? The interface doesn't strictly say, but usually contains returns false for null.
             // However, the SortedCollection interface method signature in the placeholder had 'throws NullPointerException' although the interface file itself didn't have it in the throws clause of the method signature but checking the comments...
             // "throws NullPointerException if data argument is null" is for insert.
             // For contains: "@param find the value to check for".
             // Let's stick to safe convention: return false or handle gracefully.
             // Actually, if I look at potential existing tests or requirements...
             // The placeholder had `throws NullPointerException` on `contains`.
             // I will stick to the interface. The interface file `SortedCollection.java` does NOT declare `throws NullPointerException` for `contains`.
             // So I will just return false if find is null, or let it throw if it tries to compare?
             // `find.compareTo(...)` might replace this.
             // Let's assume standard behavior: return false if null.
        }
        return containsHelper(find, this.root);
    }
    
    protected boolean containsHelper(Comparable<T> find, BinaryNode<T> node) {
        if (node == null) {
            return false;
        }
        int comparison = find.compareTo(node.getData());
        if (comparison == 0) {
            return true;
        } else if (comparison < 0) {
            return containsHelper(find, node.getLeft());
        } else {
            return containsHelper(find, node.getRight());
        }
    }

    /**
     * Counts the number of values in the collection.
     * @return the number of values in the collection
     */
    @Override
    public int size() {
        return this.numElements;
    }

    /**
     * Checks if the collection is empty.
     * @return true if the collection contains 0 values, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.numElements == 0;
    }

    /**
     * Removes all values and duplicates from the collection.
     */
    @Override
    public void clear() {
        this.root = null;
        this.numElements = 0;
    }
}
