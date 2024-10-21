package ru.nsu.chepik;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

class MulTest {

    @Test
    void testMulEvaluation() throws Exception {
        Number num1 = new Number(5);
        Number num2 = new Number(6);
        Mul mul = new Mul(num1, num2);
        assertEquals(30, mul.evalMap());
    }

    @Test
    void testMulWithVariables() throws Exception {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Expression.dict = new HashMap<>();
        Expression.dict.put("x", 3);
        Expression.dict.put("y", 2);
        Mul mul = new Mul(x, y);
        assertEquals(6, mul.evalMap());
    }

    @Test
    void testMulDerivative() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Mul mul = new Mul(x, y);

        PrintStream originalOut = System.out;

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Expression derivative = mul.derivative("x");
        derivative.print();

        System.setOut(originalOut);

        assertEquals("((1*" + y.value + ")+(x*0))", outContent.toString());
    }

    @Test
    void testPrintMul() {
        Mul mul = new Mul(new Number(2), new Number(3));
        mul.print();
    }
}

