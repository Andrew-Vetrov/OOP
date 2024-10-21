package ru.nsu.chepik;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class NumberTest {

    @Test
    void testEvalMap() throws Exception {
        Number num = new Number(5);
        assertEquals(5, num.evalMap());
    }

    @Test
    void testDerivative() {
        Number num = new Number(5);
        Expression derivative = num.derivative("x");
        assertEquals(0, ((Number) derivative).value);
    }

    @Test
    void testPrint() {
        Number num = new Number(10);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        num.print();
        assertEquals("10", outContent.toString());
        System.setOut(System.out);
    }

    @Test
    void testNegativeNumber() {
        Number num = new Number(-10);
        assertEquals(-10, num.value);
    }
}
