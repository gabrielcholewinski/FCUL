package src;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An Immutable Binary Tree implemented with linked nodes.
 * Notice that all attributes, both in LinkedBinaryTree and in Node, are final.
 * Also, the class does not implement the Cloneable interface,
 * since there is no need to clone immutable objects.
 *
 * @author Vasco T. Vasconcelos
 * @author Antonia Lopes
 * @author updated to be sonarLint sound
 */
public class LinkedBinaryTree<E> implements BinaryTree<E>, Iterable<E>{



    /**
     * The root of the tree
     */
    private final Node<E> root;

    /**
     * the number of elements in the three
     */
    private int size;


    /**
     * Constructor for an empty tree
     */
    public LinkedBinaryTree() {
        this((Node<E>)null);
        this.size = 0;
    }

    /**
     * Private Constructor
     * @param root - the root of the tree
     */
    private LinkedBinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * Constructor for a tree with a single element
     * @param data - the data for the root of the tree
     */
    public LinkedBinaryTree (E data) {
        this(new Node<E>(data, null, null));
        this.size = 1;
    }

    /**
     * Constructor for a tree with a root with data and the
     * indicated left and right trees
     * @param data - data for the root
     * @param left - the left tree
     * @param right - the right tree
     */
    public LinkedBinaryTree (E data, LinkedBinaryTree<E> left,
            LinkedBinaryTree<E> right) {
        this(new Node<E>(data, left.root, right.root));
        this.size = 1 + left.size + right.size;
    }




    //constructors


    /**
     * Verifies if the elements are equal
     * @param one - the first element to be compared
     * @param other - the element one is compared to
     * @return true if both elements are equal, false otherwise
     */
    private static boolean equalReferences(Object one, Object other) {
        return one == null ? other == null : one.equals(other); 
    }

    // Observers



    /**
     * Returns the element at the root of the tree
     * @return - element at the root
     */
    @Override
    public E data() {
        return this.root.data;
    }

    /**
     * Is this tree equal to another object?
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object other) { 
        if (this == other) {
            return true;
        } else if(other == null){
            throw new IllegalArgumentException();
        } else {
            return this.getClass() == other.getClass() &&
                    equalTrees(this.root, ((LinkedBinaryTree<E>) other).root);
        }
    }


    private boolean equalTrees(Node<E> one, Node<E> other) {
        boolean result = false;
        if  ((one == null && other == null) || 
                (one != null && one.equals(other))) {
            result = true;
        } else {
            result =  
                    one != null && 
                    other != null &&
                    equalReferences(one.data, other.data) &&
                    equalTrees(one.left, other.left) &&
                    equalTrees(one.right, other.right);
        } 
        return result;

    }

    /**
     * The height of this tree
     * @return the height of the tree
     */
    @Override
    public int height() {
        return height(this.root);
    }

    /**
     * Auxiliary method to calculate the height of the tree
     * @param node - current node
     * @return - height from this node
     */
    private int height (Node<E> node) {
        return node == null?
                0 :
                    1 + Math.max(height(node.left), height(node.right));
    }


    /**
     * Builds the correct indentation for the desired depth
     * @param depth - depth of the tree
     * @param builder - Strinbuilder where the string is being built
     */
    private static void indent (int depth, StringBuilder builder) {
        for (int i = 0; i < depth; i++) {
            builder.append(' ');
        }
    }

    /**
     * The index where an element occurs in this tree,
     * when traversed in the prefix order.
     * The index of the root is 0.
     *
     * @param element The element to look for
     * @return the index where the element was found; -1 if not found
     */
    public int indexOf(E element) {
        int i = 0;
        for (E e : this) {
            if (equalReferences(e, element)) {
                return i;
            } else {
                i++;
            }
        }
        return -1;
    }


    /**
     * Returns the infix iterator
     * @return the infix iterator
     */
    public Iterator<E> infixIterator() {
        return new InfixIterator();
    }



    /**
     * Is this tree balanced?
     * @return true if tree is balanced, false otherwise
     */
    public boolean isBalanced() {
        return isBalanced(this.root);
    }



    /**
     * Auxiliary method to verify if a tree is balanced
     * @param node - the current node
     * @return - if the tree is balanced
     */
    private boolean isBalanced(Node<E> node) {
        return
                node == null
                ||
                isBalanced(node.left) && isBalanced(node.right) &&
                Math.abs(height(node.left) - height(node.right)) <= 1;
    }

    /**
     * Is this tree complete?
     * @return true if the tree is complete
     */
    public boolean isComplete () {
        return isComplete (this.root);
    }
    /**
     * Auxiliary method to verify if a tree is complete
     * @param node - the current node
     * @return - true if the tree is complete, false otherwise
     */
    private boolean isComplete (Node<E> node) {
        return
                node == null
                ||
                height (node.left) == height (node.right) &&
                isPerfect(node.left) && isComplete(node.right)
                ||
                height (node.left) == height (node.right) + 1 &&
                isComplete(node.left) && isPerfect(node.right);
    }

    /**
     * Checks if the tree is empty
     * An empty tree has no root
     * @return true if the tree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Is this tree complete?
     * @return true if the tree is complete, false otherwise
     */
    public boolean isFull() {
        // After you have done size(), this method can be simplified.
        return isFull(this.root);
    }

    /**
     * Auxiliary method to verify if a tree is full
     * @param node - the current node
     * @return true if the tree is full, false otherwise
     */
    private boolean isFull(Node<E> node) {
        return
                node == null
                ||
                height(node.left) == height(node.right) &&
                isFull(node.left) && isFull(node.right);
    }

