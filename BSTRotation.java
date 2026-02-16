import java.util.LinkedList;

public class BSTRotation<T extends Comparable<T>> extends BinarySearchTree<T> {

    public BSTRotation() {
    }

    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a left child of the provided parent, this
     * method will perform a right rotation. When the provided child is a right
     * child of the provided parent, this method will perform a left rotation.
     *
     * @param child  is the node being rotated from child to parent position
     * @param parent is the node being rotated from parent to child position
     * @throws IllegalArgumentException if the parent or child are null, or not
     *                                  related
     */
    protected void rotate(BinaryNode<T> child, BinaryNode<T> parent) throws IllegalArgumentException {
        // TODO: Implement this method.

        if (child == null || parent == null) {
            return;
        }

        if (parent.getLeft() != child && parent.getRight() != child) {
            throw new IllegalArgumentException("Nodes are not related");
        }

        BinaryNode<T> grandParent = parent.getUp();

        if (child == parent.getLeft()) {
            // Right Rotation
            parent.setLeft(child.getRight());
            if (child.getRight() != null) {
                child.getRight().setUp(parent);
            }
            child.setRight(parent);
        } else {
            // Left Rotation
            parent.setRight(child.getLeft());
            if (child.getLeft() != null) {
                child.getLeft().setUp(parent);
            }
            child.setLeft(parent);
        }

        // Update parent and child "up" pointers
        parent.setUp(child);
        child.setUp(grandParent);

        // Update Grandparent or Root
        if (grandParent == null) {
            this.root = child;
        } else if (grandParent.getLeft() == parent) {
            grandParent.setLeft(child);
        } else {
            grandParent.setRight(child);
        }
    }

    /**
     * Main method to run the manual tests.
     */
    public static void main(String[] args) {
        BSTRotation<Integer> tree = new BSTRotation<>();

        System.out.println("Test 1: " + (tree.test1() ? "PASSED" : "FAILED"));
        System.out.println("Test 2: " + (tree.test2() ? "PASSED" : "FAILED"));
        System.out.println("Test 3: " + (tree.test3() ? "PASSED" : "FAILED"));
    }

    /**
     * TEST 1: Checks basic Left and Right rotations on nodes that are NOT the root.
     */
    public boolean test1() {
        try {
            // Left Rotation
            // Tree: Grandparent(10) -> Parent(20) -> Child(30)
            BinaryNode<Integer> grandParent = new BinaryNode<>(10);
            BinaryNode<Integer> parent = new BinaryNode<>(20);
            BinaryNode<Integer> child = new BinaryNode<>(30);

            this.root = (BinaryNode<T>) grandParent; // 10 is root
            grandParent.setRight(parent);
            parent.setUp(grandParent);
            parent.setRight(child);
            child.setUp(parent);

            // Perform left rotation
            rotate((BinaryNode<T>) child, (BinaryNode<T>) parent);

            // Verify
            if (grandParent.getRight() != child)
                return false;
            if (child.getUp() != grandParent)
                return false;

            if (child.getLeft() != parent)
                return false;
            if (parent.getUp() != child)
                return false;

            if (parent.getRight() != null)
                return false;

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * TEST 2: Checks rotations involving the ROOT node.
     */
    public boolean test2() {
        try {
            // Right Rotation at Root
            // Tree: Root(50) -> LeftChild(40)
            BinaryNode<Integer> oldRoot = new BinaryNode<>(50);
            BinaryNode<Integer> newRoot = new BinaryNode<>(40);

            this.root = (BinaryNode<T>) oldRoot;
            oldRoot.setLeft(newRoot);
            newRoot.setUp(oldRoot);

            // Perform right rotation
            rotate((BinaryNode<T>) newRoot, (BinaryNode<T>) oldRoot);

            // Verify
            if (this.root != newRoot)
                return false;

            if (newRoot.getUp() != null)
                return false;

            if (newRoot.getRight() != oldRoot)
                return false;
            if (oldRoot.getUp() != newRoot)
                return false;

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * TEST 3: Checks rotations with the node between parent and child.
     */
    public boolean test3() {
        try {
            // Right Rotation
            // Parent(30) -> Left Child(10) -> Right Node(20)
            // 20 is "between" 10 and 30.

            BinaryNode<Integer> parent = new BinaryNode<>(30);
            BinaryNode<Integer> child = new BinaryNode<>(10);
            BinaryNode<Integer> sharedChild = new BinaryNode<>(20);

            this.root = (BinaryNode<T>) parent;
            parent.setLeft(child);
            child.setUp(parent);
            child.setRight(sharedChild);
            sharedChild.setUp(child);

            // Perform right rotation
            rotate((BinaryNode<T>) child, (BinaryNode<T>) parent);

            // Verify

            if (parent.getLeft() != sharedChild)
                return false;
            if (sharedChild.getUp() != parent)
                return false;

            if (this.root != child)
                return false;

            if (child.getRight() != parent)
                return false;

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}