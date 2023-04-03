package cse214hw4;

import java.util.function.Function;

public class DoubleHashTable implements OpenAddressTable {
    int sizeOfTable;
    Integer[] data;
    Function<Integer, Integer> h1;
    Function<Integer, Integer> h2;

    public DoubleHashTable(int size, Function h1, Function h2) {
        this.sizeOfTable = size;
        data = new Integer[size];
        this.h1 = h1;
        this.h2 = h2;
        for (int i = 0; i < sizeOfTable; ++i) {
            data[i] = null;
        }
    }

    @Override
    public void insert(int value) {
        Integer value1 = value;
        if (value1 == null) {
            throw new NullPointerException("Value provided to be inserted is NULL!");
        }
        if (isFull()) {
            System.out.println("Table is Full!");
            return;
        }
        int idx = h1.apply(value) % sizeOfTable;
        if (data[idx] != null) {

            int i = 1;
            while (data[idx] != null) {
                int newidx = hash(value, i);
                if (i > sizeOfTable) {
                    return;
                }
                if (data[newidx] == null) {
                    data[newidx] = value;
                    break;
                }
                i++;
            }
        } else {
            data[idx] = value;
        }
    }

    @Override
    public double loadFactor() {
        return getSize() / sizeOfTable;
    }

    @Override
    public int hash(int key, int probenumber) {
        return (h1.apply(key) + probenumber * h2.apply(key)) % sizeOfTable;
    }

    @Override
    public int find(int value) {
        int idx = h1.apply(value) % sizeOfTable;
        if (data[idx] == value) {
            return idx;
        } else {
            int idx2 = h2.apply(value);
            int i = 1;
            while (data[idx] != null) {
                int newidx = hash(value, i);
                if (i > sizeOfTable) {
                    return -1;
                }
                if (data[newidx] == null) {
                    break;
                }
                if (data[newidx] == value) {
                    return newidx;
                }
                i++;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        for (int j = 0; j < sizeOfTable; j++) {
            if (data[j] != null)
                System.out.print(j + " -> " + data[j] + ", ");
        }
        return "";
    }

    private double getSize() {
        int value = 0;
        for (int i = 0; i < sizeOfTable; i++) {
            if (data[i] != null) {
                value++;
            }
        }
        return value;
    }

    private boolean isFull() {
        return getSize() == sizeOfTable;
    }

    public static void main(String[] args) {
        Function<Integer, Integer> h1 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return Integer.parseInt(Integer.toString(integer).substring(0, 1));
            }
        };

        Function<Integer, Integer> h2 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer % 10;
            }
        };

        DoubleHashTable table = new DoubleHashTable(5, h1, h2);
        table.insert(4);
        table.insert(5);
        table.insert(21);
        table.insert(2);
        System.out.println(table.toString());
    }
}
