package ru.nsu.chepik;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Test
    void main_test() {
        Main.main(null);
        assertTrue(true);
    }

    @Test
    void test_sort_two_numbers() {
        int [] input = new int[] {1, 2, 0};
        int [] res = Main.heapSort(input);
        assertArrayEquals(new int[] {0, 1, 2}, res);
    }

    @Test
    void empty_array() {
        int [] input = new int[] {};
        int [] res = Main.heapSort(input);
        assertArrayEquals(new int[] {}, res);
    }
}
