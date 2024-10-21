package ru.nsu.chepik;

/**
 * Класс переменной.
 */
public class Variable extends Expression {
    public String value;

    /**
     * Конструктор.
     *
     * @param arg переменная.
     */
    public Variable(String arg) {
        this.value = arg;
    }

    /**
     * Печать выражения.
     */
    public void print() {
        System.out.print(value);
    }

    /**
     * Нахождение производной.
     *
     * @param variable дифференцируемая переменная.
     * @return выражение.
     */
    public Expression derivative(String variable) {
        if (variable.equals(value)) {
            return new Number(1);
        }

        return new Number(0);
    }

    /**
     * Означивание переменных.
     *
     * @return результат выражения.
     * @throws Exception возможное исключение.
     */
    protected int evalMap() throws Exception {
        if (!dict.containsKey(value)) {
            throw new Exception("No such variable");
        }

        return dict.get(value);
    }
}
