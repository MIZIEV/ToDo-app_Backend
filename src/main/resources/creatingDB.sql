create database db_for_todo;
use db_for_todo;
create table todo
(
    id    int auto_increment primary key,
    title varchar(200)
)