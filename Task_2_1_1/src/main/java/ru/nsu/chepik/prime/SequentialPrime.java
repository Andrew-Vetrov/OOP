package ru.nsu.chepik.prime;

/**
 * Класс последовательной проверки массива на одной нити.
 */
public class SequentialPrime implements PrimeNumberCheck {
    /**
     * Метод для работы с входным массивом чисел.
     *
     * @param numbers входной массив.
     * @return true/false в зависимости от наличия составного числа во входном массиве.
     */
    public boolean checkArray(long[] numbers) {
        for (long number : numbers) {
            if (!PrimeNumberCheck.numberIsPrime(number)) {
                return true;
            }
        }

        return false;
    }
}