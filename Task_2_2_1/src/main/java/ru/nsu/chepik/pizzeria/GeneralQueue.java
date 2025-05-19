package ru.nsu.chepik.pizzeria;

import java.util.LinkedList;
import java.util.Queue;

public class GeneralQueue {
    private final Queue<Order> queue = new LinkedList<>();
    private boolean isOpen = true;

    public synchronized void addOrder(Order order) {
        order.setStatus(Status.NEW);
        System.out.println(order);
        queue.add(order);
        notifyAll();
    }

    public synchronized Order getOrder() throws InterruptedException {
        while (queue.isEmpty() && isOpen) {
            wait();
        }

        return queue.poll();
    }

    public synchronized void close() {
        isOpen = false;
        notifyAll();
    }
}
