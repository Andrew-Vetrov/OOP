package ru.nsu.chepik;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void multipleMatchesTest() throws IOException {
        // input.txt содержит: "бра-браслет и браслеты"
        List<Long> ans = Main.find("multipleMatchesTest.txt", "бра");
        assertEquals(Arrays.asList(0L, 4L, 14L), ans);
    }

    @Test
    void noMatchesTest() throws IOException {
        // input.txt содержит: "в тексте нет совпадений"
        List<Long> ans = Main.find("noMatchesTest.txt", "бра");
        assertEquals(Arrays.asList(), ans);
    }

    @Test
    void emptySubstringTest() throws IOException {
        // input.txt содержит: "браслет"
        List<Long> ans = Main.find("emptySubstringTest.txt", "");
        assertTrue(ans.isEmpty());
    }

    @Test
    void emptyStringTest() throws IOException {
        // input.txt содержит: ""
        List<Long> ans = Main.find("emptyStringTest.txt", "бра");
        assertEquals(Arrays.asList(), ans);
    }

    @Test
    void fullStringMatchTest() throws IOException {
        // input.txt содержит: "бра"
        List<Long> ans = Main.find("fullStringMatchTest.txt", "бра");
        assertEquals(Arrays.asList(0L), ans);
    }

    @Test
    void overlappingMatchesTest() throws IOException {
        // input.txt содержит: "брабрабра"
        List<Long> ans = Main.find("overlappingMatchesTest.txt", "бра");
        assertEquals(Arrays.asList(0L, 3L, 6L), ans);
    }

    @Test
    void caseSensitiveTest() throws IOException {
        // input.txt содержит: "Браслет и бра"
        List<Long> ans = Main.find("caseSensitiveTest.txt", "бра");
        assertEquals(Arrays.asList(10L), ans);
    }

    @Test
    void substringLongerThanStringTest() throws IOException {
        // input.txt содержит: "бра"
        List<Long> ans = Main.find("substringLongerThanStringTest.txt", "браслет");
        assertEquals(Arrays.asList(), ans);
    }

    @Test
    void matchAtEndTest() throws IOException {
        // input.txt содержит: "ахахахах - бра"
        List<Long> ans = Main.find("matchAtEndTest.txt", "бра");
        assertEquals(Arrays.asList(11L), ans);
    }

    @Test
    void matchAtStartTest() throws IOException {
        // input.txt содержит: "браслет и бра"
        List<Long> ans = Main.find("matchAtStartTest.txt", "браслет");
        assertEquals(Arrays.asList(0L), ans);
    }

    @Test
    void multilingualContentTest() throws IOException {
        // input.txt содержит: "这是一个测试 браслет и example"
        List<Long> ans = Main.find("multilingualContentTest.txt", "测试");
        assertEquals(Arrays.asList(4L), ans);
    }

    @Test
    void multilingualSubstringTest() throws IOException {
        // input.txt содержит: "Это тест, включающий разные языки: пример, example, 例а子"
        List<Long> ans = Main.find("multilingualSubstringTest.txt", "例а子");
        assertEquals(Arrays.asList(52L), ans);
    }

    @Test
    void mixedLanguageMatchTest() throws IOException {
        // input.txt содержит: "example пример браслет"
        List<Long> ans = Main.find("mixedLanguageMatchTest.txt", "example");
        assertEquals(Arrays.asList(0L), ans);
    }

    @Test
    void bigDataTest() throws  IOException {
        String fileName = "bigData.txt";
        File file = new File(fileName);
        String substring = "абвгдеёж";
        long fileSize = 20L * 1024 * 1024 * 1024;
        long patternLength = substring.length();
        long repeatCount = fileSize / patternLength;

        try (Writer bwriter = new BufferedWriter(new FileWriter(file))) {
            file.createNewFile();

            String manySubstrings = substring.repeat(1024);
            for (long i = 0; i < repeatCount / 1024; i++) {
                bwriter.write(manySubstrings);
            }

            bwriter.flush();
        }

        try (Reader openFile = new BufferedReader(new FileReader(file))) {
            List<Long> ans = Main.findWithReader(openFile, substring);

            assertTrue(!ans.isEmpty());
            assertEquals(repeatCount, ans.size());
            for (long i = 0; i < repeatCount; i++) {
                assertEquals(i * patternLength, ans.get((int) i));
            }
        } finally {
            file.delete();
        }
    }
}
