package com.quantcast;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = null;
        String date = null;

        for (int i = 0; i < args.length - 1; i++) {
            if ("-f".equals(args[i])) {
                filePath = args[i + 1];
            } else if ("-d".equals(args[i])) {
                date = args[i + 1];
            }
        }

        if (filePath == null || date == null) {
            System.err.println("Usage: java -jar most-active-cookie.jar -f <file> -d <yyyy-MM-dd>");
            System.exit(1);
        }

        CookieAnalyzer analyzer = new CookieAnalyzer();

        try {
            List<String> mostActive = analyzer.getMostActiveCookies(filePath, date);
            for (String cookie : mostActive) {
                System.out.println(cookie);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(2);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(3);
        }
    }
}
