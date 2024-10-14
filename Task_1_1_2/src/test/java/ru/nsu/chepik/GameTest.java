package ru.nsu.chepik;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

public class GameTest {

    private Game game;

    @Test
    public void testWhoWinner() {
        game = new Game(new Scanner(System.in));

        game.playerPoints = 3;
        game.dealerPoints = 2;
        assertEquals(" в вашу пользу.", game.whoWinner());

        game.playerPoints = 2;
        game.dealerPoints = 3;
        assertEquals(" в пользу дилера.", game.whoWinner());

        game.playerPoints = 2;
        game.dealerPoints = 2;
        assertEquals(" .", game.whoWinner());
    }

    @Test
    public void testWinner() {
        game = new Game(new Scanner(System.in));

        game.playerPoints = 0;
        game.dealerPoints = 0;

        // Сценарий: ничья
        game.player.addCard(new Card(Rank.TEN, "Черви"));
        game.player.addCard(new Card(Rank.TEN, "Бубны"));
        game.dealer.addCard(new Card(Rank.TEN, "Пики"));
        game.dealer.addCard(new Card(Rank.TEN, "Трефы"));
        game.winner(true);
        assertEquals(0, game.playerPoints);
        assertEquals(0, game.dealerPoints);

        // Сценарий: игрок выигрывает
        game.player.addCard(new Card(Rank.ACE, "Черви"));
        game.winner(true);
        assertEquals(1, game.playerPoints);

        // Сценарий: дилер выигрывает
        game.player.clearHand();
        game.dealer.clearHand();
        game.dealer.addCard(new Card(Rank.TEN, "Трефы"));
        game.dealer.addCard(new Card(Rank.ACE, "Черви"));
        game.winner(true);
        assertEquals(1, game.dealerPoints);
    }

    @Test
    public void testPlayerMove() {
        game = new Game(new Scanner(System.in));

        game.player.addCard(new Card(Rank.TEN, "Черви"));
        assertEquals(10, game.player.getScore());  // проверяем результат после хода
    }

    @Test
    public void testDealerMove() {
        game = new Game(new Scanner(System.in));

        game.player.addCard(new Card(Rank.TEN, "Черви"));
        game.player.setOpenCards(1);
        game.dealer.addCard(new Card(Rank.TEN, "Трефы"));
        game.dealer.addCard(new Card(Rank.FOUR, "Пики"));
        game.dealer.setOpenCards(1);

        game.dealerMove();

        assertTrue(game.dealer.getScore() >= 17);  // проверяем, что дилер остановится при >= 17
    }

    @Test
    public void testRound() {
        String simulatedInput = "1\n1\n1\n1\n1\n1\n1\n";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());

        game = new Game(new Scanner(in));
        game.round();

        assertTrue(game.player.getScore() > 0);
    }

    @Test
    public void testGame() {
        String simulatedInput = "0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());

        game = new Game(new Scanner(in));
        game.game();

        assertTrue(game.playerPoints >= 0);
        assertTrue(game.dealerPoints >= 0);
    }
}
