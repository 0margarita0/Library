INSERT INTO countries (name, continent) VALUES ('United Kingdom', 'Europe');
INSERT INTO countries (name, continent) VALUES ('USA', 'North America');
INSERT INTO countries (name, continent) VALUES ('France', 'Europe');

INSERT INTO authors (name, surname, country_id) VALUES ('George', 'Orwell', 1);
INSERT INTO authors (name, surname, country_id) VALUES ('Stephen', 'King', 2);
INSERT INTO authors (name, surname, country_id) VALUES ('Victor', 'Hugo', 3);

INSERT INTO books (name, category, author_id, state, available_copies, created_at)
VALUES ('1984', 'NOVEL', 1, 'GOOD', 5, CURRENT_TIMESTAMP);

INSERT INTO books (name, category, author_id, state, available_copies, created_at)
VALUES ('The Shining', 'THRILER', 2, 'GOOD', 3, CURRENT_TIMESTAMP);

INSERT INTO books (name, category, author_id, state, available_copies, created_at)
VALUES ('Les Miserables', 'CLASSICS', 3, 'BAD', 1, CURRENT_TIMESTAMP);

INSERT INTO users (username, password, role)
VALUES ('admin', 'admin', 'ROLE_ADMIN');