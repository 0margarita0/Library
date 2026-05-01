package com.example.library.service;

import com.example.library.dto.BookHistoryResponse;

public interface BookHistoryService {
    BookHistoryResponse getHistoryForBook(Long bookId);
}