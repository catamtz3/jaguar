package datastructures.dictionaries;

import cse332.datastructures.containers.Item;
import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.misc.DeletelessDictionary;
import cse332.interfaces.misc.Dictionary;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

/**
 * dictionary/list and return that dictionary/list's iterator.
 */
public class ChainingHashTable<K, V> extends DeletelessDictionary<K, V> {
    private final Supplier<Dictionary<K, V>> newChain;
    private Dictionary<K, V>[] array;
    private int startCount;
    private double loadFactor;
    private int totalCount;
    private double counting;
    private final int[] sizes = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,3907,3911,3917,3919,3923,3929,3931,3943,3947,3967 };

    public ChainingHashTable(Supplier<Dictionary<K, V>> newChain) {
        this.newChain = newChain;
        array = new Dictionary[7];
        for (int i = 0; i < 7; i++){
            array[i] = newChain.get();
        }
        startCount = 0;
        totalCount = 0;
        counting = 0.0;


    }

    public int size(){
        System.out.println("returning" + totalCount);
        return totalCount;
    }

    @Override
    public V insert(K key, V value) {
        if(loadFactor >= 1) {
            this.array = resize(array);
        }
        int index = Math.abs(key.hashCode() % array.length);
        if(index >= 0) {
            if(array[index] == null) {
                array[index] = newChain.get();
            }
            V returnValue = null;
            if(this.find(key) == null) {
                totalCount++;
            } else {
                returnValue = this.find(key);
            }
            array[index].insert(key, value);
            loadFactor = (++counting) / array.length;
            return returnValue;
        } else {
            return null;
        }
    }

    @Override
    public V find(K key) {
        int index = Math.abs(key.hashCode() % array.length);
        if(index >= 0) {
            if(array[index] == null) {
                array[index] = newChain.get();
                return null;
            }
            return array[index].find(key);
        } else {
            return null;
        }
    }

    @Override
    public Iterator<Item<K, V>> iterator() {
        if (array[0] == null) {
            array[0] = newChain.get();
        }
        Iterator<Item<K, V>> iter = new Iterator<Item<K, V>>() {
            private int iterStart = 0;
            Iterator<Item<K, V>> iterZero = array[0].iterator();

            @Override
            public boolean hasNext() {
                if (iterStart < array.length && !iterZero.hasNext()) {
                    if (array[iterStart + 1] == null) {
                        iterStart++;

                        while (array[iterStart] == null) {
                            iterStart++;
                            if (iterStart >= array.length) {
                                return false;
                            }
                        }
                    } else {
                        iterStart++;
                    }
                    if (iterStart < array.length) {
                        iterZero = array[iterStart].iterator();
                    }
                }
                if (iterStart >= array.length) {
                    return false;
                } else {
                    return iterZero.hasNext();
                }
            }

            @Override
            public Item<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return iterZero.next();
            }
        };
        return iter;
    }

    private Dictionary<K, V>[] resize(Dictionary<K, V> changeArray[]) {
        Dictionary<K, V>[] changedDict;
        if (startCount > 15) {
            changedDict = new Dictionary[changeArray.length * 2];
        } else {
            changedDict = new Dictionary[sizes[startCount]];
        }
        for (int i = 0; i < changeArray.length; i++) {
            if (changeArray[i] != null) {
                for (Item<K, V> item : changeArray[i]) {
                    int index = Math.abs(item.key.hashCode() % changedDict.length);
                    if (index >= 0) {
                        if (changedDict[index] == null) {
                            changedDict[index] = newChain.get();
                        }
                        changedDict[index].insert(item.key, item.value);
                    } else {
                        return new Dictionary[0];
                    }
                }
            }
        }
        startCount++;
        return changedDict;
    }


    /**
     * Temporary fix so that you can debug on IntelliJ properly despite a broken iterator
     * Remove to see proper String representation (inherited from Dictionary)
     */
    @Override
    public String toString() {
        return "ChainingHashTable String representation goes here.";
    }
}

