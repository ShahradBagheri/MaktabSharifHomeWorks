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
