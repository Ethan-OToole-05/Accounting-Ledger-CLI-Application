package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date {
    private LocalDateTime timestamp;

    public Date() {

    }

    public LocalDateTime getTimestamp() {
        timestamp = LocalDateTime.now();
        return timestamp;
    }

    public String formatTimestamp(LocalDateTime stamp) {
        LocalDateTime timestamp = stamp;

        String formattedTimestamp = timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return formattedTimestamp;
    }
}
