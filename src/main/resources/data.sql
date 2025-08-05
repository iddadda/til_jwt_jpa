INSERT INTO `items` (`id`, `NAME`, `img_path`, `price`, `discount_per`, `created`)
VALUES (1, 'Starry', '001.jpg', 10000000, 5, '2025-07-07 16:01:29'),
       (2, 'Seascape', '002.jpg', 20000000, 10, '2025-07-07 16:01:29'),
       (3, 'Arles', '003.jpg', 30000000, 15, '2025-07-07 16:01:29'),
       (4, 'Mountain', '004.jpg', 40000000, 20, '2025-07-07 16:01:29'),
       (5, 'Provence', '005.jpg', 50000000, 25, '2025-07-07 16:01:29'),
       (6, 'Houses', '006.jpg', 60000000, 30, '2025-07-07 16:01:29');

INSERT INTO `members` (`id`, `name`, `login_id`, `login_pw`, `created`)
VALUES (1, '홍길동동', 'mic', '$2a$10$BrDg3bw5.Id7N1Uj1lR9VeiQyVFbuKMjrX5Sqo6p4whRRgih8yVRy', '2025-08-01 13:02:54'),
       (2, '우디아', 'udea', '$2a$10$K/1amQN4/SResFu84V9tVuO7c17voICz.RyGIoi/mqj9qMhKZzUHm', '2025-08-01 13:03:29'),
       (3, '텀즈', 'tums', '$2a$10$qL1wBWixo6RhMmm8giV6f.kKpVYFQG99Y6IEt8yLY5D2LlWUfpgNS', '2025-08-01 14:46:47');

INSERT INTO `members_roles` (`members_id`, `role_name`) VALUES
                                                            (1, 'ROLE_USER_1'),
                                                            (2, 'ROLE_USER_2'),
                                                            (3, 'ROLE_USER_1'),
                                                            (3, 'ROLE_USER_2');
