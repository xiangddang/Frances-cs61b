public class LinkedListDeque<T> {

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        if(size == 0){
            sentinel.item = item;
            size ++;
        }else{
            sentinel = new Node(sentinel, item, sentinel.next);
            sentinel.next.next.prev = sentinel.next;
            size ++;
        }
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        sentinel.prev = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    /**
     * Returns true if deuqe is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return this.size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        Node pos = sentinel.next;
        for (int i = 0; i < size - 1; i++) {
            System.out.print(pos.item + "");
            pos = pos.next;
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null
     */
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        Node temp = sentinel.next;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size --;
        return temp.item;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null
     */
    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        Node temp = sentinel.prev;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return temp.item;

    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such
     * item exists, returns null
     */
    public T get(int index) {
        Node pos = sentinel.next;
        if (index >= size) {
            return null;
        }
        for (int i = 0; i < index; i++) {
            pos = pos.next;
        }
        return pos.item;
    }

    //** Same as get, but uses recursion.*/
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursive(0, index, sentinel.next);
    }

    private T getRecursive(int pos, int index, Node x) {
        if (pos == index) {
            return x.item;
        }
        return getRecursive(pos + 1, index, x.next);
    }

    private class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
}
