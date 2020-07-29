CREATE TABLE `task`(
`id_task` bigint(20) NOT NULL AUTO_INCREMENT,
`id_todo_list` bigint(20),
`name` varchar(200) NOT NULL,
`description` varchar(200) NOT NULL,
PRIMARY KEY (`id_task`),
CONSTRAINT FK_TASK_TODO_LIST FOREIGN KEY (`id_todo_list`)
REFERENCES `todo_list`(`id_todo_list`)
);