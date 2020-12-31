package chapter3;

public interface STI<Key extends Comparable<Key>, Value> {
    void put(Key key, Value val);   // 将键值对存入表中
    Value get(Key key);             // 获取指定键对应的值，若不存在返回null
    void delete(Key key);           // 从表中删除指定键
    boolean contains(Key key);      // 指定键是否存在于表中
    boolean isEmpty();              // 表是否为空
    int size();                     // 表中的键值对数量
    int size(Key lo, Key hi);       // 指定范围内键的数量
    Key min();                      // 最小键
    Key max();                      // 最大键
    Key floor(Key key);             // 小于等于指定键的最大键
    Key ceiling(Key key);           // 大于等于指定键的最小键
    int rank(Key key);              // 返回小于被查找键的键的数量
    Key select(int k);              // 排名为k的键
    void deleteMin();               // 删除最小的键
    void deleteMax();               // 删除最大的键
    Iterable<Key> keys(Key lo, Key hi);// 指定范围内所有键，已排序
    Iterable<Key> keys();           // 表中所有键的集合，已排序
}
