package ru.nsu.chepik.prime;

public class SequentialPrime implements PrimeNumberCheck {
    public boolean checkArray(long[] numbers) {
        for (long number : numbers) {
            if (!PrimeNumberCheck.numberIsPrime(number)) {
                return true;
            }
        }

        return false;
    }
}