package ru.nsu.chepik;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Класс, описывающий колоду.
 */
public class Deck {
    private ArrayList<Card> deck;

    /**
     * Создание новой колоды.
     */
    public void newDeck() {
        this.deck = new ArrayList<Card>();

        Rank[] ranks = {Rank.TW0, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN,
                Rank.EIGHT, Rank.NINE, Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING, Rank.ACE};
        String[] suits = {"Пики", "Черви", "Бубны", "Трефы"};

        for (Rank rank : ranks) {
            for (String suit : suits) {
                deck.add(new Card(rank, suit));
            }
        }

        Collections.shuffle(deck);
    }

    /**
     * Конструктор.
     */
    public Deck() {
        newDeck();
    }

    /**
     * Взятие карты с вершины колоды.
     *
     * @return карту.
     */
    public Card getCard() {
        return deck.remove(deck.size() - 1);
    }
}
