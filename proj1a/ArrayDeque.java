public class ArrayDeque<T> {
    private T[] items;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        this.size = 0;
    }

    /**
     * if the usage of the array is over 50%, double the size of the array
     * if the usage of the array is less than 50%, shrink the length in half;
     */

    private void resize(int cap) {
        T[] a = (T[]) new Object[cap];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    /**
     * add the item at the front of the array.
     */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        T[] a = (T[]) new Object[items.length];
        a[0] = item;
        System.arraycopy(items, 0, a, 1, size);
        items = a;
        size++;
    }
    /**
     public static void main(String[] args) {
     ArrayDeque a = new ArrayDeque();
     a.addFirst(1);
     a.addFirst(2);
     a.addFirst(3);
     a.addFirst(4);
     a.addFirst(5);
     a.addFirst(6);
     a.addLast(1);
     a.addLast(2);
     a.addLast(3);
     a.addLast(4);
     a.addLast(5);
     a.removeFirst();
     a.removeFirst();
     a.removeLast();
     a.removeLast();

     }
     */

    /**
     * add the item at the end of the array.
     */
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = item;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(items[i] + "");
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T res = items[0];
            T[] a = (T[]) new Object[items.length];
            System.arraycopy(items, 1, a, 0, size - 1);
            items = a;
            size--;
            return res;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T res = items[size - 1];
            T[] a = (T[]) new Object[items.length];
            System.arraycopy(items, 0, a, 0, size - 1);
            items = a;
            size--;
            return res;

        }
    }

    public T get(int index) {
        if (index >= 0 && index < this.size) {
            return items[index];
        } else {
            return null;
        }
    }
}
