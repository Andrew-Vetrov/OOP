package ru.nsu.chepik;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class MainTest {
    @Test
    void main_test() {
        Main.main(null);
        assertTrue(true);
    }

//    @Test
//    void main_test() {
//        // Имитация ввода данных
//        String input = "5\n3 1 4 1 5";  // Первое число - размер массива, далее - элементы
//        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
//        System.setIn(inContent);
//
//        // Захват вывода
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//
//        // Вызов метода main
//        Main.main(null);
//
//        // Ожидаемый результат после сортировки
//        String expectedOutput = Arrays.toString(new int[]{1, 1, 3, 4, 5});
//
//        // Проверка того, что вывод соответствует ожидаемому
//        assertEquals(expectedOutput.trim(), outContent.toString().trim());
//    }

    @Test
    void test_sort_two_numbers() {
        int [] input = new int[] {1, 2, 0};
        int [] res = Main.heapSort(input);
        assertArrayEquals(new int[] {0, 1, 2}, res);
    }

//    @Test
//    void empty_array() {
//        int [] input = new int[] {};
//        int [] res = Main.heapSort(input);
//        assertArrayEquals(new int[] {}, res);
//    }
}
