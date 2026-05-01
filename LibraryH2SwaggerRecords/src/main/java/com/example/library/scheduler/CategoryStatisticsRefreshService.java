package com.example.library.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CategoryStatisticsRefreshService {

    private static final Logger log = LoggerFactory.getLogger(CategoryStatisticsRefreshService.class);

    private final JdbcTemplate jdbcTemplate;

    public CategoryStatisticsRefreshService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Scheduled(fixedRateString = "${app.statistics.refresh-ms:60000}")
    public void refreshStatisticsCache() {
        log.info("Refreshing category_statistics_cache - start");

        jdbcTemplate.execute("DELETE FROM category_statistics_cache");

        jdbcTemplate.execute("""
                INSERT INTO category_statistics_cache(category, total_books, total_available_copies, bad_state_books)
                SELECT
                    category,
                    COUNT(*) AS total_books,
                    COALESCE(SUM(available_copies), 0) AS total_available_copies,
                    SUM(CASE WHEN state = 'BAD' THEN 1 ELSE 0 END) AS bad_state_books
                FROM books
                GROUP BY category
                """);

        log.info("Refreshing category_statistics_cache - end");
    }
}