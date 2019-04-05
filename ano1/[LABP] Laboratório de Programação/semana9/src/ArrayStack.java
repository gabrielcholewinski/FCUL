package src;


/**
 * A vectorial implementation of a Stack (using a growing array).
 *
 * @param <E> The type of the elments in this stack.
 *
 * @author Vasco T. Vasconcelos
 */
public class ArrayStack<E> implements Cloneable {

    /**
     * The stack.
     */
    private E [] theStack;

    /**
     * Index of the top element of the stack in the array.
     * -1 for an empty stack.
     */
    private int top;

    /**
     * The initial capacity of the array.
     */
    private static final int DEFAULT_CAPACITY = 10;
    
    /**
     * Clear the contents of this Stack.
     */
    public ArrayStack () {
        clear ();
    }

    
    /* (non-Javadoc)
     * @see Stack#clear()
     */
    @SuppressWarnings("unchecked")
    public void clear () {
        this.theStack = (E []) new Object [DEFAULT_CAPACITY];
        this.top = -1;
    }

    /* (non-Javadoc)
     * @see Stack#push(E)
     */
    public void push (E e) {
        if (size() == this.theStack.length) {
            grow ();
        }
        this.top++;
        this.theStack[this.top] = e;
    }

    /* (non-Javadoc)
     * @see Stack#peek()
     */
    public E peek () {
        return this.theStack[this.top];
    }

    /* (non-Javadoc)
     * @see Stack#pop()
     */
    public void pop () {
        this.theStack[this.top] = null;
        this.top--;
    }

    /* The number of elements in the stack
     */
    private int size () {
        return this.top + 1;
    }

    /* (non-Javadoc)
     * @see Stack#isEmpty()
     */
    public boolean isEmpty () {
        return size () == 0;
    }

 
    

    /**
     * Increase the capacity of the array holding the Stack.
     */
    @SuppressWarnings("unchecked")
    private void grow () {
        final int dobro = 2;
        E [] newStack = (E[]) new Object [this.theStack.length * dobro];
        for (int i = 0; i < this.theStack.length; i++) {
            newStack[i] = this.theStack[i];
        }
        this.theStack = newStack;
    }

    /**
     * The textual representation of the Stack,
     * in the form "a:b:c:[]",
     * where "c" is the element at the top of the stack.
     */
    @Override
    public String toString () {
        StringBuffer buffer = new StringBuffer ();
        for (int i = 0; i <= this.top; i++) {
            buffer.append(this.theStack [i] + ":");
        }
        buffer.append("[]");
        return buffer.toString();
    }

    /**
     * A faithful copy of this stack.
     * Clones the backbone (the array) of the
     * stack, but not its elements.
     */
    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            ArrayStack<E>result = (ArrayStack<E>) super.clone();
            result.theStack = this.theStack.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }
    }

    /**
     * Is this stack equal to a given object?
     *
     * @param other
     *            The object.
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object other) {
        return
                this == other || other instanceof ArrayStack &&
                equalStacks((ArrayStack<E>) other);
    }

    /**
     * Are two stacks equals?
     *
     * @param other
     *            The other queue.
     */
    private boolean equalStacks(ArrayStack<E> other) {
        if (this.size() != other.size()) {
            return false;
        }
        for (int k = 0; k < size(); k++) {
            if (! equalReferences(this.theStack[k], other.theStack[k])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Are two references equal?
     * Takes into consideration the case when both references are null.
     * @param one One reference.
     * @param other The other reference.
     */
    private static boolean equalReferences(Object one, Object other) {
        return one == null ? other == null : one.equals(other);
    }
}
