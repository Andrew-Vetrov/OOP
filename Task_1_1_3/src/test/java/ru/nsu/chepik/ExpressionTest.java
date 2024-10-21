package ru.nsu.chepik;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExpressionTest {
    @Test
    void testEvalWithSingleVariable() throws Exception {
        Expression expr = new Variable("x");
        String arg = "x=10;";
        int result = expr.eval(arg);
        assertEquals(10, result);
    }

    @Test
    void testEvalWithMultipleVariables() throws Exception {
        Expression expr = new Add(new Variable("x"), new Variable("y"));
        String arg = "x=3;y=4;";
        int result = expr.eval(arg);
        assertEquals(7, result);
    }

    @Test
    void testEvalWithInvalidInput() {
        Expression expr = new Variable("x");
        String arg = "x=5abc;";
        int result = expr.eval(arg);
        //assertEquals(-1, result);
    }

    @Test
    void testEvalWithMissingVariable() {
        Expression expr = new Variable("z");
        String arg = "x=10;";
        int result = expr.eval(arg);
        assertEquals(-1, result);
    }
}