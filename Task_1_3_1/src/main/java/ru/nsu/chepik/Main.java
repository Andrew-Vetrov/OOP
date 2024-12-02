package ru.nsu.chepik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Основной класс.
 */
public class Main {
    /**
     * Поиск подстроки в открытом файле.
     *
     * @param openFile открытый файл.
     * @param substring искомая подстрока.
     * @return список найденных вхождений подстроки.
     * @throws IOException исключение ввода-вывода.
     */
    public static List<Long> findWithReader(Reader openFile, String substring) throws IOException {
        List<Long> answer = new ArrayList<>();
        Queue<Long> queue = new LinkedList<>();

        int len = substring.length();
        if (len == 0) {
            return answer;
        }

        long hash, hash2, coeff = 65519, nowCoeff = 1, coeff2 = 65521, nowCoeff2 = 1, firstSymbol, firstSymbol2;
        final long mod = 1000000007, mod2 = 1000000009;
        long symbol, queueSymbol, nowIndx = 1;

        if ((symbol = openFile.read()) < 0) {
            return answer;
        }

        queue.add(symbol);

        hash = symbol;
        hash2 = hash;

        long substringHash = substring.charAt(0), substringHash2 = substringHash;

        for (int i = 1; i < len; i++) {
            if ((symbol = openFile.read()) < 0) {
                return answer;
            }

            queue.add(symbol);

            nowCoeff = (nowCoeff * coeff) % mod;
            nowCoeff2 = (nowCoeff2 * coeff2) % mod2;

            hash = (hash * coeff) % mod;
            hash2 = (hash2 * coeff2) % mod2;

            hash = (hash + symbol) % mod;
            hash2 = (hash2 + symbol) % mod2;

            substringHash = (substringHash * coeff) % mod;
            substringHash2 = (substringHash2 * coeff2) % mod2;

            substringHash = (substringHash + (long) substring.charAt(i)) % mod;
            substringHash2 = (substringHash2 + (long) substring.charAt(i)) % mod2;
        }

        queueSymbol = queue.remove();
        firstSymbol = (queueSymbol * nowCoeff) % mod;
        firstSymbol2 = (queueSymbol * nowCoeff2) % mod2;

        if (hash == substringHash && hash2 == substringHash2) {
            answer.add((long) 0);
        }

        while ((symbol = openFile.read()) >= 0) {
            queue.add(symbol);
            queueSymbol = queue.remove();

            hash = (hash + mod - firstSymbol) % mod;
            hash = (hash * coeff) % mod;
            hash = (hash + symbol) % mod;

            hash2 = (hash2 + mod2 - firstSymbol2) % mod2;
            hash2 = (hash2 * coeff2) % mod2;
            hash2 = (hash2 + symbol) % mod2;

            if (hash == substringHash && hash2 == substringHash2) {
                answer.add(nowIndx);
            }

            firstSymbol = (queueSymbol * nowCoeff) % mod;
            firstSymbol2 = (queueSymbol * nowCoeff2) % mod2;

            nowIndx++;
        }

        return answer;
    }

    /**
     * Открытие файла по его имени.
     *
     * @param file имя файла.
     * @param substring искомая подстрока.
     * @return список найденных вхождений подстроки.
     * @throws IOException исключение ввода-вывода.
     */
    public static List<Long> find(String file, String substring) throws IOException {
        try (Reader openFile = new BufferedReader(
                new InputStreamReader(((Main.class.getResourceAsStream("/" + file)))))) {
            return findWithReader(openFile, substring);
        }
    }

    /**
     * Main.
     *
     * @param args аргументы main.
     */
    public static void main(String[] args) {

    }
}