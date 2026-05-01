package com.example.library.event;

public class BookRentedEvent {

    private final String bookName;
    private final int remainingCopies;

    public BookRentedEvent(String bookName, int remainingCopies) {
        this.bookName = bookName;
        this.remainingCopies = remainingCopies;
    }

    public String getBookName() {
        return bookName;
    }

    public int getRemainingCopies() {
        return remainingCopies;
    }
}