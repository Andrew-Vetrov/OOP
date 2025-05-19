package ru.nsu.chepik.pizzeria;

import lombok.AllArgsConstructor;

import java.util.Queue;

@AllArgsConstructor
public class Baker implements Runnable {
    private final int speed;
    private final GeneralQueue queue;
    private final Store store;

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
                System.out.println(e);
                return;
            }
        }
    }
}
