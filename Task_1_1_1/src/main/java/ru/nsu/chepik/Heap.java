package ru.nsu.chepik;

/**
 * Main class.
 */
public class Heap {
    private int size;
    private int capacity;
    private int[] heap;

    /**
     * Heap.
     *
     * @param capacity
     */
    public Heap(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.heap = new int[capacity];
    }

    /**
     * swap.
     *
     * @param ind1
     * @param ind2
     */
    private void swap(int ind1, int ind2) {
        int temp = heap[ind1];
        heap[ind1] = heap[ind2];
        heap[ind2] = temp;
    }

    /**
     * parent.
     *
     * @param v
     * @return
     */
    private int parent(int v) {
        return (v - 1) / 2;
    }

    /**
     * leftChild.
     *
     * @param v
     * @return
     */
    private int leftChild(int v) {
        if (size < v * 2 + 1) {
            return -1;
        }

        return v * 2 + 1;
    }

    /**
     * rightChild.
     *
     * @param v
     * @return
     */
    private int rightChild(int v) {
        if (size < v * 2 + 2) {
            return -1;
        }

        return v * 2 + 2;
    }

    /**
     * siftUp.
     *
     * @param v
     */
    private void siftUp(int v) {
        if (v == 0) {
            return;
        }

        if (heap[v] < heap[parent(v)]) {
            swap(v, parent(v));
            siftUp(parent(v));
        }
    }

    /**
     * sfitDown.
     *
     * @param ind
     */
    private void siftDown(int ind) {
        int rightChild = rightChild(ind);
        int leftChild = leftChild(ind);
        int now = ind;

        if (rightChild != -1 && heap[rightChild] < heap[now]) {
            now = rightChild;
        }

        if (leftChild != -1 && heap[leftChild] < heap[now]) {
            now = leftChild;
        }

        if (now != ind) {
            swap(ind, now);
            siftDown(now);
        }
    }

    /**
     * extractMin.
     *
     * @return
     */
    public int extractMin() {
        int answer = heap[0];
        heap[0] = heap[--size];
        siftDown(0);
        return answer;
    }

    /**
     * add.
     *
     * @param value
     */
    public void add(int value) {
        heap[size] = value;
        size++;
        siftUp(size - 1);
    }

    /**
     * Создание и сортировка кучи
     *
     * @param nums - unsorted array
     * @return sorted array
     */
    static public int[] heapSort(int[] nums) {
        if (nums.length == 0) {
            return new int[]{};
        }

        Heap heap = new Heap(nums.length);
        for (int num : nums) {
            heap.add(num);
        }

        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = heap.extractMin();
        }

        return res;
    }

    /**
     * main.
     *
     * @param args
     */
    public static void main(String[] args) {
        int n = 5;
    }
}
