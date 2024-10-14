package ru.nsu.chepik;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeckTest {

    private Deck deck;

    @BeforeEach
    public void setUp() {
        deck = new Deck();
    }

    @Test
    public void testNewDeckHas52Cards() {
        int cardCount = 0;

        while (cardCount < 52) {
            assertNotNull(deck.getCard());
            cardCount++;
        }

        assertEquals(52, cardCount);
    }

    @Test
    public void testGetCardReducesDeckSize() {
        int initialDeckSize = 52;

        for (int i = 0; i < initialDeckSize; i++) {
            deck.getCard();
            assertEquals(initialDeckSize - i - 1, getRemainingCards(initialDeckSize, i + 1));
        }
    }

    @Test
    public void testNewDeckIsShuffled() {
        Deck anotherDeck = new Deck();
        boolean isDifferent = false;

        for (int i = 0; i < 5; i++) {
            Card card1 = deck.getCard();
            Card card2 = anotherDeck.getCard();
            if (!card1.getRank().equals(card2.getRank()) || !card1.getSuit().equals(card2.getSuit())) {
                isDifferent = true;
                break;
            }
        }

        assertTrue(isDifferent);
    }

    private int getRemainingCards(int initialDeckSize, int cardsTaken) {
        return initialDeckSize - cardsTaken;
    }
}
