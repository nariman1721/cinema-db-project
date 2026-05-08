-- SELECT
SELECT * FROM movies;

-- JOIN
SELECT c.full_name, t.price
FROM tickets t
JOIN customers c ON t.customer_id = c.customer_id;

-- INDEX TEST
EXPLAIN ANALYZE SELECT * FROM movies WHERE title = 'Avatar';