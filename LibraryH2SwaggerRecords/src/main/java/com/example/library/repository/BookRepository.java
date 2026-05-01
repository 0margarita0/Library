package com.example.library.repository;

import com.example.library.model.Book;
import com.example.library.projection.BookExpandedProjection;
import com.example.library.projection.BookShortProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    List<BookShortProjection> findAllProjectedBy();

    @Query("""
            SELECT
                b.id AS id,
                b.name AS name,
                b.category AS category,
                b.state AS state,
                b.availableCopies AS availableCopies,
                a.name AS authorName,
                a.surname AS authorSurname,
                c.name AS countryName
            FROM Book b
            JOIN b.author a
            JOIN a.country c
            """)
    List<BookExpandedProjection> findAllExpandedProjectedBy();

    /// /////////////////////////////////////////////////
    @EntityGraph(attributePaths = {"author", "author.country"})
    Optional<Book> findDetailedById(Long id);
}