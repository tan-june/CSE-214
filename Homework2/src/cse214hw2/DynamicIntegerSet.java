package cse214hw2;

public class DynamicIntegerSet implements DynamicSet {
    private Node node;

    @Override
    public boolean add(Integer x) {
        Node newNode = new Node(x);
        if (node == null) {
            node = newNode;
        } else if (contains(x)) {
            return false;
        } else {
            Node node1 = node;
            Node parent;

            while (true) {
                parent = node1;

                if (x < node1.data) {
                    node1 = node1.left;
                    if (node1 == null) {
                        parent.left = newNode;
                        return true;
                    }
                } else if (x > node1.data) {
                    node1 = node1.right;
                    if (node1 == null) {
                        parent.right = newNode;
                        return true;
                    }
                }
            }
        }
        return true;
    }

    public static void InOrderTraversal(Node node) {
        if (node != null) {
            InOrderTraversal(node.left);
            System.out.print(node.getValueAsString() + "  ");
            InOrderTraversal(node.right);
        }
    }

    @Override
    public boolean contains(Integer x) {
        return contains(x, node);

    }

    private boolean contains(Integer x, Node searchNode) {
        if (searchNode == null) {
            return false;
        }
        int searchValue = x.compareTo(searchNode.data);
        if (searchValue < 0)
            return contains(x, searchNode.left);
        else if (searchValue > 0)
            return contains(x, searchNode.right);
        else
            return true;
    }

    // this method must be there exactly in this form
    public Node root() {
        return this.node;
    }

    @Override
    public int size() {
        return size(node);
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        else
            return (size(node.left) + 1 + size(node.right));
    }

    @Override
    public boolean remove(Integer x) {
        return remove(node, x) != null;
    }

    private Node remove(Node root, Integer x) {
        if (root == null) {
            return null;
        }
        if (!contains(x)) {
            return null;
        }
        if (root.data > x) {
            root.left = remove(root.left, x);
        } else if (root.data < x) {
            root.right = remove(root.right, x);
        } else {
            if (root.left != null && root.right != null) {
                Node temp = root;
                Node minElementNode = minElement(temp.right);
                root.data = minElementNode.data;
                root.right = remove(root.right, minElementNode.data);
            } else if (root.left != null) {
                root = root.left;
            } else if (root.right != null) {
                root = root.right;
            } else {
                root = null;
            }
        }
        return root;
    }


    private Node minElement(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return minElement(node.left);
        }
    }

    public static class Node implements PrintableNode {
        Integer data;
        Node left;
        Node right;

        Node(int x) {
            data = x;
            left = null;
            right = null;
        }

        Node(int x, Node left, Node right) {
            this.data = x;
            this.left = left;
            this.right = right;
        }

        @Override
        public String getValueAsString() {
            return data.toString();
        }

        @Override
        public PrintableNode getLeft() {
            return left;
        }

        @Override
        public PrintableNode getRight() {
            return right;
        }
    }
}