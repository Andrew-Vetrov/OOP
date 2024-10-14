package ru.nsu.chepik;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RankTest {

    @Test
    public void testGetValue() {
        assertEquals(2, Rank.TW0.getValue());
        assertEquals(3, Rank.THREE.getValue());
        assertEquals(4, Rank.FOUR.getValue());
        assertEquals(5, Rank.FIVE.getValue());
        assertEquals(6, Rank.SIX.getValue());
        assertEquals(7, Rank.SEVEN.getValue());
        assertEquals(8, Rank.EIGHT.getValue());
        assertEquals(9, Rank.NINE.getValue());
        assertEquals(10, Rank.TEN.getValue());
        assertEquals(10, Rank.JACK.getValue());
        assertEquals(10, Rank.QUEEN.getValue());
        assertEquals(10, Rank.KING.getValue());
        assertEquals(11, Rank.ACE.getValue());
    }

    @Test
    public void testGetName() {
        assertEquals("Двойка", Rank.TW0.getName());
        assertEquals("Тройка", Rank.THREE.getName());
        assertEquals("Четвёрка", Rank.FOUR.getName());
        assertEquals("Пятёрка", Rank.FIVE.getName());
        assertEquals("Шестёрка", Rank.SIX.getName());
        assertEquals("Семёрка", Rank.SEVEN.getName());
        assertEquals("Восьмёрка", Rank.EIGHT.getName());
        assertEquals("Девятка", Rank.NINE.getName());
        assertEquals("Десятка", Rank.TEN.getName());
        assertEquals("Валет", Rank.JACK.getName());
        assertEquals("Дама", Rank.QUEEN.getName());
        assertEquals("Король", Rank.KING.getName());
        assertEquals("Туз", Rank.ACE.getName());
    }
}
