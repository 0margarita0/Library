package com.example.library.controller;

import com.example.library.dto.BookCreateRequest;
import com.example.library.dto.BookListResponse;
import com.example.library.dto.BookResponse;
import com.example.library.dto.BookUpdateRequest;
import com.example.library.model.enums.BookState;
import com.example.library.model.enums.Category;
import com.example.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.library.projection.BookExpandedProjection;
import com.example.library.projection.BookShortProjection;
import java.util.List;
import com.example.library.projection.BookOverviewViewProjection;
import com.example.library.projection.CategoryStatisticsProjection;
import com.example.library.model.ActivityLog;
import com.example.library.repository.ActivityLogRepository;
import com.example.library.dto.BookHistoryResponse;
import com.example.library.service.BookHistoryService;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "Book management endpoints")
public class BookController {

    private final BookService bookService;
    private final ActivityLogRepository activityLogRepository;
    private final BookHistoryService bookHistoryService;

    public BookController(BookService bookService,
                          ActivityLogRepository activityLogRepository,
                          BookHistoryService bookHistoryService) {
        this.bookService = bookService;
        this.activityLogRepository = activityLogRepository;
        this.bookHistoryService = bookHistoryService;
    }

    @GetMapping
    @Operation(summary = "Get all books")
    public List<BookResponse> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/search")
    @Operation(summary = "Search books with pagination, sorting and filtering")
    public Page<BookListResponse> search(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) BookState state,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Boolean available
    ) {
        return bookService.search(page, size, sortBy, sortDir, category, state, authorId, available);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by id")
    public BookResponse findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new book")
    public BookResponse create(@Valid @RequestBody BookCreateRequest request) {
        return bookService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update existing book")
    public BookResponse update(@PathVariable Long id, @Valid @RequestBody BookUpdateRequest request) {
        return bookService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a book")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    @PatchMapping("/{id}/rent")
    @Operation(summary = "Mark book as rented")
    public BookResponse markAsRented(@PathVariable Long id) {
        return bookService.markAsRented(id);
    }

    @GetMapping("/projections/short")
    @Operation(summary = "Get all books as short projection")
    public List<BookShortProjection> findAllShortProjected() {
        return bookService.findAllShortProjected();
    }

    @GetMapping("/projections/expanded")
    @Operation(summary = "Get all books as expanded projection")
    public List<BookExpandedProjection> findAllExpandedProjected() {
        return bookService.findAllExpandedProjected();
    }

    @GetMapping("/detailed/{id}")
    @Operation(summary = "Get detailed book by id using EntityGraph")
    public BookResponse findDetailedById(@PathVariable Long id) {
        return bookService.findDetailedById(id);
    }

    @GetMapping("/view")
    @Operation(summary = "Get books from database view")
    public List<BookOverviewViewProjection> findAllFromView() {
        return bookService.findAllFromView();
    }

    @GetMapping("/statistics")
    @Operation(summary = "Get category statistics from cache table")
    public List<CategoryStatisticsProjection> findCategoryStatistics() {
        return bookService.findCategoryStatistics();
    }

    @GetMapping("/activities")
    @Operation(summary = "Get activity log")
    public List<ActivityLog> getActivities() {
        return activityLogRepository.findAll();
    }

    @GetMapping("/{id}/history")
    @Operation(summary = "Get history for book by id")
    public BookHistoryResponse getBookHistory(@PathVariable Long id) {
        return bookHistoryService.getHistoryForBook(id);
    }

    @GetMapping("/statistics")
    public List<CategoryStatisticsProjection> getStatistics() {
        return bookService.getCategoryStatistics();
    }
}