package p2.sorts;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.PriorityWorkList;
import datastructures.worklists.MinFourHeap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, Comparator<E> comparator) {
        PriorityWorkList<E> heap = new MinFourHeap<E>(comparator);
        for (int i = 0; i < array.length; i++){
            heap.add(array[i]);
        }
        for (int j = 0; j < array.length; j++){
            array[j] = heap.next();
        }
    }
}
