package com.example.simulation;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Array-based circular buffer implementation of the {@link MyQueue} interface.
 * The implementation resizes automatically when capacity is reached so that
 * enqueue and dequeue operations remain constant time on average.
 *
 * @param <T> element type
 */
public class ArrayQueue<T> implements MyQueue<T> {
    private static final int DEFAULT_CAPACITY = 16;

    private Object[] elements;
    private int front;
    private int size;

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayQueue(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        elements = new Object[initialCapacity];
        front = 0;
        size = 0;
    }

    @Override
    public void enqueue(T item) {
        Objects.requireNonNull(item, "Cannot enqueue null elements");
        ensureCapacity();
        int rearIndex = (front + size) % elements.length;
        elements[rearIndex] = item;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T value = (T) elements[front];
        elements[front] = null; // allow garbage collection
        front = (front + 1) % elements.length;
        size--;
        return value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return (T) elements[front];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size < elements.length) {
            return;
        }
        int newCapacity = elements.length * 2;
        Object[] newArray = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = elements[(front + i) % elements.length];
        }
        elements = newArray;
        front = 0;
    }
}
