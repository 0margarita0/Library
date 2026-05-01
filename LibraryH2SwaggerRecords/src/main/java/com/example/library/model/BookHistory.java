package com.example.library.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "book_history")
public class BookHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private LocalDateTime eventTime;

    public BookHistory() {
    }

    public BookHistory(Long bookId, String description, LocalDateTime eventTime) {
        this.bookId = bookId;
        this.description = description;
        this.eventTime = eventTime;
    }

    public Long getId() {
        return id;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }
}