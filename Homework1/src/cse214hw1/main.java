package cse214hw1;

public class main {
    public static void main(String[] args){

        DoublyLinkedList list = new DoublyLinkedList<>();
        DoublyLinkedList list1 = new DoublyLinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        System.out.println(list.toString());
        System.out.println(list.size());
        list.remove(2);
        list.add(4,412312);
        System.out.println(list.toString());
        System.out.println(list.size());
        list.remove(2);
        System.out.println(list.toString());
        System.out.println(list.size());

    }
}
