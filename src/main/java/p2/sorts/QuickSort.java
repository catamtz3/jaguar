package p2.sorts;

import cse332.exceptions.NotYetImplementedException;

import java.util.Comparator;

public class QuickSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        QuickSort.sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, Comparator<E> comparator) {
//        if (comparator.compare(array[0], array[array.length]) < 0){
//            int i = array.length;
//            E pivot = array[i];
//            int first = array[0] - 1;
//            for (int j = data[0])
//        }
//    }
        throw new NotYetImplementedException();
    }
}
