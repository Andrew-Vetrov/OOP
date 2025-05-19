package ru.nsu.chepik.pizzeria;

public class Order {
    private final int id;
    private Status state;

    public Order(int id) {
        this.id = id;
        state = Status.NEW;
    }

    public synchronized void setStatus(Status state) {
        this.state = state;
    }

    public Status getStatus() {
        return state;
    }

    @Override
    public String toString() {
        return "[" + id + "] [" + state + "]";
    }
}
