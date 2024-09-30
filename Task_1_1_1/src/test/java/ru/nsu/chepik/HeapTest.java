package ru.nsu.chepik;

import java.util.Arrays;
import java.util.SplittableRandom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class for tests.
 */
public class HeapTest {

    private static int[] generateRandomArray(int size, int minValue, int maxValue) {
        SplittableRandom random = new SplittableRandom();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(minValue, maxValue + 1);
        }
        return array;
    }

    public static void runTests(int n, int arraySize, int minValue, int maxValue) {
        for (int i = 0; i < n; i++) {
            int[] randomArray = generateRandomArray(arraySize, minValue, maxValue);

            int[] arrayForStandardSort = Arrays.copyOf(randomArray, randomArray.length);

            int[] arrayForHeapSort = Arrays.copyOf(randomArray, randomArray.length);

            Arrays.sort(arrayForStandardSort);

            int[] heapSortedArray = Heap.heapSort(arrayForHeapSort);

            assertArrayEquals(arrayForStandardSort, heapSortedArray);
        }

        System.out.println("All " + n + " tests passed successfully!");
    }

    @Test
    void n_tests() {
        int n = 1000;
        int arraySize = 100000;
        int minValue = -1000000;
        int maxValue = 1000000;

        runTests(n, arraySize, minValue, maxValue);
    }

    @Test
    void main_test() {
        Heap.main(null);
        assertTrue(true);
    }

    @Test
    void test_sort_tree_numbers() {
        int[] input = new int[]{1, 2, 0};
//        Heap heap = new Heap();
        int[] res = Heap.heapSort(input);
        assertArrayEquals(new int[]{0, 1, 2}, res);
    }

    @Test
    void empty_array() {
        int[] input = new int[]{};
//        Heap heap = new Heap();
        int[] res = Heap.heapSort(input);
        assertArrayEquals(new int[]{}, res);
    }
}
