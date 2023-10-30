package com.company.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class MyListImpl<T> implements MyList<T> {

    private static final int DEFAULT_CAPACITY = 4;

    private T[] elements;
    private int size;

    public MyListImpl() {
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public MyListImpl(int initialCapacity) {
        checkInitialCapacity(initialCapacity);

        this.elements = (T[]) new Object[initialCapacity];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.elements.length;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        return this.elements[index];
    }

    @Override
    public void add(T element) {
        ensureCapacity(size + 1);

        this.elements[size] = element;
        size++;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elements[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        for (int i = size - 1; i >= 0; i--) {
            if (element.equals(this.elements[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean remove(T element) {
        int index = indexOf(element);

        if (index != -1) {
            removeAt(index);

            return true;
        }

        return false;
    }

    @Override
    public void removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }

        System.arraycopy(this.elements, index + 1, this.elements, index, size - index - 1);
        elements[--size] = null;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    @Override
    public void swap(int from, int to) {
        if (from < 0 || from >= size || to < 0 || to >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        T temp = (T) this.elements[from];
        this.elements[from] = this.elements[to];
        this.elements[to] = temp;
    }

    @Override
    public Iterator iterator() {
        return new MyArrayListIterator();
    }

    private class MyArrayListIterator implements Iterator<T> {
        private int currentIndex;

        public MyArrayListIterator() {
            this.currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            T currentElement = elements[currentIndex];
            currentIndex++;

            return currentElement;
        }
    }

    private void checkInitialCapacity(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Invalid capacity");
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > this.elements.length) {
            int newCapacity = Math.max(this.elements.length * 2, minCapacity);
            this.elements = Arrays.copyOf(this.elements, newCapacity);
        }
    }

    public void print() {
        System.out.print("[");

        for (int i = 0; i < size; i++) {
            System.out.print(this.elements[i]);

            if (i < size - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }
}