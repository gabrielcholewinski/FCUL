package src;


public interface BinaryTree<E> {

    /** @return the data in the root node
     * @requires !isEmpty()
     */
    E data();

    /** @return the left subtree
     * @requires !isEmpty()
     */
    BinaryTree<E> leftSubtree();

    /** @return the right subtree
     * @requires !isEmpty()
     */
    BinaryTree<E> rightSubtree();

    /** @return if the tree is empty
     */
    boolean isEmpty();

    /** @return the height of the tree
     */
    int height();

    /** @param e an element
     * @return The number of times e occurs in this three
     */
    int occurrences(E element);
}
