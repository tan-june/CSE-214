package cse214hw3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Node<E extends Comparable<E>> {

    ArrayList<E> elements; //array of data values
    int minDegree;
    ArrayList<Node<E>> children; //list of children of this node
    int keySize; // number of data values to be filled - min Degree*2
    boolean isLeaf;

    public Node(int k, boolean isLeaf) {
        if (k < 2) {
            k = 2;
        }
        minDegree = k;
        this.isLeaf = isLeaf;
        elements = new ArrayList<E>(((2 * minDegree) - 1));
        this.children = new ArrayList<Node<E>>(((2 * minDegree)));
        this.keySize = 0;
    }

    public NodeIndexPair<E> search(E key, Node<E> node) {
        int i = 0;
        while (i < node.keySize && node.elements.get(i).compareTo(key) < 0)
            ++i;
        if (i < node.keySize && key.compareTo(node.elements.get(i)) == 0)
            return new NodeIndexPair(node, i);
        if (node.isLeaf)
            return new NodeIndexPair(null, -1);
        return search(key, node.children.get(i));
    }

    public boolean isLeaf() {
        return children.size() == 0;
    }

    public boolean isFull() {
        return elements.size() == (2 * minDegree) - 1;
    }

    public void updateKeySize() {
        keySize = elements.size();
    }

    public void insertNotFull(E inputKey) {
        updateKeySize();
        int i = keySize - 1;
        if (isLeaf) {
            while (i >= 0 && elements.get(i).compareTo(inputKey) > 0) {
                i--;
            }
            this.elements.add(i + 1, inputKey);
        } else {
            while (i >= 0 && inputKey.compareTo(elements.get(i)) < 0) {
//possible issue here
                i--;
            }
            children.get(i + 1).updateKeySize();
            if (children.get(i + 1).keySize == 2 * minDegree - 1) {
                splitChild(i + 1, children.get(i + 1));
                if (elements.get(i + 1).compareTo(inputKey) < 0) {
                    i++;
                }
            }
            //elements < element
            children.get(i + 1).insertNotFull(inputKey);
        }
        updateKeySize();
    }



    public void splitChild(int i, Node<E> y) {
        if (minDegree % 2 == 1) {
            Node<E> z = new Node<E>(y.minDegree, y.isLeaf);
            y.updateKeySize();
            z.updateKeySize();
            z.keySize = minDegree - 1;
            int h = 0;
            for (int j = 0; j < minDegree - 1; j++) {
                z.elements.add(j, y.elements.get(j + minDegree));
                h++;
            }
            for (int z1 = minDegree + 1; z1 >= h + 1; z1--) {
                y.elements.remove(z1);
            }
            if (!y.isLeaf()) {
                for (int j = 0; j < minDegree; j++) {
                    z.children.add(j, y.children.get(j + minDegree));
                }
            }
            y.keySize = minDegree - 1;

            for (int j = keySize; j > i + 1; j--) {
                children.set(j + 1, children.get(j));
            }
            for (int j = keySize - 1; j > i; j--) {
                elements.set(j + 1, elements.get(j));
            }
            children.add(i + 1, z);


            elements.add(i, y.elements.get(minDegree - 1));
            y.elements.remove(minDegree - 1);
            updateKeySize();
        } else {
            Node<E> z = new Node<E>(y.minDegree, y.isLeaf);
            y.updateKeySize();
            z.updateKeySize();
            z.keySize = minDegree - 1;
            int h = 0;
            for (int j = 0; j < minDegree - 1; j++) {
                z.elements.add(j, y.elements.get(j + minDegree));
                h++;
            }
            for (int z1 = minDegree; z1 >= h + 1; z1--) {
                y.elements.remove(z1);
            }
            if (!y.isLeaf()) {
                for (int j = 0; j < minDegree; j++) {
                    z.children.add(j, y.children.get(j + minDegree));
                }
            }
            y.keySize = minDegree - 1;

            for (int j = keySize; j > i + 1; j--) {
                children.set(j + 1, children.get(j));
            }
            for (int j = keySize - 1; j > i; j--) {
                elements.set(j + 1, elements.get(j));
            }
            children.add(i + 1, z);


            elements.add(i, y.elements.get(minDegree - 1));
            y.elements.remove(minDegree - 1);
            updateKeySize();
        }
    }

    @Override
    public String toString() {
        return toString(0);
    }

    // based on what toString() does, think about what ‘elements’ and ‘children’ can be
    private String toString(int depth) {
        StringBuilder builder = new StringBuilder();
        String blankPrefix = new String(new char[depth]).replace("\0", "\t");
        List<String> printedElements = new LinkedList<>();
        for (E e : elements) printedElements.add(e.toString());
        String eString = String.join(" :: ", printedElements);
        builder.append(blankPrefix).append(eString).append("\n");
        children.forEach(c -> builder.append(c.toString(depth + 1)));
        return builder.toString();
    }
}
