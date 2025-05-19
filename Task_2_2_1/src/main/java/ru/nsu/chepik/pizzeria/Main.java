package ru.nsu.chepik.pizzeria;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Pizzeria pizzeria = new Pizzeria("src/main/resources/config.json");
        pizzeria.open();
        for (int i = 0; i < 1000; i++) {
            pizzeria.addOrder();
        }
        pizzeria.close();
    }
}
