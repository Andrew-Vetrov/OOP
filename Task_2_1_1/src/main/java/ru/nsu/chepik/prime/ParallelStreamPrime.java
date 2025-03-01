package ru.nsu.chepik.prime;

import java.util.Arrays;
import java.util.List;

/**
 * Класс параллельной проверки массива при помощи parallelStream().
 */
public class ParallelStreamPrime implements PrimeNumberCheck {
    /**
     * Метод для работы с входным массивом чисел.
     *
     * @param numbers входной массив.
     * @return true/false в зависимости от наличия составного числа во входном массиве.
     */
    public boolean checkArray(long[] numbers) {
        List<Long> listNumbers = Arrays.stream(numbers).boxed().toList();
        return listNumbers.parallelStream().anyMatch(PrimeNumberCheck::numberIsPrime);
    }
}
