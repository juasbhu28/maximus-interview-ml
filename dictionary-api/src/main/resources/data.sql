INSERT INTO roles (id, name) VALUES (1, 'USER'), (2, 'ADMIN');

-- Assuming 'mipassword' is a placeholder for an actual BCrypt encrypted password.
INSERT INTO users (username, password, email, locked, disabled) VALUES
('johndoe', 'mipassword', 'john.doe@example.com', 0, 0);

INSERT INTO users_roles (user_id, role_id) VALUES
('johndoe', 1);


-- Insertar palabras comunes en español
INSERT INTO words (word) VALUES
    ('boludo'),
    ('chabón'),
    ('che'),
    ('culiado'),
    ('facha'),
    ('pibe'),
    ('guay'),
    ('vale'),
    ('colega'),
    ('fiesta'),
    ('tío'),
    ('chaval'),
    ('coche'),
    ('ordenador'),
    ('playa'),
    ('verano'),
    ('chido'),
    ('padre'),
    ('chamba'),
    ('cuate'),
    ('guayabo'),
    ('güey'),
    ('carro'),
    ('computadora'),
    ('playa'),
    ('verano');

-- Insertar datos de países
INSERT INTO sites (code, description) VALUES
    ('AR', 'Argentina'),
    ('ES', 'España'),
    ('MX', 'México');

-- Insertar datos de relación entre sitios y palabras en la tabla site_words
-- Asignar palabras comunes para Argentina (AR)
INSERT INTO site_words (site_id, word_id)
SELECT s.id, w.id
FROM sites s, words w
WHERE s.code = 'AR';

-- Insertar más palabras comunes para España (ES)
INSERT INTO site_words (site_id, word_id)
SELECT s.id, w.id
FROM sites s, words w
WHERE s.code = 'ES'
AND w.word IN ('guay', 'vale', 'colega', 'fiesta', 'tío', 'chaval', 'coche', 'ordenador', 'playa', 'verano');

-- Insertar más palabras comunes para México (MX)
INSERT INTO site_words (site_id, word_id)
SELECT s.id, w.id
FROM sites s, words w
WHERE s.code = 'MX'
AND w.word IN ('chido', 'padre', 'chamba', 'cuate', 'guayabo', 'güey', 'carro', 'computadora', 'playa', 'verano');
