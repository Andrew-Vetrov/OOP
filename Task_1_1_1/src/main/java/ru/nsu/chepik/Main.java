package ru.nsu.chepik;

import java.util.Arrays;
import java.util.Scanner;

class Heap {
    private int size;
    private int capacity;
    private int[] heap;

    public Heap(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.heap = new int[capacity];
    }

    private void swap(int ind1, int ind2) {
        int temp = heap[ind1];
        heap[ind1] = heap[ind2];
        heap[ind2] = temp;
    }

    private int parent(int v) {
        return (v - 1) / 2;
    }

    private int leftChild(int v) {
        if (size < v * 2 + 1)
            return -1;
        return v * 2 + 1;
    }

    private int rightChild(int v) {
        if (size < v * 2 + 2)
            return -1;
        return v * 2 + 2;
    }

    private void siftUp(int v) {
        if (v == 0)
            return;

        if (heap[v] < heap[parent(v)]) {
            swap(v, parent(v));
            siftUp(parent(v));
        }
    }

    private void siftDown(int ind) {
        int rightChild = rightChild(ind);
        int leftChild = leftChild(ind);
        int now = ind;

        if (rightChild != -1 && heap[rightChild] < heap[now])
            now = rightChild;

        if (leftChild != -1 && heap[leftChild] < heap[now])
            now = leftChild;

        if (now != ind) {
            swap(ind, now);
            siftDown(now);
        }
    }

    public int extractMin() {
        int answer = heap[0];
        heap[0] = heap[--size];
        siftDown(0);
        return answer;
    }

    public void add(int value) {
        heap[size] = value;
        size++;
        siftUp(size - 1);
    }
}

public class Main {
    public static int[] heapSort(int[] nums) {
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

    public static void main(String[] args) {
        int n = 5;
//        Scanner scanner = new Scanner(System.in);
//
//        int n = scanner.nextInt();
//        int[] input = new int[n];
//
//        for (int i = 0; i < n; i++)
//            input[i] = scanner.nextInt();
//
//        int[] sorted = heapSort(input);
//
//        System.out.println(Arrays.toString(sorted));
//
//        scanner.close();
    }
}
