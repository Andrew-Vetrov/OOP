package ru.nsu.chepik;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardTest {

    private Card card;

    @BeforeEach
    public void setUp() {
        card = new Card(Rank.ACE, "Hearts");
    }

    @Test
    public void testConstructor() {
        assertNotNull(card);
    }

    @Test
    public void testGetRank() {
        assertEquals(Rank.ACE, card.getRank());
    }

    @Test
    public void testGetSuit() {
        assertEquals("Hearts", card.getSuit());
    }

    @Test
    public void testPrintCard() {
        String expectedOutput = "Туз Hearts (11)";
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        card.printCard();

        assertEquals(expectedOutput, outContent.toString().trim());

        System.setOut(System.out);
    }
}
