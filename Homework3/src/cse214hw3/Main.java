package cse214hw3;

import java.lang.reflect.Array;

public class Main {
    @SafeVarargs
    private static <T extends Comparable<T>> void addAllInThisOrder(BTree<T> theTree, T... items) {
        for (T item : items)
            theTree.add(item);
    }

    public static void main(String[] args) {
        BTree<Integer> numTree = new BTree<>(2);
        addAllInThisOrder(numTree, 5, 0, 11, 17, 30);
        System.out.println(numTree);


        Array [Integer] a = new int[];

        for(int i = 0; i < a.length; )

    }


}