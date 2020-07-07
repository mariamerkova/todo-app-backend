ALTER TABLE `todo_list`
ADD `user_id` bigint(50),
add constraint FK_TODO_LIST_USER foreign key(`user_id`) references `user`(`id`);


