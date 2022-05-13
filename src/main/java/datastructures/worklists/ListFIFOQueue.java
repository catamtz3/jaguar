package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.FIFOWorkList;

import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/FIFOWorkList.java
 * for method specifications.
 */
public class ListFIFOQueue<E> extends FIFOWorkList<E> {
    private static class Node<E>{
        E data;
        Node<E> next;
        public Node (E data){
            this.data = data;
            this.next = null;
        }
    }

    private Node<E> front;
    private Node<E> rear;
    private int size;
    public ListFIFOQueue(){
        size = 0;
    }


    @Override
    public void add(E work) {
        Node<E> temp = new Node<E>(work);
        size++;
        if (this.front == null){
            this.front = this.rear = temp;
        }
        this.rear.next = temp;
        this.rear = temp;
    }

    @Override
    public E peek() {
        if(front == null) {
            throw new NoSuchElementException();
        }
        return front.data;
    }

    @Override
    public E next() {
        if (this.front == null){
            throw new NoSuchElementException();
        }
        Node<E> temp = this.front;
        this.front = this.front.next;
        if(this.front == null){
            this.rear = null;
        }
        size--;
        return temp.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }
}
