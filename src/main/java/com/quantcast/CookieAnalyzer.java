package com.quantcast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.*;

public class CookieAnalyzer {

    public List<String> getMostActiveCookies(String filePath, String date) throws IOException {
        Map<String, Integer> cookieCount = new HashMap<>();
        int maxCount = 0;

        LocalDate targetDate = LocalDate.parse(date);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) continue;

                String cookie = parts[0].trim();
                OffsetDateTime timestamp = OffsetDateTime.parse(parts[1].trim());
                LocalDate cookieDate = timestamp.toLocalDate();

                if (cookieDate.equals(targetDate)) {
                    int count = cookieCount.getOrDefault(cookie, 0) + 1;
                    cookieCount.put(cookie, count);
                    maxCount = Math.max(maxCount, count);
                }
            }
        }

        List<String> mostActive = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : cookieCount.entrySet()) {
            if (entry.getValue() == maxCount) {
                mostActive.add(entry.getKey());
            }
        }

        return mostActive;
    }
}
