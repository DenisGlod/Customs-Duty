--Insert Table Role
insert into role(name) values ('Администратор');
insert into role(name) values ('Пользователь');
-- Insert Table Client
insert into client(login, password, role_id, status, lastName, firstName, middleName) values ('admin', 'admin', 1, true, 'Петров', 'Василий', 'Иванович');
insert into client(login, password, role_id, status, lastName, firstName, middleName) values ('user', 'user', 2, true, 'Савиков', 'Василий', 'Васильевич');
-- Insert Table Product
insert into product(code, name, percent) values (2, 'Яблоко', 1.4);
insert into product(code, name, percent) values (2, 'Морковь', 2.0);
insert into product(code, name, percent) values (1, 'Заяц', 2.3);
insert into product(code, name, percent) values (1, 'Курица', 1.2);
-- Insert Table Post
insert into post(adress, name) values ('Гродненская обл, Вороновский р-н, д.Бенякони', 'Пункт пропуска Бенякони');
insert into post(adress, name) values ('Витебская обл, Верхнедвинский р-н, д.Григоровщина', 'Пункт пропуска Григоровщина');
-- Insert Table Cargo
insert into cargo(uuid, dateCargo, post_id) values ('asd12asd12', now(), 1);
insert into cargo(uuid, dateCargo, post_id) values ('lkjhg76653', now(), 1);
insert into cargo(uuid, dateCargo, post_id) values ('iuytrqw561', now(), 2);
insert into cargo(uuid, dateCargo, post_id) values ('kjhasg2212', now(), 2);
-- Insert Table ProductCargo
insert into productcargo(product_id, cargo_id, weight, cost, customsDuty) values (1, 1, 100, 10.0, 2);
insert into productcargo(product_id, cargo_id, weight, cost, customsDuty) values (2, 1, 100, 10.0, 2);
insert into productcargo(product_id, cargo_id, weight, cost, customsDuty) values (3, 1, 100, 10.0, 2);
insert into productcargo(product_id, cargo_id, weight, cost, customsDuty) values (4, 1, 100, 10.0, 2);
insert into productcargo(product_id, cargo_id, weight, cost, customsDuty) values (1, 2, 100, 10.0, 2);
insert into productcargo(product_id, cargo_id, weight, cost, customsDuty) values (1, 3, 100, 10.0, 2);
insert into productcargo(product_id, cargo_id, weight, cost, customsDuty) values (1, 4, 100, 10.0, 2);
