package com.example.library.service;

import com.example.library.dto.BookCreateRequest;
import com.example.library.dto.BookListResponse;
import com.example.library.dto.BookResponse;
import com.example.library.dto.BookUpdateRequest;
import com.example.library.model.enums.BookState;
import com.example.library.model.enums.Category;
import com.example.library.projection.BookExpandedProjection;
import com.example.library.projection.BookShortProjection;
import org.springframework.data.domain.Page;
import com.example.library.projection.BookOverviewViewProjection;
import com.example.library.projection.CategoryStatisticsProjection;
import java.util.List;

public interface BookService {
    List<BookResponse> findAll();
    BookResponse findById(Long id);
    BookResponse create(BookCreateRequest request);
    BookResponse update(Long id, BookUpdateRequest request);
    void delete(Long id);
    BookResponse markAsRented(Long id);

    List<BookShortProjection> findAllShortProjected();
    List<BookExpandedProjection> findAllExpandedProjected();
    BookResponse findDetailedById(Long id);

    Page<BookListResponse> search(
            int page,
            int size,
            String sortBy,
            String sortDir,
            Category category,
            BookState state,
            Long authorId,
            Boolean available
    );
    List<BookOverviewViewProjection> findAllFromView();
    List<CategoryStatisticsProjection> findCategoryStatistics();

    List<CategoryStatisticsProjection> getCategoryStatistics();
}