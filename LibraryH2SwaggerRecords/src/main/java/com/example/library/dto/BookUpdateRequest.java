package com.example.library.dto;

import com.example.library.model.enums.BookState;
import com.example.library.model.enums.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookUpdateRequest(
        @NotBlank(message = "Book name is required")
        String name,

        @NotNull(message = "Category is required")
        Category category,

        @NotNull(message = "Author id is required")
        Long authorId,

        @NotNull(message = "State is required")
        BookState state,

        @NotNull(message = "Available copies is required")
        @Min(value = 0, message = "Available copies cannot be negative")
        Integer availableCopies
) {
}
