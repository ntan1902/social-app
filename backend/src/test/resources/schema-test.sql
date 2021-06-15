create table users
(
    id       bigint auto_increment,
    username varchar(50),
    password varchar(255),
    email    varchar(50),
    primary key (id)
) ;

create table follows
(
    user_id      bigint,
    following_id bigint,
    primary key (user_id, following_id),
    constraint fk_follows_users_1 foreign key (user_id) references users (id) on delete cascade,
    constraint fk_follows_users_2 foreign key (following_id) references users (id) on delete cascade
) ;

insert into users(id, username, `password`, email) values
(1, 'ntan1902', '$2y$10$/KO/hcLUIEXNxsWfop6hmeh255Po0.Y2eJ66KCm0grl0HM8N81VJG', 'trinh.an.1902@gmail.com'),
(2, 'ohnguyen', '$2y$10$/KO/hcLUIEXNxsWfop6hmeh255Po0.Y2eJ66KCm0grl0HM8N81VJG', 'ohnguyen@fit.hcmus.edu.vn'),
(3, 'nthao', '$2y$10$/KO/hcLUIEXNxsWfop6hmeh255Po0.Y2eJ66KCm0grl0HM8N81VJG', 'nthao@fit.hcmus.edu.vn'),
(4, 'dbtien', '$2y$10$/KO/hcLUIEXNxsWfop6hmeh255Po0.Y2eJ66KCm0grl0HM8N81VJG', 'dbtien@fit.hcmus.edu.vn'),
(5, 'htthanh', '$2y$10$/KO/hcLUIEXNxsWfop6hmeh255Po0.Y2eJ66KCm0grl0HM8N81VJG', 'htthanh@fit.hcmus.edu.vn');

insert into `follows`(user_id, following_id) values
(1, 2),
(1, 4),
(1, 5),
(2, 1),
(4, 1),
(4, 2);
