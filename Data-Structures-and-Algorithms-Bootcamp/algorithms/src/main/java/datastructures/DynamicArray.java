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
        // O(1)
    }

    public void set(int index, T value) {
        data[index] = value;
        // O(1)
    }

    public void add(T value) {
        if (size == initialCapacity) {
            resize();
            // O(1)
        }

        data[size] = value;
        size++;
        // O(1)

        // O(1)
    }

    public void insert(int index, T value) {
        // Check size
        if (size == initialCapacity) {
            resize();
            // O(1)
        }

        // Copy up
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
            // O(n)
        }

        // Insert
        data[index] = value;
        size++;
        // O(1)

        // O(n)
    }

    public void delete(int index) {
        // Copy down
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
            // O(n)
        }
        size--;

        // O(n)
    }

    public int size() {
        return size;
        // O(1)
    }

    public boolean isEmpty() {
        return size == 0;
        // O(1)
    }

    public boolean Contains(T value) {
        for (int i = 0; i < size; i++) {
            // O(n)
            if (data[i].equals(value)) {
                return true;
                // O(1)
            }
        }
        return false;

        // O(n)
    }

    private void resize() {
        Object[] newData = new Object[initialCapacity * 2];

        for (int i = 0; i < initialCapacity; i++) {
            newData[i] = data[i];
            // O(n)
        }
        data = newData;
        initialCapacity *= 2;

        // O(n)
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println("data[i] = " + data[i]);
        }
    }

}
