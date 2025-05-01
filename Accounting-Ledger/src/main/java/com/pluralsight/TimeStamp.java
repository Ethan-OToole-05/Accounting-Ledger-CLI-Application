package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeStamp {
    private LocalDateTime timestamp;

    public TimeStamp() {

    }

    //Get current time and return the Date and Time as a stamp.
    public LocalDateTime getTimestamp() {
        timestamp = LocalDateTime.now();
        return timestamp;
    }

    //Return the stamp formatted as a string for use.
    public String formatTimestamp(LocalDateTime stamp) {
        LocalDateTime timestamp = stamp;

        String formattedTimestamp = timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss"));

        return formattedTimestamp;
    }
}
