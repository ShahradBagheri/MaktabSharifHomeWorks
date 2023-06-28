import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CustomHashmap<K, V> {
    private int size;
    private int capacity;
    private Map<K,V>[] hashmap;

    public CustomHashmap(){
        this.size = 0;
        this.capacity = 16;
        hashmap = new Map[this.capacity];
    }

    public CustomHashmap(int capacity){
        this.size = 0;
        this.capacity = capacity;
        hashmap = new Map[capacity];
    }

    public void put(K key, V value){
        if (key == null)
            throw new RuntimeException("key cant be null");
        for (int i = 0; i < hashmap.length; i++) {
            if (hashmap[i].getKey() == key) {
                hashmap[i].setValue(value);
                return;
            }
        }
        hashmap[size] = new Map<>(key, value);
        size++;
        if (75 < this.size/this.capacity*100)
            resizeHashmap();
    }

    public V get(K key) {
        for (int i = 0; i < hashmap.length; i++) {
            if (hashmap[i].getKey() == key) {
                return hashmap[i].getValue();
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        for (int i = 0; i < hashmap.length; i++) {
            if (hashmap[i].getKey() == key) {
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public List<V> getAll(){
        List<V> outputList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            outputList.add(this.hashmap[i].getValue());
        }
        return outputList;
    }

    public void resizeHashmap(){
        Map<K,V>[] hashmap = Arrays.copyOf(this.hashmap,this.capacity*2);
        this.capacity = this.capacity*2;
        this.hashmap = hashmap;
    }

    private static class Map<K, V> {
        private K key;
        private V value;

        Map(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K getKey() {
            return key;
        }

        V getValue() {
            return value;
        }

        void setValue(V value) {
            this.value = value;
        }
    }
}
