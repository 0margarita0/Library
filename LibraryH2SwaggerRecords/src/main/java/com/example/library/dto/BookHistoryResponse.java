package com.example.library.dto;

import java.util.List;

public record BookHistoryResponse(
        Long bookId,
        List<String> descriptions
) {
}