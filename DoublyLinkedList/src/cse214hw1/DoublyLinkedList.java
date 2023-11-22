package cse214hw1;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements CollectionType<T>, ListAbstractType<T> {
    private static class Node<T> { //figure out if you want to make the node class static
        private T t;
        private Node<T> next;
        private Node<T> previous;


        public Node() {
            t = null;
            next = null;
            previous = null;
        }

    }

    private Node<T> head;
    private Node<T> tail;
    private int sizeofArray = 0;

    public DoublyLinkedList() {
        head = new Node<>();
        tail = new Node<>();
        head.next = null;
        tail.previous = null;
    }

    public DoublyLinkedList(int a) {
        head = new Node<>();
        tail = new Node<>();
        head.next = tail;
        tail.next = head;
    }

    //Collection Type
    @Override
    public int size() {

        return sizeofArray;
    }

    @Override
    public boolean isEmpty() {

        return size() == 0;
    }

    @Override
    public boolean add(T element) {

        Node<T> addNode = new Node<>();
        addNode.t = element;

        if (sizeofArray == 0) {
            tail = addNode;
            head = addNode;
        } else {
            tail.next = addNode;
            addNode.previous = tail;
            tail = addNode;
        }
        sizeofArray++;
        return true;
    }

    @Override
    public boolean contains(T element) {
        Node<T> n = head;
        while (n.next != null) {
            if (n.t != element) {
                return true;
            }
            n = n.next;
        }
        return n.t == element;
    }


        public boolean remove(T element) {

        Node<T> n = head;
        for (int i = 0; i < sizeofArray; i++) {
            if (n.t == element) {
                remove(i);
                return true;
            } else {
                if (n.next == null) {
                    break;
                } else {
                    n = n.next;
                }
            }

        }
        sizeofArray--;
        return true;
    }

    //List Abstract Type
    @Override
    public void remove(int index) {
        if (index == 0) {
            head = head.next;
        } else if (index > sizeofArray) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            Node<T> n = head;
            Node<T> n1 = null;
            for (int i = 0; i < index - 1; i++)
                n = n.next;
            n1 = n.next;
            n.next = n1.next;
            n1 = null;
        }
        sizeofArray--;
    }

    @Override
    public T set(int index, T element) {
        if (index > sizeofArray - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node<T> n = head;
        for (int i = 0; i < index - 1; i++) {
            n = n.next;
        }
        n = n.next;
        T temp = n.t;
        n.t = element;

        return temp;

    }

    @Override
    public void add(int index, T element) {
        Node<T> node = new Node<>();
        node.t = element;
        node.next = null;

        if (index == 0) {
            addToStart(element);
        } else if (index >= sizeofArray - 1) {
            add(element);
        } else {
            Node<T> n = head;
            for (int i = 0; i < index - 1; i++) {
                n = n.next;
            }
            node.next = n.next;
            n.next = node;
            sizeofArray++;
        }
    }

    private void addToStart(T element) {
        Node<T> newHead = new Node<>();
        newHead.t = element;

        newHead.next = head;
        newHead.previous = null;
        if (head != null) {
            head.previous = newHead;
        }
        head = newHead;
        sizeofArray++;
    }

    @Override
    public T get(int index) {
        if (index > sizeofArray) {
            throw new ArrayIndexOutOfBoundsException();
        }

        Node<T> a = head;
        int currentIndex = 0;

        while (currentIndex != index && a.next != null) {
            currentIndex++;
            a = a.next;

        }
        return a.t;
    }

    @Override
    public TwoWayListIterator<T> iterator() {
        return new DoublyLinkedListIterator();
    }


    @Override
    public String toString() {
        Iterator<T> it = this.iterator();
        if (!it.hasNext())
            return "[]";
        StringBuilder builder = new StringBuilder("[");
        while (it.hasNext()) {
            T e = it.next();
            builder.append(e.toString());
            if (!it.hasNext())
                return builder.append("]").toString();
            builder.append(", ");
        }
        // code execution should never reach this line
        return null;
    }

   private class DoublyLinkedListIterator implements TwoWayListIterator<T> {

        private int currentIndex;
        private Node<T> current;
        private Node<T> lastAccessed;

        DoublyLinkedListIterator() {
            current = head;
            lastAccessed = null;
            currentIndex = 0;
        }

        @Override
        public boolean hasPrevious() {
            return currentIndex > 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException("Previous element does not exist!");
            }
            current = current.previous;
            currentIndex--;
            lastAccessed = current;
            return current.t;
        }


        @Override
        public void add(T element) {
            Node<T> addNode = new Node<>();
            addNode.t = element;
            Node<T> a = current.previous;
            Node<T> c = current;
            a.next = addNode;
            addNode.next = c;
            c.previous = addNode;
            addNode.previous = a;
            currentIndex++;
            sizeofArray++;
            lastAccessed = null;
        }

        @Override
        public void set(T element) {
            current.t = element;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size();
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T a = current.t;
            current = current.next;
            currentIndex++;
            return a;

        }


    }
}
