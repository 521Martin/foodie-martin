package com.imooc.result;

/**
 *
 */
public final class Tuple<Key, Value> {

    private Key key;
    private Value value;

    public Tuple(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public Tuple() {

    }

    public static <Left, Right> Tuple<Left, Right> of(Left left, Right right) {
        return new Tuple<>(left, right);
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

}
