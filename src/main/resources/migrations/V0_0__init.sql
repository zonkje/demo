/*
    Version: 0.0
    Description: DB Structure and data init.
*/


/*
    Structure
*/
CREATE TABLE `users`
(
    `id`                         bigint(20) NOT NULL AUTO_INCREMENT,
    `email`                      varchar(255) DEFAULT NULL,
    `first_name`                 varchar(255) DEFAULT NULL,
    `is_account_non_expired`     bit(1)       DEFAULT NULL,
    `is_account_non_locked`      bit(1)       DEFAULT NULL,
    `is_credentials_non_expired` bit(1)       DEFAULT NULL,
    `is_enabled`                 bit(1)       DEFAULT NULL,
    `password`                   varchar(255) DEFAULT NULL,
    `last_name`                  varchar(255) DEFAULT NULL,
    `username`                   varchar(255) DEFAULT NULL,
    `role`                       varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 36
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `posts`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT,
    `content`            varchar(255) DEFAULT NULL,
    `creation_date_time` datetime     DEFAULT NULL,
    `title`              varchar(255) DEFAULT NULL,
    `post_author_id`     bigint(20)   DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK7g865rj7mm8l44s91ebe2wbcs` (`post_author_id`),
    CONSTRAINT `FK7g865rj7mm8l44s91ebe2wbcs` FOREIGN KEY (`post_author_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 39
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `user_granted_authorities`
(
    `user_id`             bigint(20) NOT NULL,
    `granted_authorities` varchar(255) DEFAULT NULL,
    KEY `FKabh7nyetaaiin2lb4tokajj4c` (`user_id`),
    CONSTRAINT `FKabh7nyetaaiin2lb4tokajj4c` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

/*
    Data
*/
INSERT INTO `users`
VALUES (35, 'admin123@example.com', 'Admin', _binary '', _binary '', _binary '', _binary '',
        '$2a$05$L1NX.NOdNto.vNI5S2MWWOQfrsdOyEdHHLNckAQ4ggghPws2rTCI6', 'JedenDwaTrzy', 'admin123', 'USER');

INSERT INTO `posts`
VALUES (36, 'There are still bugs in this app :/', '2020-07-27 22:36:11', 'First Post', 35),
       (37, 'this app looks awful, who even did that?', '2020-07-27 22:39:56', 'Interface', 35),
       (38, 'Comments doesn\'t work, wake up dev!', '2020-07-27 22:41:04', 'Comments', 35);

