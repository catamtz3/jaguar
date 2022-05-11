package p2.sorts;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.PriorityWorkList;
import datastructures.worklists.MinFourHeap;

import java.util.Comparator;

public class TopKSort {
    public static <E extends Comparable<E>> void sort(E[] array, int k) {
        sort(array, k, (x, y) -> x.compareTo(y));
    }

    /**
     * Behaviour is undefined when k > array.length
     */
    public static <E> void sort(E[] array, int k, Comparator<E> comparator) {
        if (k > array.length)
            k = array.length;
        PriorityWorkList<E> pq = new MinFourHeap<>(comparator);
        for (int i = 0; i < k; i++)
            pq.add(array[i]);
        for (int j = k; j < array.length; j++) {
            if (comparator.compare(pq.next(), array[j]) < 0) {
                pq.next();
                pq.add(array[j]);
            }
        }
    }
}
