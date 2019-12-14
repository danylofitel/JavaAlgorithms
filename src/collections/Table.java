package collections;

public interface Table<Key extends Comparable<Key>, Value> {

    void put(Key key, Value val);

    Value get(Key key);

    boolean delete(Key key);

    boolean contains(Key key);

    boolean isEmpty();

    int size();

    int size(Key lo, Key hi);

    Key select(int k);

    Key min();

    Key max();

    Key floor(Key key);

    Key ceiling(Key key);

    int rank(Key key);

    void deleteMin();

    void deleteMax();

    Iterable<Key> keys();

    Iterable<Key> keys(Key lo, Key hi);

    Iterable<Value> values();
}
