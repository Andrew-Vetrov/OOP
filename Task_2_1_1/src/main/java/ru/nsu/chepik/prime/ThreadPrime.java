package ru.nsu.chepik.prime;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ThreadPrime implements PrimeNumberCheck {
    private final int threadCount;
    private boolean result = false;

    public synchronized void setResult(boolean var) {
        result = var;
    }

    public boolean checkArray(long[] numbers) {
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            ThreadRunnable task = new ThreadRunnable(this, threads, numbers, threadCount, i);

            threads[i] = new Thread(task);
            threads[i].start();
        }

        for (int i = 0; i < threadCount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println("Thread with checkArray() interrupted: " + e.getMessage());
            }
        }

        return result;
    }
}
