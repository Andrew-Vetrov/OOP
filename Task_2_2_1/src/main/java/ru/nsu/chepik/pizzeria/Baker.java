package ru.nsu.chepik.pizzeria;

import java.util.Queue;

public class Baker implements Runnable {
    private final int speed;
    private final GeneralQueue queue;
    private final Store store;

    public Baker(int speed, GeneralQueue queue, Store store) {
        this.speed = speed;
        this.queue = queue;
        this.store = store;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Order order = queue.getOrder();
                if (order == null) {
                    break;
                }

                order.setStatus(Status.COOKING);
                System.out.println(order);
                Thread.sleep(speed);
                order.setStatus(Status.PENDING_DELIVERY);
                System.out.println(order);
                store.addOrder(order);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
