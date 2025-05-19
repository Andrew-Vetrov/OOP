package ru.nsu.chepik.pizzeria;

import java.util.LinkedList;
import java.util.Queue;

public class Store {
    private final Queue<Order> store = new LinkedList<>();
    private final int capacity;
    private boolean isOpen = true;

    public Store(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void addOrder(Order order) throws InterruptedException {
        while (store.size() == capacity) {
            wait();
        }

        store.add(order);
        notifyAll();
    }

    public synchronized Order getOrder() throws InterruptedException {
        while (store.isEmpty() && isOpen) {
            wait();
        }

        Order order = store.poll();
        notifyAll();

        return order;
    }

    public synchronized void close() {
        isOpen = false;
        notifyAll();
    }
}
