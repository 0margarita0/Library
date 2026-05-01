package com.example.library.dto;

import com.example.library.model.enums.BookState;
import com.example.library.model.enums.Category;

public record BookResponse(
        Long id,
        String name,
        Category category,
        String authorFullName,
        BookState state,
        Integer availableCopies
) {
}
