package p2.sorts;

import java.util.Comparator;

public class QuickSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        QuickSort.sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, Comparator<E> comparator) {
        quicksort(array,0,array.length-1,comparator);
    }

    public static <E> void quicksort( E [] array, int start , int end,Comparator<E> comparator) {
        if(start < end) {
            E pivot = array[end];
            int beg = start-1;
            for (int j = start; j < end; j ++) {
                if (comparator.compare(pivot, array[j]) >=0 ) {
                    beg++;
                    E swap = array[beg];
                    array[beg] = array[j];
                    array[j] = swap;
                }
            }
            E swapTemp = array[beg+1];
            array[beg+1] = array[end];
            array[end] = swapTemp;
            int partition = beg+1;
            quicksort(array, start,partition-1,comparator);
            quicksort(array,partition+1, end, comparator);
        }
    }
}