import java.util.List;

public class CustomHashmap<K, V> {
    private int size;


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
