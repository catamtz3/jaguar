package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.FIFOWorkList;
import cse332.interfaces.worklists.FixedSizeFIFOWorkList;

/**
 * See cse332/interfaces/worklists/FixedSizeFIFOWorkList.java
 * for method specifications.
 */
public class CircularArrayFIFOQueue<E extends Comparable<E>> extends FixedSizeFIFOWorkList<E> {

        private int front;
        private int rear;
        private int size;
        private E[] array;
        public CircularArrayFIFOQueue(int capacity) {
            super(capacity);
            this.size = 0;
            this.rear = 0;
            this.front = 0;
            this.array = (E[])new Comparable[capacity];
        }

        @Override
        public void add(E work) {
            if(isFull()){
                throw new IllegalStateException();
            }
            array[rear] = work;
            rear = (rear + 1) % array.length;
            size++;
            if(front == -1) {
                front = rear;
            }
        }

        @Override
        public E peek() {
            if (!hasWork()){
                throw new IllegalStateException();
            }
            return array[(front)];

        }

        @Override
        public E peek(int i) {
            return array[(front + i) % array.length];
        }

        @Override
        public E next() {
            if(size == 0){
                throw new IllegalStateException();
            }
            E delete = array[front];
            array[front] = null;
            front = (front + 1) % array.length;
            size--;
            return delete;

        }

        @Override
        public void update(int i, E value) {
            if( 0 <= i && i < size()) {
                array[front + i] = value;
            } else {
                throw new IndexOutOfBoundsException();
            }
            if(!this.hasWork()) {
                throw new IllegalStateException();
            }
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public void clear() {
            front = 0;
            rear = 0;
            size = 0;
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
            for (int i = 0; i < this.array.length; i++){
                result = result * prime + ((this.array[i] == null) ? 0 : this.array[i].hashCode());
            }
            return result;
        }
    }

