package ru.nsu.chepik.prime;

public interface PrimeNumberCheck {
    boolean checkArray(long[] numbers);

    static boolean numberIsPrime(long number) {
        if (number == 2) {
            return true;
        }

        if (number < 2 || number % 2 == 0) {
            return false;
        }

        for (long i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
