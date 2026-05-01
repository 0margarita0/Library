package com.example.library.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activity_log")
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookName;

    private String eventType;

    private LocalDateTime timestamp;

    public ActivityLog() {}

    public ActivityLog(String bookName, String eventType) {
        this.bookName = bookName;
        this.eventType = eventType;
        this.timestamp = LocalDateTime.now();
    }

    // getters & setters
    public Long getId() { return id; }
    public String getBookName() { return bookName; }
    public String getEventType() { return eventType; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public void setId(Long id) { this.id = id; }
    public void setBookName(String bookName) { this.bookName = bookName; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}