package platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity(name = "code")
public class Code {

    @Id
    @Column
    @JsonIgnore
    private final String codeId;

    @Column
    private String code;

    @Column
    private String date;
    @JsonIgnore
    private final LocalDateTime created;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime expirationTime;

    @Column
    private long time;

    @Column
    private long views;

    @JsonIgnore
    private boolean timeRestriction;

    @JsonIgnore
    private boolean viewsRestriction;

    public Code() {
        codeId = UUID.randomUUID().toString();
        created = LocalDateTime.now();
        date = created.format(FORMATTER);
        timeRestriction = false;
        viewsRestriction = false;
    }

    public Code(String code, long time, long views) {
        codeId = UUID.randomUUID().toString();
        this.code = code;
        created = LocalDateTime.now();
        date = created.format(FORMATTER);
        this.time = time;
        this.views = views;
    }
    @JsonIgnore
    public Long getTimePasses() {
        LocalDateTime tmp = created;
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(tmp, now);
        return duration.getSeconds();
    }

    public String getCodeId() {
        return codeId;
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public void setTimeRestriction(boolean timeRestriction) {
        this.timeRestriction = timeRestriction;
    }

    public void setViewsRestriction(boolean viewsRestriction) {
        this.viewsRestriction = viewsRestriction;
    }

    @Override
    public String toString() {
        return "code: " + code + "\ndate: " + date;
    }

    public boolean isViewsRestriction() {
        return viewsRestriction;
    }

    public boolean isTimeRestriction() {
        return timeRestriction;
    }
}
