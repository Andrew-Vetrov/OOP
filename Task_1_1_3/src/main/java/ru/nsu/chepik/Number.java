package ru.nsu.chepik;

/**
 * Класс константы.
 */
public class Number extends Expression {
    public int value;

    /**
     * Конструктор.
     *
     * @param arg значение константы.
     */
    public Number(int arg) {
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
        return new Number(0);
    }

    /**
     * Означивание переменных.
     *
     * @return результат выражения.
     * @throws Exception возможное исключение.
     */
    protected int evalMap() throws Exception {
        return value;
    }
}
