package ru.nsu.chepik;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class DivTest {

    @Test
    void testDivEvaluation() throws Exception {
        Number num1 = new Number(8);
        Number num2 = new Number(2);
        Div div = new Div(num1, num2);
        assertEquals(4, div.evalMap());
    }

    @Test
    void testDivDerivative() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Div div = new Div(x, y);

        PrintStream originalOut = System.out;

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Expression derivative = div.derivative("x");
        derivative.print();
        System.setOut(originalOut);
        assertEquals("(((1*" + y.value + ")-(" + x.value + "*0))/(" + y.value + "*" + y.value + "))", outContent.toString());
    }

    @Test
    void testDivByZero() {
        Div div = new Div(new Number(8), new Number(0));

        Exception exception = assertThrows(Exception.class, div::evalMap);
        assertTrue(exception.getMessage().contains("division"));
    }

    @Test
    void testPrintDiv() {
        Div div = new Div(new Number(6), new Number(2));
        div.print();
    }
}

