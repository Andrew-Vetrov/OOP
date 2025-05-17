package ru.nsu.chepik.prime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class PrimeNumberCheckTest {
    @ParameterizedTest
    @MethodSource("provideImplementations")
    public void testCheckArrayWithPrimes(PrimeNumberCheck intrf) {
        long[] numbers = {-5, 0, 2, 8, 7, 13, 5, 9, 4};

        boolean result = intrf.checkArray(numbers);
        assertTrue(result);
    }

    @ParameterizedTest
    @MethodSource("provideImplementations")
    public void testCheckArrayWithLargePrimes(PrimeNumberCheck intrf) { // All numbers in the array are prime
        long[] numbers = {
                20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053
        };

        boolean result = intrf.checkArray(numbers);
        assertFalse(result);
    }

    @ParameterizedTest
    @MethodSource("provideImplementations")
    public void testCheckArrayWithInterrupts(PrimeNumberCheck intrf) { // Check thread's interrupts
        long[] numbers = {6, 5, 9, 7, 7, 7, 7, 7, 7, 7, 7, 7,
                          7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7
        };

        boolean result = intrf.checkArray(numbers);
        assertTrue(result);
    }

    static Stream<PrimeNumberCheck> provideImplementations() {
        return Stream.of(
                new SequentialPrime(),
                new ThreadPrime(4),
                new ParallelStreamPrime()
        );
    }
}
