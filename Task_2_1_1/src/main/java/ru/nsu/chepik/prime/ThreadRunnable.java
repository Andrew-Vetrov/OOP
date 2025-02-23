package ru.nsu.chepik.prime;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class ThreadRunnable implements Runnable {
    private final ThreadPrime parent;
    private final Thread[] threads;
    private final long[] numbers;
    private final int threadCount, startIndex;

    @Override
    public void run() {
        for (int i = startIndex; i < numbers.length; i += threadCount) {
            if (threads[startIndex].isInterrupted()) {
                return;
            }

            if (!PrimeNumberCheck.numberIsPrime(numbers[i])) {
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
