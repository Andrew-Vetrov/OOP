package ru.nsu.chepik;

/**
 * Сопоставление достоинства карты числу.
 */
public enum Rank {
    TW0("Двойка", 2),
    THREE("Тройка", 3),
    FOUR("Четвёрка", 4),
    FIVE("Пятёрка", 5),
    SIX("Шестёрка", 6),
    SEVEN("Семёрка", 7),
    EIGHT("Восьмёрка", 8),
    NINE("Девятка", 9),
    TEN("Десятка", 10),
    JACK("Валет", 10),
    QUEEN("Дама", 10),
    KING("Король", 10),
    ACE("Туз", 11);

    private String name;
    private int value;

    /**
     * Конструктор.
     *
     * @param name  достоинство карты.
     * @param value соответствующее числовое значение.
     */
    Rank(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Получить числовое значение достоинства карты.
     *
     * @return число.
     */
    public int getValue() {
        return value;
    }

    /**
     * Получить строковое значение достоинства карты.
     *
     * @return строку.
     */
    public String getName() {
        return name;
    }
}
