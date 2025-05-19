package ru.nsu.chepik.pizzeria;

public class Courier implements Runnable {
    private final int capacity;
    private final Store store;

    public Courier(int capacity, Store store) {
        this.capacity = capacity;
        this.store = store;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Order order = store.getOrder();
                if (order == null) {
                    break;
                }

                order.setStatus(Status.DELIVERY);
                System.out.println(order);
                Thread.sleep(2000);
                order.setStatus(Status.COMPLETED);
                System.out.println(order);
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            }
        }
    }
}
