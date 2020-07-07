CREATE TABLE `user`(
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`username` varchar(100),
`password` varchar(100),
`is_active` tinyint(4),
`roles` varchar(100),
CONSTRAINT PK PRIMARY KEY (`id`)
);