package ru.nsu.chepik;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс сложения.
 */
public class Add extends Expression {
    public Expression first;
    public Expression second;

    /**
     * Констркутор.
     *
     * @param first  первое слагаемое.
     * @param second второе слагаемое.
     */
    public Add(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Печать выражения.
     */
    public void print() {
        System.out.print("(");
        first.print();
        System.out.print("+");
        second.print();
        System.out.print(")");
    }

    /**
     * Нахождение производной.
     *
     * @param variable дифференцируемая переменная.
     * @return выражение.
     */
    public Expression derivative(String variable) {
        return new Add(first.derivative(variable), second.derivative(variable));
    }

    /**
     * Означивание переменных.
     *
     * @return результат выражения.
     * @throws Exception возможное исключение.
     */
    protected int evalMap() throws Exception {
        return first.evalMap() + second.evalMap();
    }
}
