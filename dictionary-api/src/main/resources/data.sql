INSERT INTO roles (id, name) VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');

-- Assuming 'mipassword' is a placeholder for an actual BCrypt encrypted password.
INSERT INTO users (username, password, email, locked, disabled) VALUES
('johndoe', 'mipassword', 'john.doe@example.com', 0, 0);

INSERT INTO users_roles (user_id, role_id) VALUES
('johndoe', 1);
