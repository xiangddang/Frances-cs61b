public class ArrayDeque<T> {
    private T[] items;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        this.size = 0;
    }
    public ArrayDeque(T[] items, int size){
        this.items = items;
        this.size = size;
    }

    public int getLength(){
        return items.length;
    }


    /** if the usage of the array is over 50%, double the size of the array
     * if the usage of the array is less than 50%, shrink the length in half;
     */

    private void arrayCheckModify(){
        if(this.size == 0){
            return;
        }
        if(items.length / size < 2){
            T[] temp = (T[]) new Object[items.length * 2];
            System.arraycopy(items, 0, temp, 0, size);
            items = temp;
        }
        if(items.length >= 16) {
            if (items.length / size > 4) {
                T[] temp = (T[]) new Object[items.length / 2];
                System.arraycopy(items, 0, temp, 0, size);
                items = temp;
            }
        }
    }

    /**
     * add the item at the front of the array.
     */
    public void addFirst(T item) {
        arrayCheckModify();
        for(int i = this.size - 1; i >= 0; i--) {
            items[i + 1] = items[i];
        }
        items[0] = item;
        size++;
    }

    /**
     * add the item at the end of the array.
     */
    public void addLast(T item) {
        arrayCheckModify();
        items[size] = item;
        size ++;
    }

    public boolean isEmpty() {
        if(size == 0){
            return true;
        }else{
            return false;
        }
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        for (int i = 0; i < this.size; i ++) {
            System.out.print(items[i] + "");
        }
    }

    public T removeFirst() {
        T res = items[0];
        for(int i = 0; i < this.size; i++){
            items[i] = items[i + 1];
        }
        size --;
        arrayCheckModify();
        return res;
    }
    public T removeLast(){
        T res = items[size - 1];
        size --;
        arrayCheckModify();
        return res;
    }
    public T get(int index){
        if(index > 0 && index < this.size){
            return items[index];
        }else{
            return null;
        }
    }

    /** using main method to test the codes. */
    public static void main(String[] args) {
        ArrayDeque<Integer> myArray = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            myArray.addLast(i);
        }

        System.out.println(myArray.size() + " and " + myArray.getLength());
        for (int i = 0; i < 5; i++) {
            myArray.addLast(i);
        }
        System.out.println(myArray.size()+" and "+myArray.getLength());

        for (int i = 0; i < 100; i++) {
            myArray.addLast(i);
        }
        System.out.println(myArray.size()+" and "+myArray.getLength());

        for (int i = 0; i < 65; i++) {
            myArray.removeLast();
        }
        System.out.println(myArray.size()+" and "+myArray.getLength());
    }
}
