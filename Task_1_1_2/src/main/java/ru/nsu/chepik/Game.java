package ru.nsu.chepik;

import java.util.Scanner;

/**
 * Главный класс, описывающий игру.
 */
public class Game {
    Scanner scanner;
    int playerPoints;
    int dealerPoints;
    Hand dealer;
    Hand player;
    Deck deck;

    private int nowRound;

    /**
     * Конструктор.
     */
    public Game(Scanner scn) {
        this.scanner = scn;
        this.deck = new Deck();
        this.nowRound = 0;
        this.playerPoints = 0;
        this.dealerPoints = 0;
        this.dealer = new Hand();
        this.player = new Hand();
    }

    /**
     * Распечатать колоду игрока.
     */
    void printPlayerCards() {
        System.out.print("\tВаши карты: [");
        player.printHand();
        System.out.println("] ⟹ " + player.getScore());
    }

    /**
     * Распечатать колоду дилера.
     *
     * @param number флаг, отвечающий за скрытость карты дилера.
     */
    void printDealerCards(int number) {
        System.out.print("\tКарты дилера: [");
        dealer.printHand();

        if (number == 1) {
            System.out.println(", <закрытая карта>]");
        } else {
            System.out.println("] ⟹ " + dealer.getScore());
        }
    }

    /**
     * Ход игрока.
     */
    void playerMove() {
        player.addCard(deck.getCard());

        System.out.print("Вы открыли карту ");

        player.showCard();
        printPlayerCards();
        printDealerCards(1);
    }

    /**
     * Ход дилера.
     */
    void dealerMove() {
        System.out.println("\nХод дилера\n-------\nДилер открывает закрытую карту ");
        dealer.showCard();
        printPlayerCards();
        printDealerCards(0);

        while (dealer.getScore() < 17) {
            System.out.println("\nДилер открывает карту ");
            dealer.addCard(deck.getCard());
            dealer.showCard();
            printPlayerCards();
            printDealerCards(0);
        }
    }

    /**
     * Определение победителя по текущему счёту.
     *
     * @return соответствующую строку.
     */
    String whoWinner() {
        if (dealerPoints > playerPoints) {
            return " в пользу дилера.";
        } else if (dealerPoints < playerPoints) {
            return " в вашу пользу.";
        } else {
            return " .";
        }
    }

    /**
     * Определение победителя раунда.
     *
     * @param flag отвечает за гарантированный проигрыш игрока (если flag = 0).
     */
    void winner(boolean flag) {
        int dealerScore = dealer.getScore();
        int playerScore = player.getScore();

        if (flag && (dealerScore > 21 && playerScore > 21 || dealerScore == playerScore)) {
            System.out.print("\nНичья! ");
        } else if (!flag || playerScore > 21 || playerScore < dealerScore && dealerScore <= 21) {
            System.out.print("\nДилер выиграл раунд! ");
            dealerPoints++;
        } else {
            System.out.print("\nВы выиграли раунд! ");
            playerPoints++;
        }

        System.out.println("Счет " + playerPoints + ":" + dealerPoints + whoWinner());
    }

    /**
     * Раунд игры.
     */
    void round() {
        int inputNumber;

        System.out.println("\nРаунд " + ++nowRound);

        deck.newDeck();
        dealer.clearHand();
        player.clearHand();

        for (int i = 0; i < 2; i++) {
            dealer.addCard(deck.getCard());
            player.addCard(deck.getCard());
        }

        dealer.setOpenCards(1);
        player.setOpenCards(2);

        System.out.println("Дилер раздал карты");

        printPlayerCards();
        printDealerCards(1);

        System.out.print("\nВаш ход\n-------");

        while (true) {
            System.out.println("\nВведите “1”, чтобы взять карту, и “0”, чтобы остановиться .");

            inputNumber = scanner.nextInt();

            if (inputNumber == 0) {
                break;
            }

            playerMove();

            if (player.getScore() > 21) {
                winner(false);
                return;
            }
        }

        dealerMove();
        winner(true);
    }

    /**
     * Инициализация игры.
     */
    void game() {
        System.out.print("Добро пожаловать в Блэкджек!");

        for (int i = 0; i < 5; i++) {
            round();
        }
    }

    /**
     * Создание объекта игры.
     *
     * @param args аргументы main.
     */
    public static void main(String[] args) {
        Game newGame = new Game(new Scanner(System.in));
        newGame.game();
    }
}