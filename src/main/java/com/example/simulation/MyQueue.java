package com.example.simulation;

/**
 * Basic Queue ADT interface used by the customer service simulation.
 *
 * @param <T> element type stored in the queue
 */
public interface MyQueue<T> {
    /**
     * Inserts an item at the rear of the queue.
     *
     * @param item element to enqueue
     */
    void enqueue(T item);

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return element at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    T dequeue();

    /**
     * Returns, but does not remove, the element at the front of the queue.
     *
     * @return element at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    T peek();

    /**
     * Indicates whether the queue is empty.
     *
     * @return true if the queue contains no elements
     */
    boolean isEmpty();

    /**
     * Returns the current number of elements stored in the queue.
     *
     * @return queue size
     */
    int size();
}
