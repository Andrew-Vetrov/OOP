package ru.nsu.chepik;

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
        return new Div(new Sub(new Mul(first.derivative(variable), second), new Mul(first, second.derivative(variable))), new Mul(second, second));
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
