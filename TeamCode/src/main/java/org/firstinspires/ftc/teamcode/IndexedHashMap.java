package org.firstinspires.ftc.teamcode;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

/*
* This is a HashMap that keeps track on indexes, which we use in SettingsMenu.java
* We need this because we use indexes for cursor movement, but k/v pairs for display.
*/
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

    public V get(int index) {
        return this.get(keys.get(index));
    }

    public V remove(int index) {
        return this.remove(keys.get(index));
    }
}
