package datastructures.worklists;

import cse332.datastructures.trees.BinarySearchTree;
import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.PriorityWorkList;
import cse332.interfaces.worklists.WorkList;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;


/**
 * See cse332/interfaces/worklists/PriorityWorkList.java
 * for method specifications.
 */
public class MinFourHeapComparable<E extends Comparable<E>> extends PriorityWorkList<E> {
    /* Do not change the name of this field; the tests rely on it to work correctly. */
    private E[] data;
    private int defaultSize;
    private int currentSize;


    public MinFourHeapComparable() {
        currentSize = 0;
        defaultSize = 100;
        data = (E[]) new Comparable[defaultSize];
    }

    @Override
    public boolean hasWork() {
        return currentSize > 0;
    }

    @Override
    public void add(E work) {
        if (currentSize == defaultSize) {
            defaultSize = defaultSize * 2;
            data = Arrays.copyOf(data, defaultSize);
        }
        currentSize++;
        data[currentSize - 1] = work;
        percolateUp(currentSize - 1);
    }

    @Override
    public E peek() {
        if (currentSize == 0) {
            throw new NoSuchElementException();
        }
        return data[0];
    }

    @Override
    public E next() {
        if (currentSize == 0) {
            throw new NoSuchElementException();
        }
        E value = data[0];
        currentSize = currentSize - 1;
        data[0] = data[currentSize];
        percolateDown(0);
        return value;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public void clear() {
        currentSize = 0;
    }

    private void percolateDown(int parentIndex) {
        int childIndex = (parentIndex * 4) + 1;
        while (childIndex < currentSize){
            int minChild = childIndex;
            int lastChild = Math.min(childIndex + 4, currentSize);
            for (int currentChild = childIndex + 1; currentChild < lastChild; currentChild++){
                if (data[minChild].compareTo(data[currentChild]) > 0) {
                    minChild = currentChild;
                }
            }
            if (data[minChild].compareTo(data[parentIndex]) < 0) {
                swapTwoIndices(minChild, parentIndex);
                parentIndex = minChild;
                childIndex = (parentIndex * 4) + 1;
            } else {
                return;
            }
        }
    }

    private void swapTwoIndices (int child, int parent){
        E temp = data[parent];
        data[parent] = data[child];
        data[child] = temp;

    }

    private void percolateUp(int childIndex){
        int parentIndex = ((childIndex - 1) / 4);
        while (parentIndex >= 0) {
            if(data[childIndex].compareTo(data[parentIndex]) < 0) {
                swapTwoIndices(childIndex, parentIndex);
                childIndex = parentIndex;
                parentIndex = ((childIndex - 1) / 4);
            } else {
                return;
            }
        }
    }
}
