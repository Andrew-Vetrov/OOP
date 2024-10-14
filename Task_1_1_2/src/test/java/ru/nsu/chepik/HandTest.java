package ru.nsu.chepik;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HandTest {

    private Hand hand;

    @BeforeEach
    public void setUp() {
        hand = new Hand();
    }

    @Test
    public void testAddCard() {
        Card card = new Card(Rank.TEN, "Черви");
        hand.addCard(card);
        assertEquals(10, hand.getScore());
    }

    @Test
    public void testAddMultipleCards() {
        hand.addCard(new Card(Rank.TEN, "Черви"));
        hand.addCard(new Card(Rank.FIVE, "Бубны"));
        hand.addCard(new Card(Rank.TW0, "Трефы"));

        assertEquals(17, hand.getScore());
    }

    @Test
    public void testAddAce() {
        hand.addCard(new Card(Rank.ACE, "Пики"));
        assertEquals(11, hand.getScore());

        hand.addCard(new Card(Rank.TEN, "Черви"));
        hand.addCard(new Card(Rank.TW0, "Трефы"));
        assertEquals(13, hand.getScore());
    }

    @Test
    public void testClearHand() {
        hand.addCard(new Card(Rank.FIVE, "Черви"));
        hand.addCard(new Card(Rank.TEN, "Пики"));

        hand.clearHand();
        assertEquals(0, hand.getScore());
    }

    @Test
    public void testShowCard() {
        Card card1 = new Card(Rank.TEN, "Черви");
        Card card2 = new Card(Rank.FIVE, "Бубны");

        hand.addCard(card1);
        hand.addCard(card2);
        hand.setOpenCards(1);

        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        hand.showCard();

        String expectedOutput = "Пятёрка Бубны (5)\n";
        assertEquals(expectedOutput, outContent.toString());

        System.setOut(System.out);
    }

    @Test
    public void testPrintHand() {
        hand.addCard(new Card(Rank.TEN, "Черви"));
        hand.addCard(new Card(Rank.FIVE, "Бубны"));
        hand.setOpenCards(2);

        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        hand.printHand();

        String expectedOutput = "Десятка Черви (10), Пятёрка Бубны (5)";
        assertEquals(expectedOutput, outContent.toString());

        System.setOut(System.out);
    }

    @Test
    public void testAddThreeAces() {
        hand.addCard(new Card(Rank.ACE, "Пики"));
        hand.addCard(new Card(Rank.ACE, "Черви"));
        hand.addCard(new Card(Rank.ACE, "Бубны"));

        assertEquals(3, hand.getScore());
    }
}
