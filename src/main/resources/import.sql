drop table if exists products;
create table if not exists products(id bigserial primary key, title varchar(255), price integer);
insert into products (title, price) VALUES ('first', 10), ('second', 20), ('third', 30);