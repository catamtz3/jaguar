package p2.sorts;

import cse332.exceptions.NotYetImplementedException;

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
            int partitionIndex = insertionSort(array, start, end, comparator);
            quicksort(array, start,partitionIndex-1,comparator);
            quicksort(array,partitionIndex+1, end, comparator);
        }
    }

    public static <E> int insertionSort(E [] array,int start,int end,Comparator<E> comparator) {
        E pivot = array[end];
        int i = start-1;
        for (int j = start; j < end; j ++) {
            if (comparator.compare(pivot, array[j]) >=0 ) {
                i++;
                E swaptemp = array[i];
                array[i] = array[j];
                array[j] = swaptemp;
            }
        }
        E swapTemp = array[i+1];
        array[i+1] = array[end];
        array[end] = swapTemp;
        return i+1;
    }
}