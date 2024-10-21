package ru.nsu.chepik;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import java.util.Map;

class VariableTest {

    @Test
    void testEvalMap() throws Exception {
        Variable var = new Variable("x");
        Expression.dict = Map.of("x", 10);
        assertEquals(10, var.evalMap());
    }

    @Test
    void testDerivativeSelf() {
        Variable var = new Variable("x");
        Expression derivative = var.derivative("x");
        assertEquals(1, ((Number) derivative).value);
    }

    @Test
    void testDerivativeDifferent() {
        Variable var = new Variable("x");
        Expression derivative = var.derivative("y");
        assertEquals(0, ((Number) derivative).value);
    }

    @Test
    void testPrint() {
        Variable var = new Variable("x");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        var.print();
        assertEquals("x", outContent.toString());
        System.setOut(System.out);
    }
}
