package ru.nsu.chepik.prime;

/**
 * Интерфейс для разных вариантов реализации проверки входного массива на наличие составного числа.
 */
public interface PrimeNumberCheck {
    /**
     * Метод для работы с входным массивом чисел.
     *
     * @param numbers входной массив.
     * @return true/false в зависимости от наличия составного числа во входном массиве.
     */
    boolean checkArray(long[] numbers);

    /**
     * Метод для проверки числа: составное оно или нет.
     *
     * @param number проверяемое число.
     * @return true/false - результат проверки.
     */
    static boolean numberIsNotPrime(long number) {
        if (number == 2) {
            return false;
        }

        if (number < 2 || number % 2 == 0) {
            return true;
        }

        for (long i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return true;
            }
        }

        return false;
    }
}
