drop database if exists social_app;
create database social_app;
use social_app;

create table users
(
    id              bigint auto_increment,
    username        varchar(50),
    password        varchar(255),
    email           varchar(50),
    profile_picture varchar(255),
    cover_picture   varchar(255),
    description     varchar(255),
    city            varchar(50),
    from_city       varchar(50),
    relationship    enum ('SINGLE', 'MARRIED', 'EMPTY') default 'EMPTY',
    user_role       enum ('USER', 'ADMIN')              default 'USER',
    primary key (id),
    unique index uq_username (username ASC),
    unique index uq_email (email ASC)
) ENGINE InnoDB;

create table follows
(
    user_id      bigint,
    following_id bigint,
    primary key (user_id, following_id),
    constraint fk_follows_users_1 foreign key (user_id) references users (id) on delete cascade,
    constraint fk_follows_users_2 foreign key (following_id) references users (id) on delete cascade
) ENGINE InnoDB;

create table posts
(
    id          bigint auto_increment,
    user_id     bigint,
    description varchar(255),
    img         varchar(255),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id),
    constraint fk_posts_users foreign key (user_id) references users (id) on delete cascade,
    index idx_user_id (user_id ASC)
) ENGINE InnoDB;

create table like_posts
(
    post_id bigint,
    user_id bigint,
    primary key (post_id, user_id),
    constraint fk_like_posts_posts foreign key (post_id) references posts (id) on delete cascade,
    constraint fk_like_posts_users foreign key (user_id) references users (id) on delete cascade
) ENGINE InnoDB;

create table friends
(
    user_id   bigint,
    friend_id bigint,
    primary key (user_id, friend_id),
    constraint fk_friends_users_1 foreign key (user_id) references users (id) on delete cascade,
    constraint fk_friends_users_2 foreign key (friend_id) references users (id) on delete cascade

) ENGINE InnoDB;

create table user_conversations
(
    first_user_id  bigint,
    second_user_id bigint,
    constraint fk_conversations_users_1 foreign key (first_user_id) references users (id) on delete cascade,
    constraint fk_conversations_users_2 foreign key (second_user_id) references users (id) on delete cascade,
    index idx_first_id (first_user_id ASC),
    index idx_second_id (second_user_id ASC)
) ENGINE InnoDB;


create table user_messages
(
    id          bigint auto_increment,
    sender_id   bigint,
    receiver_id bigint,
    content     nvarchar(255),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    primary key (id),
    constraint fk_messages_users_1 foreign key (sender_id) references users (id) on delete cascade,
    constraint fk_messages_users_2 foreign key (receiver_id) references users (id) on delete cascade,
    index idx_sender_id (sender_id ASC),
    index idx_receiver_id (receiver_id ASC)
) ENGINE InnoDB;