package core.basesyntax.impl;

import core.basesyntax.Storage;
import java.util.Arrays;

public class StorageImpl<K, V> implements Storage<K, V> {
    private static final int STORAGE_SIZE = 10;
    private static final Object EMPTY = new Object();
    private final K[] keys;
    private final V[] values;
    private int currentSize;

    @SuppressWarnings("unchecked")
    public StorageImpl() {
        keys = (K[]) new Object[STORAGE_SIZE];
        values = (V[]) new Object[STORAGE_SIZE];
        Arrays.fill(keys, EMPTY);
        Arrays.fill(values, null);

        currentSize = -1;
    }

    @Override
    public void put(K key, V value) {
        int keyIndex = findKeyIndex(key);

        if (keyIndex != -1) {
            keys[keyIndex] = key;
            values[keyIndex] = value;
            return;
        }

        currentSize++;
        keys[currentSize] = key;
        values[currentSize] = value;
    }

    @Override
    public V get(K key) {
        int keyIndex = findKeyIndex(key);
        if (keyIndex == -1) {
            return null;
        }
        return values[keyIndex];
    }

    @Override
    public int size() {
        return currentSize + 1;
    }

    private int findKeyIndex(K key) {
        for (int i = 0; i < keys.length; i++) {
            if (key == keys[i] || (keys[i] != null && keys[i].equals(key))) {
                return i;
            }
        }
        return -1;
    }
}
