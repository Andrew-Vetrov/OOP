package ru.nsu.chepik;

import java.util.ArrayList;

/**
 * Класс, описывающий руку, то есть набор карт игрока или дилера.
 */
public class Hand {
    private ArrayList<Card> hand;
    private int score;
    private int nowIndex;
    private int aceCount;
    private boolean flagForAces;

    /**
     * Удаление всех карт с руки.
     */
    public void clearHand() {
        this.hand = new ArrayList<Card>();
        this.score = 0;
        this.nowIndex = 0;
        this.aceCount = 0;
        flagForAces = true;
    }

    /**
     * Конструктор.
     */
    public Hand() {
        clearHand();
    }

    /**
     * Установить значение открытых карт.
     *
     * @param count соответствующее числовое значение.
     */
    public void setOpenCards(int count) {
        nowIndex = count;
    }

    /**
     * Добавить карту в руку.
     *
     * @param card соответствующая карта.
     */
    public void addCard(Card card) {
        hand.add(card);

        if (card.getRank() == Rank.ACE) {
            aceCount++;

            if (flagForAces) {
                score += 11;
            } else {
                score++;
            }
        } else {
            score += card.getRank().getValue();
        }

        if (score > 21 && flagForAces) {
            flagForAces = false;
            score -= aceCount * 10;
        }
    }

    /**
     * Открыть на столе карту из руки.
     */
    public void showCard() {
        hand.get(nowIndex++).printCard();
        System.out.print("\n");
    }

    /**
     * Получить текущее количество очков, зная карты на руке.
     *
     * @return числовое значение.
     */
    public int getScore() {
        return score;
    }

    /**
     * Распечатать все карты руки.
     */
    public void printHand() {
        for (int i = 0; i < nowIndex - 1; i++) {
            hand.get(i).printCard();
            System.out.print(", ");
        }

        hand.get(nowIndex - 1).printCard();
    }
}
