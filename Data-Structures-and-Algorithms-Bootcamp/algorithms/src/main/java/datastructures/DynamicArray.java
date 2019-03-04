package datastructures;

public class DynamicArray<T> {

    private Object[] data;
    private int size;
    private int initialCapacity;

    public DynamicArray(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        data = new Object[initialCapacity];
    }

    public T get(int index) {
        return (T) data[index];
    }

    public void set(int index, T value) {
        data[index] = value;
    }

    public void add(T value) {
        if (size == initialCapacity) {
            resize();
        }

        data[size] = value;
        size++;
    }

    public void insert(int index, T value) {
        // Check size
        if (size == initialCapacity) {
            resize();
        }

        // Copy up
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        // Insert
        data[index] = value;
        size++;
    }

    public void delete(int index) {
        // Copy down
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean Contains(T value) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    private void resize() {
        Object[] newData = new Object[initialCapacity * 2];

        for (int i = 0; i < initialCapacity; i++) {
            newData[i] = data[i];
        }
        data = newData;
        initialCapacity *= 2;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println("data[i] = " + data[i]);
        }
    }

}
