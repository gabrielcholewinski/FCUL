package src;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A queue implemented with an array.
 *
 * @param <E>
 *            The type of the elements in the queue.
 *
 * @author Koffman and Wolgang
 * @author Vasco T. Vasconcelos
 * @version $Id: ArrayQueue.java 494 2009-04-02 09:52:01Z vv $
 * modified by respicio and mal
 */
public class ArrayQueue<E> implements Cloneable, Iterable<E> {

    /**
     * The length of initial array.
     */
    private static final int DEFAULT_CAPACITY = 4;

    /**
     * The elements on the queue.
     */
    private E[] theData;

    /**
     * The reference of the front of the queue, that is,
     * the first element to exit the queue.
     */
    private int front;

    /**
     * The reference of the rear of the queue, that is the last element
     * entered in the queue;
     */
    private int rear;

    /**
     * The number of elements in the queue.
     */
    private int size;

    /**
     * Construct an empty queue.
     */
    public ArrayQueue() {
        clear();
    }

    /**
     * Remove all elements from this queue.
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        this.theData = (E[]) new Object[DEFAULT_CAPACITY];
        this.front = 0;
        this.rear = this.theData.length - 1;
        this.size = 0;
    }

    /**
     * Add an element to the rear of this queue
     *
     * @param item
     *            The element.
     * @requires element != null
     */
    public void offer(E item) {
        if (this.size == this.theData.length) {
            reallocate();
        }
        this.rear = inc(this.rear);
        this.theData[this.rear] = item;
        this.size++;
    }

    /**
     * Is this queue empty?
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Is this queue empty?
     */
    public int size() {
        return this.size;
    }


    /**
     * The element at front of the queue.
     * @requires !isEmpty()
     */
    public E element() {
        return this.theData[this.front];
    }

    /**
     * Remove the element at the front of the queue.
     * @requires !isEmpty()
     */
    public void remove() {
        this.theData[this.front] = null;
        this.front = inc(this.front);
        this.size--;
    }

    /**
     * Arithmetic modulo the length of the array holding the elements in the
     * queue.
     */
    private int inc(int i) {
        return (i + 1) % this.theData.length;
    }

    /**
     * Move the elements in the array to a larger array.
     * with to calls to System.arraycopy.
     */
    @SuppressWarnings("unchecked")
    private void reallocate() {
        final int dobro = 2;
        E[] newData = (E[]) new Object[this.theData.length * dobro];
        System.arraycopy(this.theData, this.front, newData, 0, this.size-this.front);
        //copy size-front elements from position front  of array theData
        //to positions 0 to 0+size-front of array newData
        System.arraycopy(this.theData, 0, newData, this.size-this.front, this.front);
        this.front = 0;
        this.rear = this.size - 1;
        this.theData = newData;
    }

    /**
     * The textual representation of this queue,
     * in the form "<a, b, c, <".
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("<");
        boolean first = true;
        for(E e: this){
            if (first){
                result.append(e); 
                first = false;
            } else {
                result.append(", "+e);
            }
        }
        result.append("<");
        return result.toString();
    }


    /**
     * A faithful copy of this queue. Clones the backbone (the array) of the
     * queue, but not its elements.
     */
    @Override
    @SuppressWarnings("unchecked")
    public ArrayQueue<E> clone() {
        try {
            ArrayQueue<E> result = (ArrayQueue<E>) super.clone();
            result.theData = this.theData.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }
    }

    /**
     * Is this queue equal to a given object?
     *
     * @param other
     *            The object.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object other) {
        return this == other || other instanceof ArrayQueue
                && equalQueues((ArrayQueue<E>) other);
    }

    /**
     * Are two queues equals?
     *
     * @param other
     *            The other queue.
     */
    private boolean equalQueues(ArrayQueue<E> other) {
        if (this.size != other.size) {
            return false;
        }
        int i = this.front;
        int j = other.front;
        for (int k = 0; k < this.size; k++) {
            if (!equalReferences(this.theData[i], other.theData[j])) {
                return false;
            }
            i = (i + 1) % this.theData.length;
            j = (j + 1) % other.theData.length;
        }
        return true;
    }

    /**
     * Are two references equal? Takes into consideration the case when both
     * references are null.
     *
     * @param one
     *            One reference.
     * @param other
     *            The other reference.
     */
    private static boolean equalReferences(Object one, Object other) {
        return one == null ? other == null : one.equals(other);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    /**
     * Inner class to implement the Iterator<E> interface.
     */

    private class Iter implements Iterator<E> {
        // Data Fields

        // Index of next element
        private int index;
        // Count of elements accessed so far */
        private int count = 0;

        // Constructor
        /** Initializes the Iter object to reference the
         * first queue element.
         */
        public Iter() {
            this.index = ArrayQueue.this.front;
        }

        /**
         * Returns true if there are more elements in the queue to iterate.
         * @return true if there are more elements in the queue to iterate.
         */
        @Override
        public boolean hasNext() {
            return this.count < ArrayQueue.this.size;
        }

        /**
         * Returns the next element in the iteration of the queue.
         * @return The next element in the iteration
         * @throws NoSuchElementException - if the iteration has no more elements
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E returnValue = ArrayQueue.this.theData[this.index];
            this.index = inc(this.index);
            this.count++;
            return returnValue;
        }

      
    }
}
