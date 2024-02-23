CREATE TABLE IF NOT EXISTS "users" (
    username VARCHAR(20) NOT NULL,
    password VARCHAR(200) NOT NULL,
    email VARCHAR(50),
    locked SMALLINT NOT NULL,
    disabled SMALLINT NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS "roles" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users_roles (
  user_id VARCHAR(20) NOT NULL,
  role_id INT NOT NULL,
  CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES "users"(username) ON DELETE CASCADE,
  CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES "roles"(id) ON DELETE CASCADE,
  PRIMARY KEY (user_id, role_id)
);