    /**
     * Is this tree a leaf?
     * @return true if the tree is a leaf, false otherwise
     */
    public boolean isLeaf() {
        return this.root != null && this.root.left == null && this.root.right == null;
    }

    /**
     * Is this tree perfect?
     * @return true if the tree is perfect, false otherwise
     */
    public boolean isPerfect() {
        // After you have done size(), this method can be simplified.
        return isPerfect(this.root);
    }

    /**
     * Auxiliary method to verify if a tree is perfect
     * @param node - the current node
     * @return true if the tree is perfect, false otherwise
     */
    private boolean isPerfect(Node<E> node) {
        return
                node == null
                ||
                height(node.left) == height(node.right) &&
                isPerfect(node.left) && isPerfect(node.right);
    }

    /**
     * Returns the default iterator (prefix)
     * @return the prefix iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new PrefixIterator();
    }


    /**
     * Returns the left subtree
     * @return the left subtree from root
     */
    @Override
    public LinkedBinaryTree<E> leftSubtree() {
        return new LinkedBinaryTree<E> (this.root.left);
    }

    /**
     * The number of times an element occurs in this tree.
     * O(n)
     * @param element - the element to search
     * @return - the number of occurrences
     */
    @Override
    public int occurrences(E element) {
        return occurrences(element, this.root);
    }

    /**
     * Auxiliary method to count the number of occurrences of an element
     * @param element - the element to search
     * @param node - the current node
     * @return - number of occurrences of the element
     */
    private int occurrences(E element, Node<E> node) {
        if (node == null) {
            return 0;
        } else if (equalReferences(element, node.data)) {
            return 1 + occurrences(element, node.left)
            + occurrences(element, node.right);
        } else {
            return occurrences(element, node.left)
                    + occurrences(element, node.right);
        }
    }


    /**
     * Transverses the tree in preorder, adding textual representations
     * of the nodes to the StringBuilder
     * @param node - the current node
     * @param depth - the depth of the node
     * @param builder - the StringBuilder where the String is being built
     */
    private void preOrderTraverse(Node<E> node, int depth, StringBuilder builder) {
        //indent(depth, builder);
        //        if (node == null) {
        //            builder.append ("Empty\n");
        //        } else {
        if(node != null) {
            indent(depth, builder);
            builder.append (node.data == null ? "null" : node.data.toString());
            builder.append ('\n');
            preOrderTraverse (node.left, depth + 1, builder);
            preOrderTraverse (node.right, depth + 1, builder);
        }
    }

    /**
     * Returns the right subtree
     * @return the right subtree from root
     */
    @Override
    public LinkedBinaryTree<E> rightSubtree() {
        return new LinkedBinaryTree<E> (this.root.right);
    }


    /** How many elements are in the three
     * @return - number of elements in the tree
     */
    public int size() {
        return this.size;
    }

    /**
     * Textual representation of a Tree
     * @return String representing the tree
     */
    @Override
    public String toString () {
        StringBuilder builder = new StringBuilder ();
        preOrderTraverse(this.root, 0, builder);
        return builder.toString();
    }


    /**
     * Inner class for InfixIterator
     * Can read attribute root; inherits type parameter E.
     */
    private class InfixIterator implements Iterator<E> {

        private Node<E> current;

        private ArrayStack<Node<E>> stack;
        /**
         * Constructor
         */
        private InfixIterator() {
            this.stack = new ArrayStack<Node<E>>();
            this.current = LinkedBinaryTree.this.root;
        }

        /**
         * Verifies if iterator still has elements
         * @return true if the iterator still has elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            return !this.stack.isEmpty() || this.current != null;
        }

        /**
         * Returns the next element
         * @return - next element
         */
        @Override
        public E next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            while (this.current != null) {
                this.stack.push(this.current);
                this.current = this.current.left;
            }

            this.current = this.stack.peek();
            this.stack.pop();
            Node<E> node = this.current;
            this.current = this.current.right;

            return node.data;
        }

    }



    /**
     * Inner class for PrefixIterator
     * Can read attribute root; inherits type parameter E.
     */
    private class PrefixIterator implements Iterator<E> {

        /**
         * Stack of Nodes
         */
        private ArrayStack<Node<E>> stack;

        /**
         * Constructor
         */
        private PrefixIterator() {
            this.stack = new ArrayStack<Node<E>>();
            if (LinkedBinaryTree.this.root != null) {
                this.stack.push(LinkedBinaryTree.this.root);
            }
        }

        /**
         * Verifies if iterator still has elements
         * @return true if the iterator still has elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        /**
         * Returns the next element
         * @return - next element
         */
        @Override
        public E next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> node = this.stack.peek();
            this.stack.pop();
            if (node.right != null) {
                this.stack.push(node.right);
            }
            if (node.left != null) {
                this.stack.push(node.left);
            }
            return node.data;
        }

        /**
         * Remove operation, not supported
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }



    /**
     * Class that represents a node
     * @param <E> - type of the node
     */
    private static class Node<E> {

        /**
         * The data stored on the node
         */
        private final E data;

        /**
         * The node linked on the left
         */
        private final Node<E> left;

        /**
         * The node linked on the right
         */
        private final Node<E> right;

        /**
         * Constructor
         * @param data - the data to store in the node
         * @param left - the node to link on the left
         * @param right - the node to link on the right
         */
        private Node(E data, Node<E> left, Node<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString(){
            return data.toString(); 
        }
    } 
    //end node
}
