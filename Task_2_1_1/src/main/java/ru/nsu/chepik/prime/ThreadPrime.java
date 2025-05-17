package ru.nsu.chepik.prime;

import lombok.RequiredArgsConstructor;

/**
 * Класс параллельной проверки массива на нескольких нитях.
 */
@RequiredArgsConstructor
public class ThreadPrime implements PrimeNumberCheck {
    private final int threadCount;
    private boolean result = false;

    /**
     * Метод для изменения состояния флага, обозначающего наличие составного числа в массиве.
     *
     * @param var новое значение флага.
     */
    public synchronized void setResult(boolean var) {
        result = var;
    }

    /**
     * Метод для работы с входным массивом чисел.
     *
     * @param numbers входной массив.
     * @return true/false в зависимости от наличия составного числа во входном массиве.
     */
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
