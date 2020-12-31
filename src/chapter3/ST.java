package chapter3;

public abstract class ST<Key extends Comparable<Key>, Value> implements STI<Key, Value> {
    protected int N = 0;

    public abstract void put(Key key, Value val);

    public abstract Value get(Key key);

    public abstract void delete(Key key);

    // 指定键是否存在于表中
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // 表是否为空
    public boolean isEmpty() {
        return size() == 0;
    }

    // 表中的键值对数量
    public int size() {
        return N;
    }

    // 指定范围内键的数量
    public int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0) {
            return 0;
        } else if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    public abstract Key min();

    public abstract Key max();

    public abstract Key floor(Key key);

    public abstract Key ceiling(Key key);

    public abstract int rank(Key key);

    public abstract Key select(int k);

    // 删除最小的键
    public void deleteMin() {
        delete(min());
    }

    // 删除最大的键
    public void deleteMax() {
        delete(max());
    }

    public abstract Iterable<Key> keys(Key lo, Key hi);

    // 表中所有键的集合，已排序
    public Iterable<Key> keys() {
        return keys(min(), max());
    }
}
