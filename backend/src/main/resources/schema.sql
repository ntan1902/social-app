drop database if exists social_app;
create database social_app;

use social_app;

drop table if exists users;
create table users (
    id bigint auto_increment,
    username varchar(50),
    password varchar(255),
    email varchar(50),
    primary key (id)
);

drop table if exists `follows`;
create table follows (
    user_id bigint,
    following_id bigint,
    primary key (user_id, following_id),
    foreign key (user_id) references users(id),
    foreign key (following_id) references users(id)
);