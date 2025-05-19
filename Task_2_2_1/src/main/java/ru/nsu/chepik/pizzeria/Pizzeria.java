package ru.nsu.chepik.pizzeria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pizzeria {
    private final Config params;
    private final GeneralQueue queue = new GeneralQueue();
    private final Store store;
    private final List<Thread> bakers;
    private final List<Thread> couriers;
    private boolean isOpen = false;
    private int ordersCount = 0;

    public Pizzeria(String path) throws IOException {
        params = Config.createConfig(path);
        store = new Store(params.getStore_capacity());
        bakers = new ArrayList<>(params.getBakers());
        couriers = new ArrayList<>(params.getCouriers());

        for (int speed : params.getBakers_speeds()) {
            bakers.add(new Thread(new Baker(speed, queue, store)));
        }

        for (int capacity : params.getCouriers_capacities()) {
            couriers.add(new Thread(new Courier(capacity, store)));
        }
    }

    public void open() {
        isOpen = true;

        for (Thread baker : bakers) {
            baker.start();
        }

        for (Thread courier : couriers) {
            courier.start();
        }

        System.out.println("Pizzeria is open");
    }

    public void close() {
        isOpen = false;
        System.out.println("Pizzeria is close");
        queue.close();

        for (Thread baker : bakers) {
            try {
                baker.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        store.close();

        for (Thread courier : couriers) {
            try {
                courier.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public synchronized void addOrder() {
        if (isOpen) {
            queue.addOrder(new Order(++ordersCount));
        }
    }
}
