package ru.nsu.chepik;

import java.util.Objects;

/**
 * Класс умножения.
 */
public class Mul extends Expression {
    public Expression first;
    public Expression second;

    /**
     * Констркутор.
     *
     * @param first  первый множитель.
     * @param second второй множитель.
     */
    public Mul(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Печать выражения.
     */
    public void print() {
        System.out.print("(");
        first.print();
        System.out.print("*");
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
        Expression prom = first.derivative(variable), res;

        if (Objects.equals(prom, new Number(0)) || Objects.equals(second, new Number(0))) {
            res = new Number(0);
        }

        else if (Objects.equals(prom, new Number(1))) {
            res = second;
        }

        else if (Objects.equals(second, new Number(1))) {
            res = prom;
        }

        else {
            res = new Mul(prom, second);
        }

        prom = second.derivative(variable);
        //
        if (!Objects.equals(prom, new Number(0)) && !Objects.equals(first, new Number(0))) {
            res = new Add(res, new Mul(first, prom));
        }

        return res;
    }

    /**
     * Означивание переменных.
     *
     * @return результат выражения.
     * @throws Exception возможное исключение.
     */
    protected int evalMap() throws Exception {
        return first.evalMap() * second.evalMap();
    }
}
