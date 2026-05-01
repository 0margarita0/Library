package com.example.library.service.impl;

import com.example.library.dto.BookHistoryResponse;
import com.example.library.repository.BookHistoryRepository;
import com.example.library.service.BookHistoryService;
import org.springframework.stereotype.Service;

@Service
public class BookHistoryServiceImpl implements BookHistoryService {

    private final BookHistoryRepository bookHistoryRepository;

    public BookHistoryServiceImpl(BookHistoryRepository bookHistoryRepository) {
        this.bookHistoryRepository = bookHistoryRepository;
    }

    @Override
    public BookHistoryResponse getHistoryForBook(Long bookId) {
        return new BookHistoryResponse(
                bookId,
                bookHistoryRepository.findByBookIdOrderByEventTimeAsc(bookId)
                        .stream()
                        .map(history -> history.getDescription())
                        .toList()
        );
    }
}