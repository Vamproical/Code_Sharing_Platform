package platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name = "code")
public class Code {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long codeId;
    @Column
    private String code;

    @Column
    private String date;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Code() {
        date = LocalDateTime.now().format(FORMATTER);
    }

    public Code(String code) {
        this.code = code;
        date = LocalDateTime.now().format(FORMATTER);
    }

    public void setCode(String code) {
        this.code = code;
        date = LocalDateTime.now().format(FORMATTER);
    }

    public long getCodeId() {
        return codeId;
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
