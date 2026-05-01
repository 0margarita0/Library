package com.example.library.specification;

import com.example.library.model.Book;
import com.example.library.model.enums.BookState;
import com.example.library.model.enums.Category;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> hasCategory(Category category) {
        return (root, query, cb) ->
                category == null ? null : cb.equal(root.get("category"), category);
    }

    public static Specification<Book> hasState(BookState state) {
        return (root, query, cb) ->
                state == null ? null : cb.equal(root.get("state"), state);
    }

    public static Specification<Book> hasAuthor(Long authorId) {
        return (root, query, cb) ->
                authorId == null ? null : cb.equal(root.get("author").get("id"), authorId);
    }

    public static Specification<Book> hasAvailableCopies(Boolean available) {
        return (root, query, cb) -> {
            if (available == null) {
                return null;
            }
            return available
                    ? cb.greaterThan(root.get("availableCopies"), 0)
                    : cb.equal(root.get("availableCopies"), 0);
        };
    }
}