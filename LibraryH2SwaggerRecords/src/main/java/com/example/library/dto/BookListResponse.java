package com.example.library.dto;

import com.example.library.model.enums.BookState;
import com.example.library.model.enums.Category;

public record BookListResponse(
        Long id,
        String name,
        Category category,
        BookState state,
        Integer availableCopies,
        String authorFullName
) {
}
