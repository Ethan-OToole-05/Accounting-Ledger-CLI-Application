package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeStamp {
    private LocalDateTime timestamp;

    public TimeStamp() {

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
