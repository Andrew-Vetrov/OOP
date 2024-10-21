package ru.nsu.chepik;

/**
 * Класс вычитания.
 */
public class Sub extends Expression {
    public Expression first;
    public Expression second;

    /**
     * Констркутор.
     *
     * @param first  уменьшаемое.
     * @param second вычитаемое.
     */
    public Sub(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Печать выражения.
     */
    public void print() {
        System.out.print("(");
        first.print();
        System.out.print("-");
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
        return new Sub(first.derivative(variable), second.derivative(variable));
    }

    /**
     * Означивание переменных.
     *
     * @return результат выражения.
     * @throws Exception возможное исключение.
     */
    protected int evalMap() throws Exception {
        return first.evalMap() - second.evalMap();
    }
}
