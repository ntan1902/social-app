INSERT INTO users(id, username, `password`, email, `profile_picture`, `cover_picture`, `description`, `city`,
                  `from_city`, `relationship`)
VALUES (1, 'ntan1902', '$2y$10$/KO/hcLUIEXNxsWfop6hmeh255Po0.Y2eJ66KCm0grl0HM8N81VJG', 'ntan1902@gmail.com',
        'https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80',
        'https://images.unsplash.com/photo-1500099817043-86d46000d58f?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80',
        'Hi, I\'m An', 'Tp. Ho Chi Minh', 'Tp. Ho Chi Minh', 'SINGLE'),
       (2, 'ohnguyen', '$2y$10$/KO/hcLUIEXNxsWfop6hmeh255Po0.Y2eJ66KCm0grl0HM8N81VJG', 'ohnguyen@fit.hcmus.edu.vn',
        'https://images.unsplash.com/photo-1599566150163-29194dcaad36?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80',
        'https://images.unsplash.com/photo-1504805572947-34fad45aed93?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1050&q=80',
        'Hi, I\'m Nguyen', 'Tp. Ho Chi Minh', 'Ha Noi', 'SINGLE'),
       (3, 'nthao', '$2y$10$/KO/hcLUIEXNxsWfop6hmeh255Po0.Y2eJ66KCm0grl0HM8N81VJG', 'nthao@fit.hcmus.edu.vn',
        'https://images.unsplash.com/photo-1531427186611-ecfd6d936c79?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=634&q=80',
        'https://images.unsplash.com/photo-1492112007959-c35ae067c37b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=631&q=80',
        'Hi, I\'m Hao', 'Tp. Ho Chi Minh', 'LA', 'SINGLE'),
       (4, 'dbtien', '$2y$10$/KO/hcLUIEXNxsWfop6hmeh255Po0.Y2eJ66KCm0grl0HM8N81VJG', 'dbtien@fit.hcmus.edu.vn',
        'https://images.unsplash.com/photo-1543610892-0b1f7e6d8ac1?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=634&q=80',
        'https://images.unsplash.com/photo-1515138692129-197a2c608cfd?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80',
        'Hi, I\'m Tien', 'Tp. Ho Chi Minh', 'Tp. Ho Chi Minh', 'MARRIED'),
       (5, 'htthanh', '123456', 'htthanh@fit.hcmus.edu.vn', '', '', '', '', '', 'EMPTY');

INSERT INTO `follows`(user_id, following_id)
VALUES (1, 2),
       (1, 4),
       (1, 5),
       (2, 1),
       (4, 1),
       (4, 2);

INSERT INTO `posts`(id, `user_id`, `description`, `img`, `created_at`, `updated_at`)
VALUES (1, 1, 'Hey! It\'s my first post :)',
        'https://images.unsplash.com/photo-1482061855243-4d326cb25fe2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1050&q=80',
        '2021-06-25T14:29:48', '2021-06-25T19:29:48'),
       (2, 1, 'Hello everyone',
        'https://images.unsplash.com/photo-1487715433499-93acdc0bd7c3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1114&q=80',
        '2021-06-25T14:29:48', '2021-06-25T10:29:48'),
       (3, 2, 'It\'s so cool',
        'https://images.unsplash.com/photo-1473042904451-00171c69419d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1075&q=80',
        '2021-06-25T14:29:48', '2021-06-25T12:29:48'),
       (4, 1, 'Beautiful view',
        'https://images.unsplash.com/photo-1519865885898-a54a6f2c7eea?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=695&q=80',
        '2021-06-26T17:13:38',  '2021-06-26T17:13:38'),
       (5, 2, 'It\'s water',
        'https://images.unsplash.com/photo-1533167649158-6d508895b680?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1489&q=80',
        '2021-06-26T17:17:38', '2021-06-26T17:17:38');
INSERT INTO `like_posts`(`post_id`, `user_id`)
VALUES (1, 2),
       (1, 3),
       (2, 4);
