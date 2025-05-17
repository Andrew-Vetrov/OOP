package ru.nsu.chepik.prime;

import lombok.AllArgsConstructor;

/**
 * Класс, описывающий исполнение одной нити.
 */
@AllArgsConstructor
class ThreadRunnable implements Runnable {
    private final ThreadPrime parent;
    private final Thread[] threads;
    private final long[] numbers;
    private final int threadCount, startIndex;

    /**
     * Исполняемая нитью функция.
     */
    @Override
    public void run() {
        for (int i = startIndex; i < numbers.length; i += threadCount) {
            if (threads[startIndex].isInterrupted()) {
                return;
            }

            if (PrimeNumberCheck.numberIsNotPrime(numbers[i])) {
                parent.setResult(true);

                for (int k = 0; k < threadCount; k++) {
                    if (k != startIndex) {
                        threads[k].interrupt();
                    }
                }

                return;
            }
        }
    }
}
