package com.example.library.repository;

import com.example.library.model.Book;
import com.example.library.projection.CategoryStatisticsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryStatisticsRepository extends JpaRepository<Book, Long> {

    @Query(value = """
            SELECT
                csc.category AS category,
                csc.total_books AS totalBooks,
                csc.total_available_copies AS totalAvailableCopies,
                csc.bad_state_books AS badStateBooks
            FROM category_statistics_cache csc
            ORDER BY csc.category
            """, nativeQuery = true)
    List<CategoryStatisticsProjection> findAllStatistics();
}