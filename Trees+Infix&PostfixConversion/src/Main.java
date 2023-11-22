import cse214hw2.DynamicIntegerSet;

import static cse214hw2.printTree.printTree;

public class Main {
    public static void main(String[] args) {

        DynamicIntegerSet a = new DynamicIntegerSet();
        a.add(2);
        printTree(a.root());
        a.remove(2);
        printTree(a.root());

    }

    }





