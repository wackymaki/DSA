import java.util.Iterator;

@SuppressWarnings("unchecked")
public class Array<T> implements Iterable<T> {
    private T[] arr;
    private int len = 0; // length user thinks array is
    private int capacity = 0; // Actual array size

    public Array(){
        this(16);
    }

    public Array(int capacity){//instantiate Array object with given capacity
        if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size(){ //return size of array
        return len;
    }

    public boolean isEmpty(){ //checks if array is empty
        return size() == 0;
    }

    public T get(int index){ //returns the element at the specified index
        return arr[index];
    }

    public void set(int index, T elem){ //modifies the element at the specified index
        arr[index] = elem;
    }

    public void clear(){ // clears contents of array
        for(int i = 0; i < capacity; i++){
            arr[i] = null;
        }
        len = 0;
    }

    public void add(T elem){
        // conditional structure to check if added element will exceed capacity
        if (len + 1 >= capacity){
            if(capacity == 0)
                capacity = 1;
            else
                capacity *= 2; // double capacity of the original array
            T[] new_arr = (T[]) new Object[capacity]; // create new array with double capacity
            for(int i = 0; i < len; i++)
                new_arr[i] = arr[i]; // copy contents of original array
            arr = new_arr; // rewrite new array as base array
        }

        arr[len++] = elem; // add the new element
    }

    public T removeAt(int rm_index){
        if(rm_index >= len || rm_index < 0)
            throw new IndexOutOfBoundsException(); // return exception if requested index is invalid
        T data = arr[rm_index]; // stores removed element at specified index
        T[] new_arr = (T[]) new Object[len - 1]; // creates new array with reduced size
        for(int  i = 0, j = 0; i < len; i++, j++){
            if (i == rm_index)
                j--; // moves pointer a step back if specified index is found
            else
                new_arr[j] = arr[i]; // copies element from original array
        }
        arr = new_arr;
        capacity = --len;
        return data;
    }

    public boolean remove(Object obj){ // remove a specified object from the array
        for(int i = 0; i < len; i++){
            if(arr[i].equals(obj)){
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(Object obj){
        for(int i = 0; i < len; i++){
            if(arr[i].equals(obj))
                return i;
        }
        return -1;
    }

    public boolean contains(Object obj){
        return indexOf(obj) != -1;
    }

    @Override public Iterator<T> iterator() {
        return new Iterator<T>(){
            int index = 0;
            public boolean hasNext(){
                return index < len;
            }
            public T next(){
                return arr[index++];
            }
        };
    }

    @Override public String toString(){ //override toString list
        if(len == 0)
            return "[]";
        else{
            StringBuilder sb = new StringBuilder(len).append("[");
            for(int i = 0; i < len - 1; i++){
                sb.append(arr[i] + ", ");
            }
            return sb.append(arr[len-1] + "]").toString();
        }
    }
    
}
