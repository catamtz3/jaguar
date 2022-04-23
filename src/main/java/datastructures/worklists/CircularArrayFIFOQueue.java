package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.FIFOWorkList;
import cse332.interfaces.worklists.FixedSizeFIFOWorkList;

/**
 * See cse332/interfaces/worklists/FixedSizeFIFOWorkList.java
 * for method specifications.
 */
public class CircularArrayFIFOQueue<E extends Comparable<E>> extends FixedSizeFIFOWorkList<E> {

    private E[] arr;
    public CircularArrayFIFOQueue(int capacity) {
        super(capacity);
        throw new NotYetImplementedException();
    }

    @Override
    public void add(E work) {
        throw new NotYetImplementedException();
    }

    @Override
    public E peek() {
        throw new NotYetImplementedException();
    }

    @Override
    public E peek(int i) {
        throw new NotYetImplementedException();
    }

    @Override
    public E next() {
        throw new NotYetImplementedException();
    }

    @Override
    public void update(int i, E value) {
        throw new NotYetImplementedException();
    }

    @Override
    public int size() {
        throw new NotYetImplementedException();
    }

    @Override
    public void clear() {
        throw new NotYetImplementedException();
    }

    @Override
    public int compareTo(FixedSizeFIFOWorkList<E> other) {
        // You will implement this method in project 2. Leave this method unchanged for project 1.
        int compare = Math.min(this.size(), other.size());
        for(int i = 0; i < compare; i++){
            if(this.peek(i).compareTo(other.peek(i)) != 0){
                return this.peek(i).compareTo(other.peek(i));
            }
        }

        if(this.size() > other.size()){
            return 1;
        }
        if(this.size() < other.size()){
            return -1;
        }
        return 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        // You will finish implementing this method in project 2. Leave this method unchanged for project 1.
        if (this == obj) {
            return true;
        } else if (!(obj instanceof FixedSizeFIFOWorkList<?>)) {
            return false;
        } else {
            // Uncomment the line below for p2 when you implement equals
            FixedSizeFIFOWorkList<E> other = (FixedSizeFIFOWorkList<E>) obj;
            if(this.size() != other.size())
                return false;
            else {
                for (int i = 0; i < this.size(); i++){
                    if(this.peek(i) != other.peek(i)){
                        return false;
                    }
                }
                return true;
            }
        }
    }

    @Override
    public int hashCode() {
        // You will implement this method in project 2. Leave this method unchanged for project 1.
        int prime = 7;
        int result = 1;
        for (int i = 0; i < this.arr.length; i++){
            result = result * prime + ((this.arr[i] == null) ? 0 : this.arr[i].hashCode());
        }
        return result;
    }
}
