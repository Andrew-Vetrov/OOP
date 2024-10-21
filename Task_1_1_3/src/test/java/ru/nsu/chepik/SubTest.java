package ru.nsu.chepik;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

class SubTest {

    @Test
    void testSubEvaluation() throws Exception {
        Number num1 = new Number(10);
        Number num2 = new Number(4);
        Sub sub = new Sub(num1, num2);
        assertEquals(6, sub.evalMap());
    }

    @Test
    void testSubWithVariables() throws Exception {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Expression.dict = new HashMap<>();
        Expression.dict.put("x", 10);
        Expression.dict.put("y", 4);
        Sub sub = new Sub(x, y);
        assertEquals(6, sub.evalMap());
    }

    @Test
    void testSubDerivative() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Sub sub = new Sub(x, y);

        Sub derivative = (Sub) sub.derivative("x");

        assertEquals(1, ((Number) derivative.first).value);
        assertEquals(0, ((Number) derivative.second).value);
    }

    @Test
    void testPrintSub() {
        Sub sub = new Sub(new Number(5), new Number(2));
        sub.print();
    }
}

