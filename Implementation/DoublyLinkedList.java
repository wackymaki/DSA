import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {
    //DLL attributes
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    //Inner Node Class
    private static class Node<T>{
        //Node Attributes
        private T data;
        private Node<T> prev, next;

        public Node(T data, Node<T> prev, Node<T> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override public String toString(){
            return data.toString();
        }
    }
    //Empty DLL
    public void clear(){
        Node<T> trav = head;
        while(trav != null){
            Node<T> next = trav.next;
            trav.prev = trav.next = null;
            trav.data = null;
            trav = next;
        }

        head = tail = trav = null;
        size = 0;
    }

    //return size of DLL
    public int size(){
        return size;
    }

    //check if DLL is empty
    public boolean isEmpty(){
        return size() == 0;
    }

    //add element add the tail of the list
    public void add (T elem){
        addLast(elem);
    }

    //specifically add element at tail of the list
    public void addLast(T elem){
        if(isEmpty())
            head = tail = new Node<T>(elem, null, null);
        else{
            tail.next = new Node<T>(elem, tail, null);
            tail = tail.next;
        }
        size++;
    }

    //specifically add element at head of the list
    public void addFirst(T elem){
        if(isEmpty())
            head = tail = new Node<T>(elem, null, null);
        else{
            head.prev = new Node<T>(elem, null, head);
            head = head.prev;
        }
        size++;
    }

    //return the value of the head
    public T peekFirst(){
        if(isEmpty())
            throw new RuntimeException("Empty list");
        return head.data;
    }

    //return the value of the tail
    public T peekLast(){
        if(isEmpty())
            throw new RuntimeException("Empty list");
        return tail.data;
    }

    //remove head node
    public T removeFirst(){
        if(isEmpty())
            throw new RuntimeException("Empty list");
        T data = head.data;
        head = head.next;
        --size;

        if(isEmpty())
            tail = null;
        else
            head.prev = null;
        
        return data;
    }

    //remove tail node
    public T removeLast(){
        if(isEmpty())
            throw new RuntimeException("Empty list");
        T data = tail.data;
        tail = tail.prev;
        --size;

        if(isEmpty())
            head = null;
        else
            tail.next = null;
        
        return data;
    }

    //remove specific node
    private T remove(Node<T> node){
        if(node.prev == null)
            return removeFirst();
        if(node.next == null)
            return removeLast();
        
        //reassigns pointers of previous and next nodes of deleted node
        node.next.prev = node.prev;
        node.prev.next = node.next;

        T data = node.data;
        node.data = null;
        node = node.prev = node.next = null;
        --size;

        return data;
    }

    //remove node at specified index
    public T removeAt(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException();
        
        int i;
        Node<T> trav;

        //searching for node from head to tail
        if(index < size / 2){
            for(i = 0, trav = head; i != index; i++)
                trav = trav.next;
        }else // searching for node from tail to head
            for(i = size - 1, trav = tail; i != index; i--)
                trav = trav.prev;
        
        return remove(trav);
    }

    //remove a specific value in the linked list
    public boolean remove(Object obj){
        Node<T> trav = head;

        if(obj == null){
            for(trav = head; trav != null; trav = trav.next){
                if(trav.data == null){
                    remove(trav);
                    return true;
                }
            }
        }else {
            for (trav = head; trav != null; trav = trav.next){
                if (obj.equals(trav.data)){
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    //return index of specified object
    public int indexOf(Object obj){
        int index = 0;
        Node<T> trav = head;

        if(obj == null){
            for(; trav != null; trav = trav.next, index++){
                if(trav.data == null){
                    return index;
                }
            }
        }else{
            for(; trav != null; trav = trav.next, index++){
                if(obj.equals(trav.data)){
                    return index;
                }
            }
        }

        return -1;
    }

    //return if DLL contains the specified object
    public boolean contains(Object obj){
        return indexOf(obj) != -1;
    }
    
    @Override public Iterator<T> iterator(){
        return new Iterator<T>(){
            private Node<T> trav = head;

            @Override public boolean hasNext(){
                return trav != null;
            }

            @Override public T next(){
                T data = trav.data;
                trav = trav.next;
                return data;
            }

            @Override public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<T> trav = head;
        while(trav != null){
            sb.append(trav.data + ", ");
            trav = trav.next;
        }
        sb.append(" ]");
        return sb.toString();
    }
}
