package com.epam.mylinkedhashmap;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyLHM<K, V> implements Externalizable {
    private List<K> list;
    private Map<K, V> map;

    public MyLHM() {
        list = new LinkedList<K>();
        map = new HashMap<K, V>();
    }

    public V put(K key, V value) {
        changeOrder(key);
        V currentValue = map.put(key, value);
        return currentValue;
    }

    private void changeOrder(K key) {
        if (containsKey(key)) {
            list.remove(list.indexOf(key));
        }
        list.add(key);
    }

    public boolean removeEldestEntry(Object o) {
        return false;
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        }
        changeOrder(key);
        return map.get(key);
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(list);
        out.writeObject(map);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.list = (List<K>) in.readObject();
        this.map = (Map<K, V>) in.readObject();
    }

    public String toString(){
        StringBuilder builder = new StringBuilder("{");
        for(K k: list) {
            builder.append("[").append(k).append("->").append(map.get(k)).append("]");
        }
        builder.append("}");
        return builder.toString();
    }

}
