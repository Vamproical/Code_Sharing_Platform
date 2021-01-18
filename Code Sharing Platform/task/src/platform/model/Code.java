package platform.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Code {
    private String code;
    private LocalDateTime timeCreated;
    private String date;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Code() {
        timeCreated = LocalDateTime.now();
        date = timeCreated.format(FORMATTER);
    }

    public Code(String code) {
        this.code = code;
        timeCreated = LocalDateTime.now();
        date = timeCreated.format(FORMATTER);
    }

    public void setCode(String code) {
        this.code = code;
        timeCreated = LocalDateTime.now();
        date = timeCreated.format(FORMATTER);
    }

    @Override
    public String toString() {
        return "code: " + code + "\ndate: " + date;
    }

    public String getDate() {
        return date;
    }

    public String getCode() {
        return code;
    }
}
