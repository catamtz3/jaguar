package p2.sorts;

import cse332.exceptions.NotYetImplementedException;

import java.util.Comparator;

public class QuickSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        QuickSort.sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, Comparator<E> comparator) {
        if (comparator.compare(array[0], array[array.length]) < 0){
            int start = 0;
            int end = array.length;
            int pivot = pivot(array, start, end);
            pivot()
            sort(array, comparator.compare(start, end))
        }
    }

    public static int pivot(E[] array, int start, int end){
        int pivot = start + end / 2;
        E pivotValue = array[pivot];
        while (true){
            do start++; while(comparator.compare(array[start], pivotValue) < 0);
            do end--; while(comparator.compare(array[end], pivotValue) > 0);
            if (start >= end){
                end;
            }
            E temp = array[start];
            array[start] = array[end];
            array[end] = temp;
        }
    }
}
