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


-- Crear la tabla words
CREATE TABLE words (
    id SERIAL PRIMARY KEY,
    word VARCHAR(50) NOT NULL
);

-- Crear la tabla sites (correspondiente a países)
CREATE TABLE sites (
    id SERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(100) NOT NULL
);

-- Crear la tabla de relación entre sitios y palabras
CREATE TABLE site_words (
    site_id INTEGER REFERENCES sites(id),
    word_id INTEGER REFERENCES words(id),
    PRIMARY KEY (site_id, word_id)
);

-- Crear la tabla de estadísticas
CREATE TABLE stats (
    id SERIAL PRIMARY KEY,
    request VARCHAR(1000) NOT NULL,
    response VARCHAR(1000) NOT NULL,
    execution_time BIGINT NOT NULL,
    request_id VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL
);