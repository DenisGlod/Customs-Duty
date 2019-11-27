--Insert Table Role
insert into role(name) values ('Администратор');
insert into role(name) values ('Пользователь');
-- Insert Table Client
insert into client(login, password, role_id, status, lastName, firstName, middleName) values ('admin', 'admin', 1, true, 'Петров', 'Василий', 'Иванович');
insert into client(login, password, role_id, status, lastName, firstName, middleName) values ('user', 'user', 2, true, 'Савиков', 'Василий', 'Васильевич');