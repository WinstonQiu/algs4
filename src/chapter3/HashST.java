package chapter3;

public interface HashST<Key, Value> {
    void put(Key key, Value val);
    Value get(Key key);
    void delete(Key key);
    default boolean contains(Key key) {
        return get(key) != null;
    }
    default boolean isEmpty() {
        return size() == 0;
    }
    int size();
    Iterable<Key> keys();
}
