CREATE VIEW IF NOT EXISTS book_overview_view AS
SELECT
    b.id AS id,
    b.name AS name,
    b.category AS category,
    b.state AS state,
    b.available_copies AS available_copies,
    CONCAT(a.name, ' ', a.surname) AS author_full_name,
    c.name AS country_name
FROM books b
         JOIN authors a ON b.author_id = a.id
         JOIN countries c ON a.country_id = c.id;

CREATE TABLE IF NOT EXISTS category_statistics_cache (
    category VARCHAR(50) PRIMARY KEY,
    total_books BIGINT NOT NULL,
    total_available_copies BIGINT NOT NULL,
    bad_state_books BIGINT NOT NULL
    );