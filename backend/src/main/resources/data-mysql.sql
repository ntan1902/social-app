INSERT INTO users(id, username, `password`, email)
VALUES (1, 'ntan1902', '$2y$10$/KO/hcLUIEXNxsWfop6hmeh255Po0.Y2eJ66KCm0grl0HM8N81VJG', 'trinh.an.1902@gmail.com'),
       (2, 'ohnguyen', '$2y$10$/KO/hcLUIEXNxsWfop6hmeh255Po0.Y2eJ66KCm0grl0HM8N81VJG', 'ohnguyen@fit.hcmus.edu.vn'),
       (3, 'nthao', '$2y$10$/KO/hcLUIEXNxsWfop6hmeh255Po0.Y2eJ66KCm0grl0HM8N81VJG', 'nthao@fit.hcmus.edu.vn'),
       (4, 'dbtien', '$2y$10$/KO/hcLUIEXNxsWfop6hmeh255Po0.Y2eJ66KCm0grl0HM8N81VJG', 'dbtien@fit.hcmus.edu.vn'),
       (5, 'htthanh', '123456', 'htthanh@fit.hcmus.edu.vn');

INSERT INTO `follows`(userId, followingId)
VALUES (1, 2),
       (1, 4),
       (1, 5),
       (2, 1),
       (4, 1),
       (4, 2);

INSERT INTO `posts`(`userId`, `description`, `img`)
VALUES (1, 'Hey! It\'s my first post :)', 'https://images.unsplash.com/photo-1623764802787-eea7b530c90a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80'),
       (1, 'Hello everyone', 'https://images.unsplash.com/photo-1623646233730-2a516e4a825c?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80'),
       (2, 'It\'s so cool', 'https://images.unsplash.com/photo-1623750318496-d74639a6b2fe?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80');
