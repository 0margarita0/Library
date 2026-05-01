package com.example.library.repository;

import com.example.library.projection.BookOverviewViewProjection;
import com.example.library.projection.CategoryStatisticsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookOverviewViewRepository extends JpaRepository<com.example.library.model.Book, Long> {

    @Query(value = """
            SELECT
                v.id AS id,
                v.name AS name,
                v.category AS category,
                v.state AS state,
                v.available_copies AS availableCopies,
                v.author_full_name AS authorFullName,
                v.country_name AS countryName
            FROM book_overview_view v
            """, nativeQuery = true)
    List<BookOverviewViewProjection> findAllFromView();

    @Query(value = "SELECT * FROM category_statistics_view", nativeQuery = true)
    List<CategoryStatisticsProjection> getCategoryStatistics();
}