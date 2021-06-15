drop database if exists social_app;
create database social_app;
use social_app;

create table users
(
    id       bigint auto_increment,
    username varchar(50),
    password varchar(255),
    email    varchar(50),
    primary key (id)
) ENGINE InnoDB;

create table follows
(
    user_id      bigint,
    following_id bigint,
    primary key (user_id, following_id),
    constraint fk_follows_users_1 foreign key (user_id) references users (id) on delete cascade,
    constraint fk_follows_users_2 foreign key (following_id) references users (id) on delete cascade
) ENGINE InnoDB;