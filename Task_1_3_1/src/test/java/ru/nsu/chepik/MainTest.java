package ru.nsu.chepik;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void searchTest() throws IOException {
        List<Long> ans = Main.find("input.txt", "бра");
        assertEquals(Arrays.asList(1L, 8L), ans);
    }
}
