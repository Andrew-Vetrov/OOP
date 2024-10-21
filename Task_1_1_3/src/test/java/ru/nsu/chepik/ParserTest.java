package ru.nsu.chepik;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    void testParseNumber() {
        Parser parser = new Parser();
        Expression result = parser.parse("42");

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        result.print();
        System.setOut(originalOut);

        assertEquals("42", outContent.toString());
    }

    @Test
    void testParseVariable() {
        Parser parser = new Parser();
        Expression result = parser.parse("x");

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        result.print();
        System.setOut(originalOut);

        assertEquals("x", outContent.toString());
    }

    @Test
    void testParseAddition() {
        Parser parser = new Parser();
        Expression result = parser.parse("-x + 5*5*5*5*5");

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        result.print();
        System.setOut(originalOut);

        assertEquals("((-1*x)+((((5*5)*5)*5)*5))", outContent.toString());
    }

    @Test
    void testParseSubtraction() {
        Parser parser = new Parser();
        Expression result = parser.parse("y - 3");

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        result.print();
        System.setOut(originalOut);

        assertEquals("(y-(1*3))", outContent.toString());
    }

    @Test
    void testParseMultiplication() {
        Parser parser = new Parser();
        Expression result = parser.parse("2 * x");

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        result.print();
        System.setOut(originalOut);

        assertEquals("(2*x)", outContent.toString());
    }

    @Test
    void testParseDivision() {
        Parser parser = new Parser();
        Expression result = parser.parse("x / 4");

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        result.print();
        System.setOut(originalOut);

        assertEquals("(x/4)", outContent.toString());
    }

    @Test
    void testParseComplexExpression() {
        Parser parser = new Parser();
        Expression result = parser.parse("x + y * 2");

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        result.print();
        System.setOut(originalOut);

        assertEquals("(x+(y*2))", outContent.toString());
    }

    @Test
    void testParseNestedExpressions() {
        Parser parser = new Parser();
        Expression result = parser.parse("(x + 1) * (y - 2)");

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        result.print();
        System.setOut(originalOut);

        assertEquals("((x+1)*(y-(1*2)))", outContent.toString());
    }
}
