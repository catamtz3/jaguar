package datastructures.dictionaries;

import cse332.datastructures.containers.Item;
import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.misc.DeletelessDictionary;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 1. The list is typically not sorted.
 * 2. Add new items to the front of the list.
 * 3. Whenever find or insert is called on an existing key, move it
 * to the front of the list. This means you remove the node from its
 * current position and make it the first node in the list.
 * 4. You need to implement an iterator. The iterator SHOULD NOT move
 * elements to the front.  The iterator should return elements in
 * the order they are stored in the list, starting with the first
 * element in the list. When implementing your iterator, you should
 * NOT copy every item to another dictionary/list and return that
 * dictionary/list's iterator.
 */
public class MoveToFrontList<K, V> extends DeletelessDictionary<K, V> {
    Node front;
    Node back;
    public static class Node<K,V> {
        final K key;
        V value;
        Node next;
        Node prev;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }
    @Override
    public V insert(K key, V value) {
        if (key == null){
            throw new IllegalArgumentException();
        }
        V temp = this.find(key);
        Node node = new Node(key,value);
        if(this.size== 0){
            this.front = node;
            this.back = node;
            this.size++;
            return value;
        } else {
            temp = (V)this.front.value;
            this.front.prev = node;
            node.next = this.front;
            this.front = node;
            this.size++;
            return temp;
        }
    }

    @Override
    public V find(K key) {
        if (key ==  null){
            throw new IllegalArgumentException();
        }
        Node curr = this.front;
        while(curr != null){
            if (curr.key.equals(key) && curr.prev == null){
                return (V) curr.value;
            } else if (curr.key.equals(key) && curr.prev != null && curr.next != null){
                curr.prev.next = curr.next;
                curr.next.prev = curr.prev;
                this.back = this.back.prev;
                curr.next = this.front;
                curr.prev = null;
                this.front.prev = curr;
                this.front = curr;
                return (V)curr.value;
            } else if (curr.key.equals(key) && curr.next == null){
                curr.prev.next = null;
                this.back = this.back.prev;
                curr.next = this.front;
                curr.prev = null;
                this.front.prev = curr;
                this.front = curr;
                return (V)curr.value;
            } else {
                curr = curr.next;
            }
        }
        return null;
    }

    @Override
    public Iterator<Item<K, V>> iterator() {
        throw new IllegalArgumentException();
    }
}
