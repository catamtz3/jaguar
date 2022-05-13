package datastructures.dictionaries;

import cse332.datastructures.containers.Item;
import cse332.interfaces.trie.TrieMap;
import cse332.types.BString;
import datastructures.worklists.ArrayStack;

import java.util.AbstractMap;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 */
public class HashTrieMap<A extends Comparable<A>, K extends BString<A>, V> extends TrieMap<A, K, V> {
    public class HashTrieNode extends TrieNode<ChainingHashTable<A,HashTrieNode>,HashTrieNode> {
        public HashTrieNode() {
            this(null);
        }

        public HashTrieNode(V value) {
            this.pointers = new ChainingHashTable<>(() -> new MoveToFrontList<>());
            this.value = value;
        }


        @Override
        public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
            ArrayStack<Entry<A, HashTrieNode>> entry = new ArrayStack<>();

            for (Item<A, HashTrieNode> value : this.pointers) {
                entry.add(new AbstractMap.SimpleEntry(value.key, value.value));
            }
            return entry.iterator();
        }
    }




    public HashTrieMap(Class<K> KClass) {
        super(KClass);
        this.root = new HashTrieNode();
        this.size = 0;
    }

    @Override
    public V insert(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        if (this.root == null) {
            this.root = new HashTrieNode();
        }
        V returnValue = null;
        if (key.isEmpty()) {
            returnValue = this.root.value;
            this.root.value = value;
        } else {
            HashTrieNode current = (HashTrieMap<A, K, V>.HashTrieNode) this.root;
            for (A currKey : key) {
                if (current.pointers.find(currKey) == null) {
                    current.pointers.insert(currKey, new HashTrieNode());
                }
                current = current.pointers.find(currKey);
            }
            returnValue = current.value;
            current.value = value;
        }
        if (returnValue == null) {
            this.size++;
        }
        return returnValue;
    }



    @Override
    public V find(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (this.root == null){
            return null;
        }
        HashTrieNode curr = (HashTrieMap<A, K, V>.HashTrieNode) this.root;
        for (A character : key){
            curr = curr.pointers.find(character);
            if (curr == null){
                return null;
            }
        }
        return curr.value;
    }

    @Override
    public boolean findPrefix(K key) {
        if (key == null){
            throw new IllegalArgumentException();
        }
        if (this.root == null) {
            return false;
        }
        HashTrieNode curr = (HashTrieMap<A,K,V>.HashTrieNode) this.root;
        for(A character : key){
            curr = curr.pointers.find(character);
            if (curr == null){
                return false;
            }
        }
        return true;
    }

    @Override
    public void delete(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
}
