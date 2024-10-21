package ru.nsu.chepik;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

class AddTest {
    @Test
    void testAddEvaluation() throws Exception {
        Number num1 = new Number(3);
        Number num2 = new Number(4);
        Add add = new Add(num1, num2);
        assertEquals(7, add.evalMap());
    }

    @Test
    void testAddWithVariables() throws Exception {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Expression.dict = new HashMap<>();
        Expression.dict.put("x", 5);
        Expression.dict.put("y", 10);
        Add add = new Add(x, y);
        assertEquals(15, add.evalMap());
    }

    @Test
    void testAddDerivative() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Add add = new Add(x, y);

        Add derivative = (Add) add.derivative("x");

        assertEquals(1, ((Number) derivative.first).value);
        assertEquals(0, ((Number) derivative.second).value);
    }

    @Test
    void testPrintAdd() {
        Add add = new Add(new Number(1), new Number(2));
        add.print();
    }
}
