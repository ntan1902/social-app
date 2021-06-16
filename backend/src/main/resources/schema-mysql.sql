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
    userId      bigint,
    followingId bigint,
    primary key (userId, followingId),
    constraint fk_follows_users_1 foreign key (userId) references users (id) on delete cascade,
    constraint fk_follows_users_2 foreign key (followingId) references users (id) on delete cascade
) ENGINE InnoDB;

create table posts
(
    id          bigint auto_increment,
    userId      bigint,
    description text,
    img         varchar(255),
    createdAt   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt   DATETIME  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id),
    constraint fk_posts_users foreign key (userId) references users (id) on delete cascade
) ENGINE InnoDB;

create table like_posts
(
    postId bigint,
    userId bigint,
    primary key (postId, userId),
    constraint fk_like_posts_posts foreign key (postId) references posts (userId) on delete cascade,
    constraint fk_like_posts_users foreign key (userId) references users (id) on delete cascade
) ENGINE InnoDB;