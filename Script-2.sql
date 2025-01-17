insert into users (name, age, email) values
('John', 30, 'john@example.com'),
('Alice', 25, 'alice@example.com'),
('Bob', 35, 'bob@example.com');

select * from users;

delete from users where name = 'Bob';

select * from users;

truncate table users;

