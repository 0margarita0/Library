package com.example.library.service.impl;

import com.example.library.dto.BookCreateRequest;
import com.example.library.dto.BookListResponse;
import com.example.library.dto.BookResponse;
import com.example.library.dto.BookUpdateRequest;
import com.example.library.event.BookRentedEvent;
import com.example.library.exception.InvalidOperationException;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.BookHistory;
import com.example.library.model.enums.BookState;
import com.example.library.model.enums.Category;
import com.example.library.projection.BookExpandedProjection;
import com.example.library.projection.BookOverviewViewProjection;
import com.example.library.projection.BookShortProjection;
import com.example.library.projection.CategoryStatisticsProjection;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookHistoryRepository;
import com.example.library.repository.BookOverviewViewRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CategoryStatisticsRepository;
import com.example.library.service.BookService;
import com.example.library.specification.BookSpecification;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookOverviewViewRepository bookOverviewViewRepository;
    private final CategoryStatisticsRepository categoryStatisticsRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final BookHistoryRepository bookHistoryRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           BookOverviewViewRepository bookOverviewViewRepository,
                           CategoryStatisticsRepository categoryStatisticsRepository,
                           ApplicationEventPublisher eventPublisher,
                           BookHistoryRepository bookHistoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookOverviewViewRepository = bookOverviewViewRepository;
        this.categoryStatisticsRepository = categoryStatisticsRepository;
        this.eventPublisher = eventPublisher;
        this.bookHistoryRepository = bookHistoryRepository;
    }

    @Override
    public List<BookResponse> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public BookResponse findById(Long id) {
        return mapToResponse(getBook(id));
    }

    @Override
    public BookResponse create(BookCreateRequest request) {
        Author author = getAuthor(request.authorId());

        Book book = new Book();
        book.setName(request.name());
        book.setCategory(request.category());
        book.setAuthor(author);
        book.setState(request.state());
        book.setAvailableCopies(request.availableCopies());

        Book saved = bookRepository.save(book);

        LocalDateTime now = LocalDateTime.now();
        saveHistory(
                saved.getId(),
                "Added book with id " + saved.getId() + " in " + now,
                now
        );

        return mapToResponse(saved);
    }

    @Override
    public BookResponse update(Long id, BookUpdateRequest request) {
        Book book = getBook(id);
        Author author = getAuthor(request.authorId());

        book.setName(request.name());
        book.setCategory(request.category());
        book.setAuthor(author);
        book.setState(request.state());
        book.setAvailableCopies(request.availableCopies());

        Book saved = bookRepository.save(book);

        LocalDateTime now = LocalDateTime.now();
        saveHistory(
                saved.getId(),
                "Updated book with id " + saved.getId() + " in " + now,
                now
        );

        return mapToResponse(saved);
    }

    @Override
    public void delete(Long id) {
        Book book = getBook(id);

        LocalDateTime now = LocalDateTime.now();
        saveHistory(
                book.getId(),
                "Deleted book with id " + book.getId() + " in " + now,
                now
        );

        bookRepository.delete(book);
    }

    @Override
    public BookResponse markAsRented(Long id) {
        Book book = getBook(id);

        if (book.getState() == BookState.BAD) {
            throw new InvalidOperationException("Book is BAD");
        }

        if (book.getAvailableCopies() <= 0) {
            throw new InvalidOperationException("No copies available");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);

        Book saved = bookRepository.save(book);

        LocalDateTime now = LocalDateTime.now();
        saveHistory(
                saved.getId(),
                "Rented book with id " + saved.getId() + " in " + now,
                now
        );

        eventPublisher.publishEvent(
                new BookRentedEvent(saved.getName(), saved.getAvailableCopies())
        );

        return mapToResponse(saved);
    }

    @Override
    public Page<BookListResponse> search(int page,
                                         int size,
                                         String sortBy,
                                         String sortDir,
                                         Category category,
                                         BookState state,
                                         Long authorId,
                                         Boolean available) {

        String sortField = switch (sortBy) {
            case "createdAt" -> "createdAt";
            case "name" -> "name";
            default -> "name";
        };

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortField));

        Specification<Book> specification = Specification
                .where(BookSpecification.hasCategory(category))
                .and(BookSpecification.hasState(state))
                .and(BookSpecification.hasAuthor(authorId))
                .and(BookSpecification.hasAvailableCopies(available));

        return bookRepository.findAll(specification, pageRequest)
                .map(book -> new BookListResponse(
                        book.getId(),
                        book.getName(),
                        book.getCategory(),
                        book.getState(),
                        book.getAvailableCopies(),
                        book.getAuthor().getName() + " " + book.getAuthor().getSurname()
                ));
    }

    @Override
    public List<BookShortProjection> findAllShortProjected() {
        return bookRepository.findAllProjectedBy();
    }

    @Override
    public List<BookExpandedProjection> findAllExpandedProjected() {
        return bookRepository.findAllExpandedProjectedBy();
    }

    @Override
    public BookResponse findDetailedById(Long id) {
        Book book = bookRepository.findDetailedById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
        return mapToResponse(book);
    }

    @Override
    public List<BookOverviewViewProjection> findAllFromView() {
        return bookOverviewViewRepository.findAllFromView();
    }

    @Override
    public List<CategoryStatisticsProjection> findCategoryStatistics() {
        return categoryStatisticsRepository.findAllStatistics();
    }

    private Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
    }

    private Author getAuthor(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + id));
    }

    private BookResponse mapToResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getName(),
                book.getCategory(),
                book.getAuthor().getName() + " " + book.getAuthor().getSurname(),
                book.getState(),
                book.getAvailableCopies()
        );
    }

    private void saveHistory(Long bookId, String description, LocalDateTime eventTime) {
        bookHistoryRepository.save(
                new BookHistory(bookId, description, eventTime)
        );
    }

    @Override
    public List<CategoryStatisticsProjection> getCategoryStatistics() {
        return bookOverviewViewRepository.getCategoryStatistics();
    }
}