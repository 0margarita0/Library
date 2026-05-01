package com.example.library.projection;

import com.example.library.model.enums.BookState;
import com.example.library.model.enums.Category;

public interface BookShortProjection {
    Long getId();
    String getName();
    Category getCategory();
    BookState getState();
    Integer getAvailableCopies();
}