--CREATE TABLE genres (
--genre_id SERIAL PRIMARY KEY,
--genre_name VARCHAR(100) NOT NULL UNIQUE
--);
--CREATE TABLE movie_genres (
--movie_id INT NOT NULL,
--genre_id INT NOT NULL,
--PRIMARY KEY (movie_id, genre_id),
--FOREIGN KEY (movie_id)
--REFERENCES movies(movie_id)
--ON DELETE CASCADE,
--FOREIGN KEY (genre_id)
--REFERENCES genres(genre_id)
--ON DELETE CASCADE
--);
-- CREATE TABLE movies (
--     movie_id SERIAL PRIMARY KEY,
--     title VARCHAR(200) NOT NULL,
--     release_year INT CHECK (release_year > 1900),
--     duration INT CHECK (duration > 0),
--     rating VARCHAR(10)
-- );

-- CREATE TABLE actors (
--     actor_id SERIAL PRIMARY KEY,
--     full_name VARCHAR(150) NOT NULL,
--     nationality VARCHAR(100)
-- );

-- CREATE TABLE movie_actors (
--     movie_id INT NOT NULL,
--     actor_id INT NOT NULL,
--     PRIMARY KEY (movie_id, actor_id),

--     FOREIGN KEY (movie_id)
--         REFERENCES movies(movie_id)
--         ON DELETE CASCADE,

--     FOREIGN KEY (actor_id)
--         REFERENCES actors(actor_id)
--         ON DELETE CASCADE
-- );

-- CREATE TABLE halls (
--     hall_id SERIAL PRIMARY KEY,
--     hall_name VARCHAR(100) NOT NULL UNIQUE,
--     capacity INT NOT NULL CHECK (capacity > 0)
-- );


-- CREATE TABLE schedules (
--     schedule_id SERIAL PRIMARY KEY,
--     movie_id INT NOT NULL,
--     hall_id INT NOT NULL,
--     show_date DATE NOT NULL,
--     show_time TIME NOT NULL,

--     FOREIGN KEY (movie_id)
--         REFERENCES movies(movie_id)
--         ON DELETE CASCADE,

--     FOREIGN KEY (hall_id)
--         REFERENCES halls(hall_id)
--         ON DELETE CASCADE
-- );


-- CREATE TABLE customers (
--     customer_id SERIAL PRIMARY KEY,
--     full_name VARCHAR(150) NOT NULL,
--     email VARCHAR(150) UNIQUE NOT NULL
-- );

-- CREATE TABLE tickets (
--     ticket_id SERIAL PRIMARY KEY,
--     customer_id INT NOT NULL,
--     schedule_id INT NOT NULL,
--     seat_number VARCHAR(20) NOT NULL,
--     price DECIMAL(10,2) NOT NULL CHECK (price >= 0),

--     FOREIGN KEY (customer_id)
--         REFERENCES customers(customer_id)
--         ON DELETE CASCADE,

--     FOREIGN KEY (schedule_id)
--         REFERENCES schedules(schedule_id)
--         ON DELETE CASCADE
-- );


-- CREATE TABLE staff (
--     staff_id SERIAL PRIMARY KEY,
--     full_name VARCHAR(150) NOT NULL,
--     position VARCHAR(100) NOT NULL,
--     supervisor_id INT,

--     FOREIGN KEY (supervisor_id)
--         REFERENCES staff(staff_id)
--         ON DELETE SET NULL
-- );
