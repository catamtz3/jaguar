package datastructures.dictionaries;

import cse332.datastructures.containers.Item;
import cse332.interfaces.trie.TrieMap;
import cse332.types.BString;

import java.util.AbstractMap;
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
            this.pointers = new ChainingHashTable<>(MoveToFrontList::new);
            this.value = value;
        }
    }

    @Override
    public Iterator<Entry<A, HashTrieNode> iterator() {

        Iterator<Item<A, HashTrieNode>> chainingHashIterator = pointers.iterator();
        Iterator<Entry<A, HashTrieNode>> returnIterator = new Iterator<Entry<A, HashTrieNode>>() {
            @Override
            public boolean hasNext() {
                return chainingHashIterator.hasNext();
            }
            @Override
            public Entry<A, HashTrieNode> next() {
                Item<A, HashTrieNode> returnItem = chainingHashIterator.next();
                Entry<A, HashTrieNode> returnEntry =  new AbstractMap.SimpleEntry<>(returnItem.key, returnItem.value);
                return returnEntry;
            }
        };
        return returnIterator;
    }

    }



    public HashTrieMap(Class<K> KClass) {
        super(KClass);
        this.root = new HashTrieNode();
    }

    @Override
    public V insert(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        HashTrieNode curr = (HashTrieNode) this.root;
        for(A currentKey : key){
            if(curr.pointers.find(currentKey) != null){
                curr = curr.pointers.find(currentKey);
            } else {
                curr.pointers.insert(currentKey, new HashTrieNode());
                curr = curr.pointers.find(currentKey);
            }
        }
        if(curr.value == null){
            size++;
        }
        curr.value = value;
        return curr.value;
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
        boolean result = true;
        for(A character : key){
            if(curr.pointers.find(character) != null){
                curr = curr.pointers.find(character);
            } else {
                result = false;
            }
        }
        return result;
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
