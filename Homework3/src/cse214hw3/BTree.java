package cse214hw3;

import java.util.NoSuchElementException;

public class BTree<E extends Comparable<E>> implements AbstractBTree<E> {

    Node<E> root;
    private static int minimumDegree;

    public BTree(int minimumDegree) {
        BTree.minimumDegree = minimumDegree;
        root = new Node<>(minimumDegree, true);
    }


    public static int getMinimumDegree() {
        return minimumDegree;
    }

    @Override
    public NodeIndexPair<E> contains(E element) {
        if (element == null) {
            throw new NoSuchElementException("Element you are searching for is null!");
        }
        NodeIndexPair z = contains(root, element);
        if (z.nodeLocation == null) {
            return null;
        }
        return z;
    }

    private NodeIndexPair<E> contains(Node<E> node, E element) {
        return root.search(element, root);
    }

    public boolean checkElementExists(E key) {
        NodeIndexPair<E> contains = contains(root, key);
        return contains.nodeLocation != null;
    }

    @Override
    public void add(E element) {
        if (root == null) {
            root = new Node<E>(minimumDegree, true);
            root.elements.add(0, element);
            root.updateKeySize();
        } else {
            if (checkElementExists(element)) {
                return;
            }
            root.updateKeySize();
            if (root.keySize == (2 * minimumDegree) - 1) {
                Node<E> s = new Node<E>(minimumDegree, false);
                s.children.add(0, root);
                s.splitChild(0, root);
                int i = 0;
                if (s.elements.get(0).compareTo(element) < 0)
                    i++;
                s.children.get(i).insertNotFull(element);
                root = s;
            } else {
                root.insertNotFull(element);
            }
        }
    }

    public String toString() {
        return root.toString();
    }

}