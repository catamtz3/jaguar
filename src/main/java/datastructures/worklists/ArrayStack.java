package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.LIFOWorkList;

import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/LIFOWorkList.java
 * for method specifications.
 */
public class ArrayStack<E> extends LIFOWorkList<E> {
    private final int DEFAULT = 10;
    private E[] arrayStack;
    private int size;
    public ArrayStack() {
        arrayStack = (E[]) new Object[DEFAULT];
        size = 0;
    }

    @Override
    public void add(E work) {
        if (size == arrayStack.length){
            E[] tempArray = (E[]) new Object[arrayStack.length * 2];
            for(int i = 0; i < arrayStack.length; i++){
                tempArray [i] = arrayStack [i];
            }
            arrayStack = tempArray;
        }
        arrayStack[size] = work;
        size++;
    }

    @Override
    public E peek() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return arrayStack[size - 1];


    }

    @Override
    public E next() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        E nextElement = arrayStack[size - 1];
        size--;
        return nextElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
    }
}

