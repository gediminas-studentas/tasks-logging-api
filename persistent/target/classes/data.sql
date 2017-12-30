INSERT INTO task (name, group_name) VALUES ('Task 1', 'Group 1');
INSERT INTO task (name, group_name) VALUES ('Task 2', 'Group 1');
INSERT INTO task (name, group_name) VALUES ('Sub-task 1', 'Group 2');
INSERT INTO task (name, group_name) VALUES ('Sub-task 2', 'Group 2');
INSERT INTO task_mapping (parent_id, child_id) VALUES (1, 3);
INSERT INTO task_mapping (parent_id, child_id) VALUES (1, 4);