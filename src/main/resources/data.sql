INSERT INTO roles (
    insert_date_time,
    insert_user_id,
    is_deleted,
    last_update_date_time,
    last_update_user_id,
    description)
VALUES
      ('2024-05-31 12:00:00', 1, false, '2024-05-31 12:00:00', 1, 'Admin'),
      ('2024-05-31 12:05:00', 1, false, '2024-05-31 12:05:00', 2, 'Manager'),
      ('2024-05-31 12:10:00', 1, false, '2024-05-31 12:10:00', 3, 'Employee');

INSERT INTO users (insert_date_time,insert_user_id,is_deleted,last_update_date_time,last_update_user_id,enabled,
first_name,gender,last_name,user_name,role_id)
VALUES
    ('2024-05-31 12:00:00', 1, false, '2024-05-31 12:00:00', 1, true, 'John', 'MALE', 'Doe', 'jdoe@example.com', 1);

