-- INSERT INTO movies (title, release_year, duration, rating)
-- VALUES
-- ('Avatar', 2009, 162, 'PG-13'),
-- ('Biznes po kazahski', 2010, 148, 'PG-13'),
-- ('Interstellar', 2014, 169, 'PG-13');

-- INSERT INTO actors (full_name, nationality)
-- VALUES
-- ('Leonardo DiCaprio', 'American'),
-- ('Nurlan Koyanbayev', 'Kazakh'),
-- ('Matthew McConaughey', 'American');

-- INSERT INTO movie_actors (movie_id, actor_id)
-- VALUES
-- (1,2),
-- (2,1),
-- (3,3);

-- INSERT INTO halls (hall_name, capacity)
-- VALUES
-- ('Hall A', 120),
-- ('Hall B', 80),
-- ('Hall C', 150);


-- INSERT INTO schedules (movie_id, hall_id, show_date, show_time)
-- VALUES
-- (1,1,'2026-04-20','18:00'),
-- (2,2,'2026-04-20','20:00'),
-- (3,3,'2026-04-21','19:30');

-- Customers
-- INSERT INTO customers (full_name, email)
-- VALUES
-- ('Nariman Suleimenov','nariman@mail.com'),
-- ('Alice Karimova','alice@mail.com'),
-- ('Bolat Seitkali','bolat@mail.com');

-- Tickets
-- INSERT INTO tickets (customer_id, schedule_id, seat_number, price)
-- VALUES
-- (1,1,'A1',3000),
-- (2,2,'B5',3500),
-- (3,3,'C10',4000);

-- Staff
-- INSERT INTO staff (full_name, position, supervisor_id)
-- VALUES
-- ('John Manager','Manager',NULL),
-- ('Sara Worker','Cashier',1),
-- ('Mike Worker','Cleaner',1);
