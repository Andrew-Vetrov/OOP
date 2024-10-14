package ru.nsu.chepik;

/**
 * Класс, описывающий карту.
 */
public class Card {
    private Rank rank;
    private String suit;

    /**
     * Конструктор.
     *
     * @param rank достоинство карты.
     * @param suit масть карты.
     */
    public Card(Rank rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Получить достоинство карты.
     *
     * @return enum Rank.
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Получить масть карты.
     *
     * @return строку
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Печать карты в установленном формате.
     */
    public void printCard() {
        System.out.printf("%s %s (%d)", rank.getName(), suit, rank.getValue());
    }
}
