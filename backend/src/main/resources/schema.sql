drop database if exists social_app;
create database social_app;

use social_app;

create table users(
    id float auto_increment,
    username varchar(50),
    password varchar(255),
    email varchar(50),
    primary key (id)
);