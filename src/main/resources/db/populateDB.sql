DELETE FROM user_roles;
DELETE FROM orders;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001),
       ('USER', 100001);

INSERT INTO orders (date_time, description, price, user_id)
VALUES ('2020-06-03 10:00:00', 'Create test for Java EE app', 40000, 100000),
       ('2020-06-03 10:00:00', 'Create Landing Page', 10000, 100001),
       ('2020-06-03 10:00:00', 'Create Android App', 30000, 100000)