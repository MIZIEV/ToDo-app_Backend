create database db_for_todo;
use db_for_todo;
create table todo
(
    id           int auto_increment primary key,
    text         varchar(200),
    is_completed boolean,
    todo_unique_key     varchar(100)
);