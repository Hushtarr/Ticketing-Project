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
    ('2024-05-31 12:00:00', 1, false, '2024-05-31 12:00:00', 1, true, 'Carl', 'MALE', 'Jhonson', 'jdoe@example.com', 1),
    ('2024-05-31 12:00:00', 2, false, '2024-05-31 12:00:00', 2, true, 'Leonard', 'MALE', 'Owen', 'cena@example.com', 2),
    ('2024-05-31 12:00:00', 3, false, '2024-05-31 12:00:00', 3, true, 'Vayne', 'MALE', 'Flynn', 'jdoe@example.com', 3),
    ('2024-05-31 12:00:00', 6, false, '2024-05-31 12:00:00', 6, true, 'Emma', 'FEMALE', 'Wilson', 'emma@example.com', 3),
    ('2024-05-31 12:00:00', 7, false, '2024-05-31 12:00:00', 7, true, 'David', 'MALE', 'Brown', 'david@example.com', 2),
    ('2024-05-31 12:00:00', 8, false, '2024-05-31 12:00:00', 8, true, 'Olivia', 'FEMALE', 'Miller', 'olivia@example.com', 3),
    ('2024-05-31 12:00:00', 9, false, '2024-05-31 12:00:00', 9, true, 'James', 'MALE', 'Taylor', 'james@example.com', 2),
    ('2024-05-31 12:00:00', 10, false, '2024-05-31 12:00:00', 10, true, 'Isabella', 'FEMALE', 'Anderson', 'isabella@example.com', 3);




INSERT INTO projects (
    insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
    end_date, project_code, project_detail, project_name, project_status, start_date, manager_id
) VALUES
    ('2023-06-01 10:00:00', 2, false, '2023-06-02 11:00:00', 3, '2024-12-31', 'PRJ001', 'Development of APIs using Spring Boot.', 'Project One', 'OPEN', '2023-01-01', 5),
    ('2023-06-02 12:00:00', 3, false, '2023-06-03 13:00:00', 2, '2024-11-30', 'PRJ002', 'Implementation of microservices architecture with Spring Cloud.', 'Project Two', 'IN_PROGRESS', '2023-02-01', 7),
    ('2023-06-03 14:00:00', 2, false, '2023-06-04 15:00:00', 3, '2024-10-31', 'PRJ003', 'Spring Security integration for authentication and authorization.', 'Project Three', 'COMPLETE', '2023-03-01', 2),
    ('2023-06-04 16:00:00', 3, false, '2023-06-05 17:00:00', 2, '2024-09-30', 'PRJ004', 'Database management with Spring Data JPA.', 'Project Four', 'OPEN', '2023-04-01', 5),
    ('2023-06-05 18:00:00', 2, false, '2023-06-06 19:00:00', 3, '2024-08-31', 'PRJ005', 'Building reactive applications with Spring WebFlux.', 'Project Five', 'IN_PROGRESS', '2023-05-01', 7);


