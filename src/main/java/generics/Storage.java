package generics;

class Storage<T> {
    private T x;

    public T getValue() {
        return x;
    }

    public void setValue(T value) {
        x = value;
    }
}