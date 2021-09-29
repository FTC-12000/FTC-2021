package org.firstinspires.ftc.teamcode;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class IndexedHashMap<K, V> extends HashMap<K, V> {
    public ArrayList<K> keys = new ArrayList<>();

    @Nullable
    @Override
    public V put(K key, V value) {
        if (!keys.contains(key)) {
            keys.add(key);
        }
        return super.put(key, value);
    }

    @Nullable
    @Override
    public V get(@Nullable Object key) {
        return super.get(key);
    }

    @Nullable
    @Override
    public V remove(@Nullable Object key) {
        keys.remove(key);
        return super.remove(key);
    }

    public V getByIndex(int index) {
        return this.get(keys.get(index));
    }

    public V removeByIndex(int index) {
        return this.remove(keys.get(index));
    }
}
