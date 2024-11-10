package ru.nsu.chepik;

import java.util.Objects;

/**
 * Класс деления.
 */
public class Div extends Expression {
    public Expression first;
    public Expression second;

    /**
     * Констркутор.
     *
     * @param first  делимое.
     * @param second делитель.
     */
    public Div(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Печать выражения.
     */
    public void print() {
        System.out.print("(");
        first.print();
        System.out.print("/");
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
        Expression efirst = first.derivative(variable);
        if (Objects.equals(efirst, new Number(0)) || Objects.equals(second, new Number(0))) {
            efirst = new Number(0);
        }

        else if (Objects.equals(efirst, new Number(1))) {
            efirst = second;
        }

        else if (Objects.equals(second, new Number(1))) {
            efirst = first;
        }

        else {
            efirst = new Mul(first.derivative(variable), second);
        }

        Expression esecond = second.derivative(variable);
        if (Objects.equals(esecond, new Number(0)) || Objects.equals(first, new Number(0))) {
            esecond = new Number(0);
        }

        else if (Objects.equals(esecond, new Number(1))) {
            esecond = first;
        }

        else if (Objects.equals(first, new Number(1))) {
            esecond = second;
        }

        else {
            esecond = new Mul(first, second.derivative(variable));
        }

        Expression ethird;
        if (Objects.equals(second, new Number(0))) {
            ethird = new Number(0);
        }

        else if (Objects.equals(second, new Number(1))) {
            ethird = second;
        }

        else {
            ethird = new Mul(second, second);
        }

        return new Div(new Sub(efirst, esecond), ethird);
    }

    /**
     * Означивание переменных.
     *
     * @return результат выражения.
     * @throws Exception возможное исключение.
     */
    protected int evalMap() throws Exception {
        /*try {
            return first.evalMap() / second.evalMap();
        } catch (Exception e) {
            System.out.println("Exception in function evalMap(), division operation: " + e);

            return -1;
        }*/

        int denominator = second.evalMap();

        if (denominator == 0) {
            throw new Exception("Exception in function evalMap(), division operation: divide by zero");
        }

        return first.evalMap() / denominator;
    }
}
