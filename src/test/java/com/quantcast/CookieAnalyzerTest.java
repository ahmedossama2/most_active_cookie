package com.quantcast;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CookieAnalyzerTest {

    private CookieAnalyzer analyzer;
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        analyzer = new CookieAnalyzer();
        tempFile = File.createTempFile("test_cookie_log", ".csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("cookie,timestamp\n");
            writer.write("cookie1,2018-12-09T14:19:00+00:00\n");
            writer.write("cookie2,2018-12-09T10:13:00+00:00\n");
            writer.write("cookie1,2018-12-09T07:25:00+00:00\n");
            writer.write("cookie3,2018-12-08T22:03:00+00:00\n");
            writer.write("cookie2,2018-12-08T09:30:00+00:00\n");
        }
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
    }

    @Test
    void testMostActiveSingle() throws IOException {
        List<String> result = analyzer.getMostActiveCookies(tempFile.getAbsolutePath(), "2018-12-09");
        assertEquals(1, result.size());
        assertEquals("cookie1", result.get(0));
    }

    @Test
    void testMostActiveMultiple() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile, true))) {
            writer.write("cookie2,2018-12-09T20:00:00+00:00\n");
        }

        List<String> result = analyzer.getMostActiveCookies(tempFile.getAbsolutePath(), "2018-12-09");
        assertEquals(2, result.size());
        assertTrue(result.contains("cookie1"));
        assertTrue(result.contains("cookie2"));
    }

    @Test
    void testNoCookiesForDate() throws IOException {
        List<String> result = analyzer.getMostActiveCookies(tempFile.getAbsolutePath(), "2018-12-07");
        assertTrue(result.isEmpty());
    }

    @Test
    void testInvalidFile() {
        assertThrows(IOException.class, () -> analyzer.getMostActiveCookies("invalid_path.csv", "2018-12-09"));
    }
}
